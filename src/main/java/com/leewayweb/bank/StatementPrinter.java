package com.leewayweb.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StatementPrinter {
    public static final String SEPARATOR = " | ";
    private static final String HEADER = "DATE" + SEPARATOR + "AMOUNT" + SEPARATOR + "BALANCE";
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    private final Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        printHeader();
        printTransactions(transactions);
    }

    private void printHeader() {
        console.printLine(HEADER);
    }

    private void printTransactions(List<Transaction> transactions) {

        for (String line : buildStatementLines(transactions).reversed()) {
            console.printLine(line);
        }
    }

    private static ArrayList<String> buildStatementLines(List<Transaction> transactions) {
        ArrayList<String> lines = new ArrayList<>();
        int balance = 0;

        for (Transaction transaction : sort(transactions)) {
            balance += transaction.amount();
            lines.add(format(transaction, balance));
        }

        return lines;
    }

    private static List<Transaction> sort(List<Transaction> transactions) {
        return transactions
                .stream()
                .sorted(
                        Comparator.comparing(tx -> LocalDate.parse(tx.date(), DateTimeFormatter.ofPattern(DATE_FORMAT)))
                )
                .toList()
                ;
    }

    private static String format(Transaction t, int balance) {
        return t.date() + SEPARATOR + t.amount() + SEPARATOR + balance;
    }
}
