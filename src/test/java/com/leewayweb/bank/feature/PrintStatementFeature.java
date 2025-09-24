package com.leewayweb.bank.feature;

import com.leewayweb.bank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrintStatementFeature {
    @Mock Console console;
    @Mock Clock clock;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        when(clock.date())
                .thenReturn("01/04/2020", "02/04/2020", "10/04/2020");

        accountService = new AccountService(
                new Account(),
                new TransactionFactory(clock),
                new StatementPrinter(console)
        );
    }

    @Test
    public void print_statement_containing_all_transactions() {

        accountService.deposit(1000);
        accountService.withdraw(100);
        accountService.deposit(500);

        accountService.printStatement();

        InOrder inOrder = Mockito.inOrder(console);

        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2020 | 500 | 1400");
        inOrder.verify(console).printLine("02/04/2020 | -100 | 900");
        inOrder.verify(console).printLine("01/04/2020 | 1000 | 1000");
    }
}
