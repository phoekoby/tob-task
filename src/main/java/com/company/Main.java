package com.company;

import com.company.service.InputReader;
import com.company.service.impl.readers.InputReaderRealtime;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReaderRealtime();
        inputReader.read();
    }
}