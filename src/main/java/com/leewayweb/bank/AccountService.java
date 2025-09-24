package com.leewayweb.bank;

public class AccountService {
    private final Account account;
    private final TransactionFactory transactionFactory = new TransactionFactory();

    public AccountService(Account account) {
        this.account = account;
    }

    public void deposit(int amount) {
        account.addTransaction(transactionFactory.buildTransaction(amount));
    }

    private Transaction buildTransaction(int amount) {
        return transactionFactory.buildTransaction(amount);
    }

    public void withdraw(int amount) {

    }

    public void printStatement() {

    }
}
