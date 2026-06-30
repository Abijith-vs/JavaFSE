
DECLARE

    CURSOR c_accounts IS
        SELECT account_id, customer_name, balance
        FROM customer_balances;

    v_bonus NUMBER(10, 2);
    v_new_balance NUMBER(10, 2);
    v_tier VARCHAR2(20);
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- PROCESSING ACCOUNT TIER UPDATES ---');


    FOR r_acc IN c_accounts LOOP

        IF r_acc.balance >= 10000 THEN
            v_bonus := r_acc.balance * 0.05;
            v_new_balance := r_acc.balance + v_bonus;
            v_tier := 'VIP';

            DBMS_OUTPUT.PUT_LINE('Customer ' || r_acc.customer_name || ' upgraded to VIP. Bonus: $' || v_bonus);

        ELSIF r_acc.balance >= 1000 AND r_acc.balance < 10000 THEN
            v_bonus := r_acc.balance * 0.02;
            v_new_balance := r_acc.balance + v_bonus;
            v_tier := 'PREMIUM';

            DBMS_OUTPUT.PUT_LINE('Customer ' || r_acc.customer_name || ' upgraded to Premium. Bonus: $' || v_bonus);

        ELSE

            v_new_balance := r_acc.balance - 5.00;
            v_tier := 'LOW BALANCE';

            DBMS_OUTPUT.PUT_LINE('Customer ' || r_acc.customer_name || ' penalized below minimum balance threshold.');
        END IF;

        UPDATE customer_balances
        SET balance = v_new_balance,
            account_tier = v_tier
        WHERE account_id = r_acc.account_id;

        INSERT INTO transaction_logs (account_id, log_message)
        VALUES (r_acc.account_id, 'Tier updated to ' || v_tier || '. Adjusted Balance: ' || v_new_balance);

    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Database batch updates completed successfully.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Critical operational failure. Stack Message: ' || SQLERRM);
END;
/
