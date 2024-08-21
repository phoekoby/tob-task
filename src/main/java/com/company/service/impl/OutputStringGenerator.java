package com.company.service.impl;

import com.company.dto.OutputDto;
import com.company.service.OutputGenerator;

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
