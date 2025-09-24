package com.leewayweb.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {
    public String date() {
        return
                LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")
                        );
    }
}
