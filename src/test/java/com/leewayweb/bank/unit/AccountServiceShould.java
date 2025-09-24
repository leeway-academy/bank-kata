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
    private static final String DEPOSIT_DATE = "10/10/2021";
    private static final Transaction A_DEPOSIT = new Transaction(DEPOSIT_AMOUNT, DEPOSIT_DATE);
    private @Mock Account account;
    private AccountService accountService;
    private @Mock TransactionFactory transactionFactory;

    @BeforeEach
    public void setUp()
    {
        accountService = new AccountService(account, transactionFactory);
    }

    @Test
    public void shouldAllowDeposits() {
        when(transactionFactory.buildTransaction(DEPOSIT_AMOUNT))
                .thenReturn(A_DEPOSIT);

        accountService.deposit(DEPOSIT_AMOUNT);

        verify(account).addTransaction(eq(A_DEPOSIT));
    }
}