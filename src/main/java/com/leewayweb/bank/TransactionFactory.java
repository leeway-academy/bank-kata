package com.leewayweb.bank;

public class TransactionFactory {

    private final Clock clock;

    public TransactionFactory(Clock clock) {
        this.clock = clock;
    }

    public Transaction buildTransaction(int amount) {

        return new Transaction(amount, clock.date());
    }
}