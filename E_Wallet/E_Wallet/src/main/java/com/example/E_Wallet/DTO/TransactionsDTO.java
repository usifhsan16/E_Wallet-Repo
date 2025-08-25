package com.example.E_Wallet.DTO;

import com.example.E_Wallet.Entity.Transactions;

import java.util.UUID;

public class TransactionsDTO {
    private UUID TransactionId;
    private String TransactionType;
    private double TransactionAmount;

    public UUID getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(UUID transactionId) {
        TransactionId = transactionId;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public double getTransactionAmount() {
        return TransactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        TransactionAmount = transactionAmount;
    }

    public TransactionsDTO todto(Transactions transactions){
        TransactionsDTO transactionsDTO= new TransactionsDTO();
        transactionsDTO.TransactionId=transactions.getTransactionId();
        transactionsDTO.TransactionAmount=transactions.getTransactionAmount();
        transactionsDTO.TransactionType=transactions.getTransactionType();
        return transactionsDTO;
    }
}
