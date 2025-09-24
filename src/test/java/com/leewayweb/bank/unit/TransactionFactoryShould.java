package com.leewayweb.bank.unit;

import com.leewayweb.bank.Transaction;
import com.leewayweb.bank.TransactionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionFactoryShould {

    private static final int AMOUNT = 500;
    private TransactionFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = new TransactionFactory();
    }

    @Test
    public void buildTransactions() {

        Transaction aTransaction = new Transaction(AMOUNT);

        assertEquals(aTransaction, factory.buildTransaction(AMOUNT));
    }
}