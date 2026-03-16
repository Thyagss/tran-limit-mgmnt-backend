package com.transaction.dao;

import com.transaction.model.Account;
import com.transaction.util.DBConnection;
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
}