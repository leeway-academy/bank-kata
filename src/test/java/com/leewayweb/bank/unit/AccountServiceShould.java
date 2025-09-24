package com.leewayweb.bank.unit;

import com.leewayweb.bank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceShould {
    public static final int DEPOSIT_AMOUNT = 400;
    public static final int WITHDRAWAL_AMOUNT = 300;

    private static final String DEPOSIT_DATE = "10/10/2021";
    private static final String WITHDRAWAL_DATE = "11/10/2021";

    private static final Transaction A_DEPOSIT = new Transaction(DEPOSIT_AMOUNT, DEPOSIT_DATE);
    private static final Transaction A_WITHDRAWAL = new Transaction(WITHDRAWAL_AMOUNT * -1, WITHDRAWAL_DATE);

    private @Mock Account account;
    private AccountService accountService;
    private @Mock TransactionFactory transactionFactory;
    private @Mock StatementPrinter statementPrinter;

    @BeforeEach
    public void setUp() {
        accountService = new AccountService(account, transactionFactory, statementPrinter);
    }

    @Test
    public void shouldAllowDeposits() {
        when(transactionFactory.buildTransaction(DEPOSIT_AMOUNT))
                .thenReturn(A_DEPOSIT);

        accountService.deposit(DEPOSIT_AMOUNT);

        verify(account).addTransaction(eq(A_DEPOSIT));
    }

    @Test
    public void shouldAllowWithdrawals() {
        when(transactionFactory.buildTransaction(WITHDRAWAL_AMOUNT * -1))
                .thenReturn(A_WITHDRAWAL);

        accountService.withdraw(WITHDRAWAL_AMOUNT);

        verify(account).addTransaction(eq(A_WITHDRAWAL));
    }

    @Test
    public void printStatementToConsole() {
        accountService.printStatement();
        verify(statementPrinter).print(account.transactions());
    }
}