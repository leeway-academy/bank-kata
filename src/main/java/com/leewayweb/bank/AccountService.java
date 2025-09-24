package com.leewayweb.bank;

public class AccountService {
    private final Account account;

    public AccountService(Account account) {
        this.account = account;
    }

    public void deposit(int amount) {
        account.addTransaction(buildTransaction(amount));
    }

    private Transaction buildTransaction(int amount) {
        return new Transaction(amount);
    }

    public void withdraw(int amount) {

    }

    public void printStatement() {

    }
}
