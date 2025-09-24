package com.leewayweb.bank.unit;

import com.leewayweb.bank.Account;
import com.leewayweb.bank.AccountService;
import com.leewayweb.bank.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceShould {
    public static final int DEPOSIT_AMOUNT = 400;
    private @Mock Account account;
    private AccountService accountService;

    @BeforeEach
    public void setUp()
    {
        accountService = new AccountService(account);
    }

    @Test
    public void shouldAllowDeposits() {
        Transaction aDeposit = new Transaction(DEPOSIT_AMOUNT);

        accountService.deposit(DEPOSIT_AMOUNT);

        verify(account).addTransaction(eq(aDeposit));
    }
}