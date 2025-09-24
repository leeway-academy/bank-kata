package com.leewayweb.bank.feature;

import com.leewayweb.bank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PrintStatementFeature {
    @Mock Console console;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(new Account(), new TransactionFactory(new Clock()));
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
