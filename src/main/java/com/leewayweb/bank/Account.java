package com.leewayweb.bank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> transactions() {
        return this.transactions;
    }

    public int balance() {
        throw new UnsupportedOperationException("balance not yet implemented");
    }
}
