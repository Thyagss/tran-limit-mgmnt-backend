package com.transaction.model;

import java.math.BigDecimal;

public class Account {

    private int accountId;
    private int customerId;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal dailyLimit;

    public Account() {}

    public Account(int customerId, String accountNumber,
                   BigDecimal balance, BigDecimal dailyLimit) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.dailyLimit = dailyLimit;
    }

    // Getters & Setters

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public BigDecimal getDailyLimit() { return dailyLimit; }
    public void setDailyLimit(BigDecimal dailyLimit) { this.dailyLimit = dailyLimit; }
}