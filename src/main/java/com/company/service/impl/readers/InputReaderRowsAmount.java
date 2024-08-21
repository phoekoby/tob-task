package com.company.service.impl.readers;

import com.company.dto.InputDto;
import com.company.dto.OutputDto;
import com.company.service.impl.InputStringParser;
import com.company.service.impl.OutputStringGenerator;
import com.company.service.impl.RequestService;
import com.company.service.BusinessLogic;
import com.company.service.InputParser;
import com.company.service.InputReader;
import com.company.service.OutputGenerator;

import java.util.Scanner;

public class InputReaderRowsAmount implements InputReader {
    private final Scanner scanner = new Scanner(System.in);
    private final InputParser<String> inputStringParser = new InputStringParser();
    private final BusinessLogic<InputDto, OutputDto> requestService = new RequestService();
    private final OutputGenerator<String> outputStringGenerator = new OutputStringGenerator();

    public void read() {
        int amountOfRows = scanner.nextInt();
        for (int i = 0; i < amountOfRows; i++) {

            String inputString = scanner.next();
            InputDto parse = inputStringParser.parse(inputString);
            OutputDto logic = requestService.process(parse);
            if (logic != null) {
                String s = outputStringGenerator.generateString(logic);
                System.out.println(s);
            }
        }
    }
}
