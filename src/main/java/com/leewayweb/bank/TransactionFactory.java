package com.leewayweb.bank;

public class TransactionFactory {
    public TransactionFactory() {
    }

    Transaction buildTransaction(int amount) {
        return new Transaction(amount);
    }
}