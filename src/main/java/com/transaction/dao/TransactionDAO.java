package com.transaction.dao;

import com.transaction.model.Transaction;
import com.transaction.util.DBconfig;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class TransactionDAO {

    public boolean saveTransaction(Transaction txn, Connection conn) throws Exception {

        String query = "INSERT INTO transactions (account_id, txn_type, amount, txn_date) VALUES (?, ?, ?, NOW())";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, txn.getAccountId());
            ps.setString(2, txn.getTxnType());
            ps.setDouble(3, txn.getAmount());

            return ps.executeUpdate() > 0;
        }
    }

    public double getTodayWithdrawnAmount(int accountId, Connection conn) throws Exception {

        String query = "SELECT COALESCE(SUM(amount), 0) FROM transactions " +
                "WHERE account_id = ? AND txn_type = 'WITHDRAW' AND DATE(txn_date) = CURDATE()";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        }
        return 0;
    }

    public List<Transaction> getTransactions(int accountId) {

        List<Transaction> list = new ArrayList<>();

        String query = "SELECT * FROM transactions WHERE account_id = ? ORDER BY txn_date DESC";

        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction txn = new Transaction();

                txn.setTxnId(rs.getInt("txn_id"));
                txn.setAccountId(rs.getInt("account_id"));
                txn.setTxnType(rs.getString("txn_type"));
                txn.setAmount(rs.getDouble("amount"));
                txn.setTxnDate(rs.getTimestamp("txn_date"));

                list.add(txn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateBalance(int accountId, double newBalance, Connection conn) throws Exception {

        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, accountId);

            int rows = ps.executeUpdate();

            System.out.println("Rows updated: " + rows);
        }
    }
}