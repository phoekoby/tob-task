package com.company.service;

public interface BusinessLogic<I,O> {
    O process(I input);
}
