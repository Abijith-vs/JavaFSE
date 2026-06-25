-- PL/SQL Block for Exercise 1: Control Structures
DECLARE
    -- Explicit cursor tracking customer account details
    CURSOR c_accounts IS
        SELECT account_id, customer_name, balance
        FROM customer_balances;

    v_bonus NUMBER(10, 2);
    v_new_balance NUMBER(10, 2);
    v_tier VARCHAR2(20);
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- PROCESSING ACCOUNT TIER UPDATES ---');

    -- Cursor FOR Loop implicitly handling OPEN, FETCH, and CLOSE operations
    FOR r_acc IN c_accounts LOOP

        -- Control Structure: Evaluating conditions sequentially
        IF r_acc.balance >= 10000 THEN
            v_bonus := r_acc.balance * 0.05; -- 5% high wealth bonus
            v_new_balance := r_acc.balance + v_bonus;
            v_tier := 'VIP';

            DBMS_OUTPUT.PUT_LINE('Customer ' || r_acc.customer_name || ' upgraded to VIP. Bonus: $' || v_bonus);

        ELSIF r_acc.balance >= 1000 AND r_acc.balance < 10000 THEN
            v_bonus := r_acc.balance * 0.02; -- 2% standard growth interest
            v_new_balance := r_acc.balance + v_bonus;
            v_tier := 'PREMIUM';

            DBMS_OUTPUT.PUT_LINE('Customer ' || r_acc.customer_name || ' upgraded to Premium. Bonus: $' || v_bonus);

        ELSE
            -- Low balance penalty control flow execution
            v_new_balance := r_acc.balance - 5.00; -- Deduct $5 maintenance fee
            v_tier := 'LOW BALANCE';

            DBMS_OUTPUT.PUT_LINE('Customer ' || r_acc.customer_name || ' penalized below minimum balance threshold.');
        END IF;

        -- Apply changes safely within the active loop reference context
        UPDATE customer_balances
        SET balance = v_new_balance,
            account_tier = v_tier
        WHERE account_id = r_acc.account_id;

        -- Record audit action into logs table
        INSERT INTO transaction_logs (account_id, log_message)
        VALUES (r_acc.account_id, 'Tier updated to ' || v_tier || '. Adjusted Balance: ' || v_new_balance);

    END LOOP;

    COMMIT; -- Persist transactions permanently across all modified records
    DBMS_OUTPUT.PUT_LINE('Database batch updates completed successfully.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK; -- Protect atomic consistency and revert all modifications on error
        DBMS_OUTPUT.PUT_LINE('Critical operational failure. Stack Message: ' || SQLERRM);
END;
/
