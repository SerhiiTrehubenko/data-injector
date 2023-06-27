package com.tsa.injector.argsparser.impl;

import com.tsa.injector.argsparser.interfaces.AppArgsPropertyContext;
import com.tsa.injector.argsparser.interfaces.ArgProperty;

import java.io.Serializable;
import java.util.Objects;

public class ArgPropertyNode implements ArgProperty {
    private final String tag;
    private final Serializable content;
    private ArgProperty nextNode;

    public ArgPropertyNode(String tag, Serializable content) {
        this.tag = tag;
        this.content = content;
    }

    @Override
    public void interpret(AppArgsPropertyContext content) {
        content.setProperty(tag, this.content);
        if (Objects.nonNull(nextNode)) {
            nextNode.interpret(content);
        }
    }

    @Override
    public void setNextNode(ArgProperty nextNode) {
        this.nextNode = nextNode;
    }
}
