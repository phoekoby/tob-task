package com.company.service;

import com.company.dto.InputDto;
import com.company.service.interfaces.InputParser;

public class InputStringParser implements InputParser<String> {
    public InputDto parse(String input) {
        String[] split = input.split(";");
        if(split.length != 8) {
            throw new IllegalArgumentException("Передана строка неверного формата");
        }

        return InputDto.builder()
                .userId(split[0])
                .clorderId(split[1])
                .action(split[2].charAt(0))
                .instrumentId(Integer.parseInt(split[3]))
                .side(split[4].charAt(0))
                .price(Long.parseLong(split[5]))
                .amount(Integer.parseInt(split[6]))
                .amountRest(Integer.parseInt(split[7]))
                .build();
    }
}
