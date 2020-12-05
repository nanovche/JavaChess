package com.chess.engine.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {

    BufferedReader bufferedReader;

    public String readConsoleInput() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
//        bufferedReader.close();
        return input;
    }

}
