package com.leewayweb.bank.feature;

import com.leewayweb.bank.AccountService;
import com.leewayweb.bank.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PrintStatementFeature {
    @Mock Console console;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService();
    }

    @Test
    public void print_statement_containing_all_transactions() {

        accountService.deposit(1000);
        accountService.withdraw(100);
        accountService.deposit(500);

        accountService.printStatement();

        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("10/04/2020 | 500.00 | 1400.00");
        verify(console).printLine("02/04/2020 | -100.00 | 900.00");
        verify(console).printLine("01/04/2020 | 1000.00 | 1000.00");

        assertTrue(true);
    }
}
