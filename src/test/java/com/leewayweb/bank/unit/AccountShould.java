package com.leewayweb.bank.unit;

import com.leewayweb.bank.Account;
import com.leewayweb.bank.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountShould {
    private static final Transaction A_TRANSACTION = new Transaction(90, "07/04/2023");
    private Account account;

    @BeforeEach
    public void setUp()
    {
        this.account = new Account();
    }

    @Test
    public void keepTrackOfItsTransactions() {
        account.addTransaction(A_TRANSACTION);
        List transactions = account.transactions();

        assertEquals(1, transactions.size());
        assertEquals(A_TRANSACTION, transactions.getFirst());
    }

    @Test
    public void startWithNoTransactions() {
        assertEquals(0, account.transactions().size());
    }
}