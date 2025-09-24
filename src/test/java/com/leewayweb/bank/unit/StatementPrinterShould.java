package com.leewayweb.bank.unit;

import com.leewayweb.bank.Console;
import com.leewayweb.bank.StatementPrinter;
import com.leewayweb.bank.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class StatementPrinterShould {

    private static final String HEADER = "DATE | AMOUNT | BALANCE";
    public static final String WITHDRAWAL_LINE = "22/12/1978 | -50 | 50";
    public static final String DEPOSIT_LINE = "22/12/1977 | 100 | 100";
    private @Mock Console console;
    private StatementPrinter printer;

    private static final Transaction A_DEPOSIT = new Transaction(100, "22/12/1977");
    private static final Transaction A_WITHDRAWAL = new Transaction(-50, "22/12/1978");

    @BeforeEach
    void setUp() {
        printer = new StatementPrinter(console);
    }

    @ParameterizedTest
    @MethodSource("provider")
    public void printTransactionsInReverseChronologicalOrder(ArrayList<Transaction> transactions, ArrayList<String> lines) {
        printer.print(transactions);
        InOrder inOrder = Mockito.inOrder(console);
        for (String line : lines) {
            inOrder.verify(console).printLine(line);
        }
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(
                        new ArrayList<Transaction>(),
                        new ArrayList<>(List.of(HEADER))
                ),
                Arguments.of(
                        new ArrayList<>(List.of(A_DEPOSIT)),
                        new ArrayList<>(List.of(HEADER, DEPOSIT_LINE))
                ),
                Arguments.of(
                        new ArrayList<>(List.of(A_DEPOSIT, A_WITHDRAWAL)),
                        new ArrayList<>(List.of(HEADER, WITHDRAWAL_LINE, DEPOSIT_LINE))
                ),
                Arguments.of(
                        new ArrayList<>(List.of(A_WITHDRAWAL, A_DEPOSIT)),
                        new ArrayList<>(List.of(HEADER, WITHDRAWAL_LINE, DEPOSIT_LINE))
                )
        );
    }
}