package com.leewayweb.bank;

import java.io.IOException;

public class Application {

    private static final Console console = new Console();

    static void main() throws IOException {
        AccountService accountService = new AccountService(
                new Account(),
                new TransactionFactory(new Clock()),
                new StatementPrinter(console)
        );

        int option;

        do {
            printMenu();
            option = getUserOption();
            switch (option) {
                case 1:
                    accountService.deposit(getAmount());
                    break;
                case 2:
                    accountService.withdraw(getAmount());
                    break;
                case 3:
                    accountService.printStatement();
                    break;
                case 4:
                    break;
                default:
                    console.printLine( "Unknown option " + option );
            }
        } while (option != 4);
    }

    private static int getAmount() throws IOException {
        console.printLine("Please enter the desired amount: ");

        return Integer.parseInt(console.readLine());
    }

    private static int getUserOption() throws IOException {
        return Integer.parseInt(console.readLine());
    }

    private static void printMenu() {
        console.printLine("Menu");
        console.printLine("====");
        console.printLine("1 - Deposit");
        console.printLine("2 - Withdrawal");
        console.printLine("3 - Print Statement");
        console.printLine("4 - Quit");
    }
}
