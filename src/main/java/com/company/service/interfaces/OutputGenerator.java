package com.company.service.interfaces;

import com.company.dto.OutputDto;

public interface OutputGenerator<O> {
    O generateString(OutputDto outputDto);
}
