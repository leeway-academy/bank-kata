package com.leewayweb.bank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List transactions() {
        return this.transactions;
    }
}
