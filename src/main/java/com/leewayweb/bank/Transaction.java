package com.leewayweb.bank;

public class Transaction {
    private final int amount;

    public Transaction(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Transaction && ((Transaction) other).amount == this.amount;
    }
}
