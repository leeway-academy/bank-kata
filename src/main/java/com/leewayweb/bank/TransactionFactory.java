package com.leewayweb.bank;

public class TransactionFactory {
    public TransactionFactory() {
    }

    public Transaction buildTransaction(int amount) {
        return new Transaction(amount);
    }
}