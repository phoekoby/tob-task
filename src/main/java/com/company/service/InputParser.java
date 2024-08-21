package com.company.service;

import com.company.dto.InputDto;

public interface InputParser<I> {
    InputDto parse(I input);
}
