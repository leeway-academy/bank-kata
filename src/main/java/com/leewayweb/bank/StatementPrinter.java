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
        int balance = 0;

        ArrayList<String> lines = new ArrayList<>();

        for (Transaction t : sort(transactions)) {
            balance += t.amount();
            lines.add(formatTransaction(t, balance));
        }
        for (String line : lines.reversed()) {
            console.printLine(line);
        }
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

    private static String formatTransaction(Transaction t, int balance) {
        return t.date() + SEPARATOR + t.amount() + SEPARATOR + balance;
    }
}
