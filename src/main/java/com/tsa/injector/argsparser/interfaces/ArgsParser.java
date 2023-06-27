package com.tsa.injector.argsparser.interfaces;

public interface ArgsParser {
    AppArgsPropertyContext getContext();
    void parse(String... incomeArgs);
}
