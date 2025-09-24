package com.leewayweb.bank;

public class AccountService {
    private final Account account;
    private final TransactionFactory transactionFactory;

    public AccountService(Account account, TransactionFactory transactionFactory) {
        this.account = account;
        this.transactionFactory = transactionFactory;
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
