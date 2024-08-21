package com.company.service.readers;

import com.company.dto.InputDto;
import com.company.dto.OutputDto;
import com.company.service.InputStringParser;
import com.company.service.OutputStringGenerator;
import com.company.service.RequestService;
import com.company.service.interfaces.BusinessLogic;
import com.company.service.interfaces.InputParser;
import com.company.service.interfaces.InputReader;
import com.company.service.interfaces.OutputGenerator;

import java.util.Scanner;

public class InputReaderRealtime implements InputReader {
    private final Scanner scanner = new Scanner(System.in);
    private final InputParser<String> inputStringParser = new InputStringParser();
    private final BusinessLogic<InputDto, OutputDto> requestService = new RequestService();
    private final OutputGenerator<String> outputStringGenerator = new OutputStringGenerator();
    private static final String EXIT_STRING = "exit";
    public void read() {
        while (true) {
            String inputString = scanner.next();
            if(EXIT_STRING.equals(inputString)){
                break;
            }
            InputDto parse = inputStringParser.parse(inputString);
            OutputDto logic = requestService.process(parse);
            if(logic != null){
                String s = outputStringGenerator.generateString(logic);
                System.out.println(s);
            }
        }
    }
}
