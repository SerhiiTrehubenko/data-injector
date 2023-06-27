package com.tsa.injector.argsparser.interfaces;

public interface ArgProperty {
    void interpret(AppArgsPropertyContext context);

    void setNextNode(ArgProperty nextNode);
}
