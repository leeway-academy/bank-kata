package com.leewayweb.bank.unit;

import com.leewayweb.bank.Console;
import com.leewayweb.bank.StatementPrinter;
import com.leewayweb.bank.Transaction;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class StatementPrinterShould {

    private static final String HEADER = "DATE | AMOUNT | BALANCE";
    public static final String WITHDRAWAL_LINE = "22/12/1978 | -50 | 50";
    public static final String DEPOSIT_LINE = "22/12/1977 | 100 | 100";

    public static final ArrayList<Transaction> NO_TRANSACTIONS = new ArrayList<>();
    private static final Transaction A_DEPOSIT = new Transaction(100, "22/12/1977");
    private static final Transaction A_WITHDRAWAL = new Transaction(-50, "22/12/1978");

    private @Mock Console console;
    @InjectMocks
    private StatementPrinter printer;

    @ParameterizedTest
    @MethodSource("provider")
    public void printTransactionsInReverseChronologicalOrder(ArrayList<Transaction> transactions, ArrayList<String> lines) {
        InOrder inOrder = Mockito.inOrder(console);

        printer.print(transactions);

        for (String line : lines) {
            inOrder.verify(console).printLine(line);
        }
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(
                        NO_TRANSACTIONS,
                        lines(HEADER)
                ),
                Arguments.of(
                        transactions(A_DEPOSIT),
                        lines(HEADER, DEPOSIT_LINE)
                ),
                Arguments.of(
                        transactions(A_DEPOSIT, A_WITHDRAWAL),
                        lines(HEADER, WITHDRAWAL_LINE, DEPOSIT_LINE)
                ),
                Arguments.of(
                        transactions(A_WITHDRAWAL, A_DEPOSIT),
                        lines(HEADER, WITHDRAWAL_LINE, DEPOSIT_LINE)
                )
        );
    }

    private static ArrayList<Transaction> transactions(Transaction... transactions) {
        return new ArrayList<>(Arrays.asList(transactions));
    }

    private static ArrayList<String> lines(String... strings) {
        return new ArrayList<>(Arrays.asList(strings));
    }
}