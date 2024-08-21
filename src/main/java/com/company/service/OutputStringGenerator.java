package com.company.service;

import com.company.dto.OutputDto;
import com.company.service.interfaces.OutputGenerator;

public class OutputStringGenerator implements OutputGenerator<String> {
    public String generateString(OutputDto outputDto) {
        return outputDto.getInstrumentId() +
                ";" +
                outputDto.getSide() +
                ";" +
                outputDto.getPrice() +
                ";" +
                outputDto.getAmount();
    }
}
