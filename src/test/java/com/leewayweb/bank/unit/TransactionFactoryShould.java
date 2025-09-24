package com.leewayweb.bank.unit;

import com.leewayweb.bank.Clock;
import com.leewayweb.bank.Transaction;
import com.leewayweb.bank.TransactionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionFactoryShould {

    private static final int AMOUNT = 500;
    public static final String TODAY = "09/25/2025";
    private TransactionFactory factory;
    private @Mock Clock clock;

    @BeforeEach
    void setUp() {
        this.factory = new TransactionFactory(clock);
    }

    @Test
    public void buildTransactions() {
        Transaction aTransaction = new Transaction(AMOUNT, TODAY);
        when(clock.date()).thenReturn(TODAY);

        assertEquals(aTransaction, factory.buildTransaction(AMOUNT));
    }
}