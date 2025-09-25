package com.leewayweb.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void printLine(String s) {
        System.out.println(s);
    }

    public String readLine() throws IOException {

        return reader.readLine();
    }
}
