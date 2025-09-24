package com.leewayweb.bank.unit;

import com.leewayweb.bank.Account;
import com.leewayweb.bank.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountShould {
    private Account account;

    public static Stream<Arguments> transactionProvider() {
        return Stream.of(
                Arguments.of(
                        new Transaction(90, "07/04/2023"),
                        new Transaction(-10, "08/24/2021")
                )
        );
    }

    @BeforeEach
    public void setUp() {
        this.account = new Account();
    }

    @ParameterizedTest
    @MethodSource("transactionProvider")
    public void keepTrackOfItsTransactions(Transaction transaction) {
        account.addTransaction(transaction);
        List<Transaction> transactions = account.transactions();

        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.getFirst());
    }

    @Test
    public void startWithNoTransactions() {
        assertEquals(0, account.transactions().size());
    }
}