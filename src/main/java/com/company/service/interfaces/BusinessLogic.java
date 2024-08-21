package com.company.service.interfaces;

public interface BusinessLogic<I,O> {
    O process(I input);
}
