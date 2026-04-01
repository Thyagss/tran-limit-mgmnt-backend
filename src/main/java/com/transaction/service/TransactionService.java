package com.transaction.service;

import com.transaction.model.Transaction;
import com.transaction.util.DBconfig;
import com.transaction.dao.TransactionDAO;
import com.transaction.dao.AccountDAO;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private TransactionDAO txnDAO = new TransactionDAO();
    private AccountDAO accDAO = new AccountDAO();

    public boolean deposit(int accountId, double amount) {

        try (Connection conn = DBconfig.getDataSource().getConnection()) {

            conn.setAutoCommit(false);

            logger.info("STEP 1: Got connection");

            double balance = accDAO.getBalance(accountId, conn);
            logger.info("STEP 2: Balance = {}", balance);

            accDAO.updateBalance(accountId, balance + amount, conn);
            logger.info("STEP 3: Balance updated to {}", (balance + amount));

            Transaction txn = new Transaction(accountId, "DEPOSIT", amount);
            logger.info("STEP 4: Before saving transaction");

            txnDAO.saveTransaction(txn, conn);
            logger.info("STEP 5: Transaction saved");

            conn.commit();
            logger.info("STEP 6: Commit successful");

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean withdraw(int accountId, double amount) {

        Connection conn = null;

        try {

            conn = DBconfig.getDataSource().getConnection();

            conn.setAutoCommit(false);

            logger.info("STEP1: Got connection");

            double balance = accDAO.getBalance(accountId, conn);
            logger.info("STEP2: Balance = {}", balance);

            if (balance < amount) return false;

            double today = txnDAO.getTodayWithdrawnAmount(accountId, conn);

            double limit = accDAO.getDailyLimit(accountId, conn);

            if (today + amount > limit) return false;

            accDAO.updateBalance(accountId, balance - amount, conn);
            logger.info("STEP3: Balance updated to {}", (balance - amount));

            Transaction txn = new Transaction(accountId, "WITHDRAW", amount);
            logger.info("STEP4: Before saving transaction");

            txnDAO.saveTransaction(txn, conn);
            logger.info("STEP5: Transaction saved");

            conn.commit();
            logger.info("STEP6: Commit successful");

            return true;

        } catch (Exception e) {
            logger.error("Transaction failed for accountId: {}", accountId, e);

            if (conn != null){
                try{
                    conn.rollback();
                } catch (Exception ex) {
                    logger.error("Rollback failed", ex);
                }
            }
        }

        return false;
    }
}
