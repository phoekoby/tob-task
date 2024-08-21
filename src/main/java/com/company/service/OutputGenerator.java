package com.company.service;

import com.company.dto.OutputDto;

public interface OutputGenerator<O> {
    O generateString(OutputDto outputDto);
}
