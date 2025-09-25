package com.leewayweb.bank.unit;

import com.leewayweb.bank.Clock;
import com.leewayweb.bank.Transaction;
import com.leewayweb.bank.TransactionFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionFactoryShould {

    private @Mock Clock clock;

    @InjectMocks
    private TransactionFactory factory;

    @ParameterizedTest
    @CsvSource({
            "10/05/2019,500",
            "11/06/2019,400",
    })
    public void createDeposits(String date, int amount) {
        when(clock.date()).thenReturn(date);
        assertEquals(new Transaction(amount, date), factory.deposit(amount));
    }

    @ParameterizedTest
    @CsvSource({
            "10/05/2019,500",
            "11/06/2019,400",
    })
    public void createWithdrawals(String date, int amount) {
        when(clock.date()).thenReturn(date);
        assertEquals(new Transaction(-amount, date), factory.withdrawal(amount));
    }
}