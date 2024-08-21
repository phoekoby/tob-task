package com.company.service.interfaces;

import com.company.dto.InputDto;

public interface InputParser<I> {
    InputDto parse(I input);
}
