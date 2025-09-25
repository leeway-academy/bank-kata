package com.leewayweb.bank;

public class AccountService {
    private final Account account;
    private final TransactionFactory transactionFactory;
    private final StatementPrinter statementPrinter;

    public AccountService(Account account, TransactionFactory transactionFactory, StatementPrinter statementPrinter) {
        this.account = account;
        this.transactionFactory = transactionFactory;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        account.addTransaction(transactionFactory.deposit(amount));
    }

    public void withdraw(int amount) {
        account.addTransaction(transactionFactory.withdrawal(amount));
    }

    public void printStatement() {
        statementPrinter
                .print(account.transactions());
    }
}
