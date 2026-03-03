package com.transaction.service;

import com.transaction.dao.AccountDAO;
import com.transaction.model.Account;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountService {

    private AccountDAO accountDAO = new AccountDAO();

    public boolean createAccount(int customerId) {

        String accountNumber = generateAccountNumber();

        Account account = new Account(
                customerId,
                accountNumber,
                new BigDecimal("0.00"),
                new BigDecimal("10000.00")
        );

        return accountDAO.createAccount(account);
    }

    private String generateAccountNumber() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}