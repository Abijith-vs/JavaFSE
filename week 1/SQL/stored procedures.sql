CREATE OR REPLACE PROCEDURE Process_Funds_Transfer (
    p_sender_id   IN NUMBER,
    p_receiver_id IN NUMBER,
    p_amount      IN NUMBER,
    p_out_status  OUT VARCHAR2
) AS
    v_sender_bal    NUMBER(10, 2);
    v_receiver_cnt  NUMBER;

    ERR_INSUFFICIENT_FUNDS EXCEPTION;
    ERR_INVALID_ACCOUNT    EXCEPTION;
BEGIN
    BEGIN
        SELECT balance INTO v_sender_bal
        FROM customer_balances
        WHERE account_id = p_sender_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE ERR_INVALID_ACCOUNT;
    END;

    SELECT COUNT(*) INTO v_receiver_cnt
    FROM customer_balances
    WHERE account_id = p_receiver_id;

    IF v_receiver_cnt = 0 THEN
        RAISE ERR_INVALID_ACCOUNT;
    END IF;

    IF v_sender_bal < p_amount THEN
        RAISE ERR_INSUFFICIENT_FUNDS;
    END IF;

    UPDATE customer_balances
    SET balance = balance - p_amount
    WHERE account_id = p_sender_id;

    UPDATE customer_balances
    SET balance = balance + p_amount
    WHERE account_id = p_receiver_id;

    INSERT INTO transaction_logs (account_id, log_message)
    VALUES (p_sender_id, 'Debited $' || p_amount || ' via transfer to Account ' || p_receiver_id);

    INSERT INTO transaction_logs (account_id, log_message)
    VALUES (p_receiver_id, 'Credited $' || p_amount || ' via transfer from Account ' || p_sender_id);

    COMMIT;
    p_out_status := 'SUCCESS: Transferred $' || p_amount || ' transaction completed safely.';

EXCEPTION
    WHEN ERR_INVALID_ACCOUNT THEN
        ROLLBACK;
        p_out_status := 'FAILED: Operational error - Invalid Sender or Receiver Account identity lookup.';

    WHEN ERR_INSUFFICIENT_FUNDS THEN
        ROLLBACK;
        p_out_status := 'FAILED: Transaction aborted due to insufficient funds.';

    WHEN OTHERS THEN
        ROLLBACK;
        p_out_status := 'FAILED: Internal server processing error: ' || SQLERRM;
END;
/
