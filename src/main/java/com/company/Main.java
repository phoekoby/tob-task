package com.company;

import com.company.service.interfaces.InputReader;
import com.company.service.readers.InputReaderRealtime;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReaderRealtime();
        inputReader.read();
    }
}