package com.transaction.model;
import java.sql.Timestamp;

public class Transaction {

    private int txnId;
    private int accountId;
    private String txnType;
    private double amount;
    private java.sql.Timestamp txnDate;

    public Transaction() {}

    public Transaction(int accountId, String txnType, double amount) {
        this.accountId = accountId;
        this.txnType = txnType;
        this.amount = amount;
    }

    public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Timestamp txnDate) {
        this.txnDate = txnDate;
    }
}