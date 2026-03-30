package com.transaction.dao;

import com.transaction.model.Account;
import com.transaction.util.DBconfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {

    public boolean createAccount(Account account) {

        String sql = "INSERT INTO accounts (customer_id, account_number, balance, daily_limit) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBconfig.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, account.getCustomerId());
            ps.setString(2, account.getAccountNumber());
            ps.setBigDecimal(3, account.getBalance());
            ps.setBigDecimal(4, account.getDailyLimit());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getBalance(int accountId, Connection conn) throws Exception {

        String sql = "SELECT balance FROM accounts WHERE account_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);

            var rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }
        }

        return 0;
    }

    public void updateBalance(int accountId, double newBalance, Connection conn) throws Exception {

        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, accountId);

            ps.executeUpdate();
        }
    }

    public double getDailyLimit(int accountId, Connection conn) throws Exception {

        String sql = "SELECT daily_limit FROM accounts WHERE account_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);

            var rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("daily_limit");
            }
        }

        return 0;
    }

}