package com.tsa.injector.argsparser.impl;

import com.tsa.injector.argsparser.interfaces.ArgsParser;
import com.tsa.injector.argsparser.interfaces.AppArgsPropertyContext;
import com.tsa.injector.argsparser.interfaces.ArgProperty;
import com.tsa.injector.domain.DefaultProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class DefaultArgsParser implements ArgsParser {

    private final static int EMPTY_ARGS = 0;
    private final AppArgsPropertyContext context;

    private ArgProperty argPropertyNode;

    private ListIterator<String> argsIterator;

    public DefaultArgsParser(AppArgsPropertyContext context) {
        this.context = context;
    }

    @Override
    public void parse(String... incomeArgs) {
        if (nonEmpty(incomeArgs)) {
            setArgsIterator(incomeArgs);
            buildInterpretTree();
            updateContext();
            deleteInterpretTree();
        } else {
            throw new RuntimeException("You should provide tag: [-data] with the path to folder with data files");
        }
    }

    private boolean nonEmpty(String... incomeArgs) {
        return incomeArgs.length != EMPTY_ARGS;
    }

    private void setArgsIterator(String... incomeArgs) {
        List<String> splitArgs = Arrays.asList(incomeArgs);
        argsIterator = splitArgs.listIterator();
    }

    private void buildInterpretTree() {
        while (argsIterator.hasNext()) {
            createNode();
        }
    }

    private void createNode() {
        ArgProperty currentNode = resolveNode();
        chainNode(currentNode);
    }

    private ArgProperty resolveNode() {
        DefaultProperty foundTag = resolveIncomeTag();
        Serializable argValue = getPropertyOfFoundTag(foundTag);
        return new ArgPropertyNode(foundTag.getTag(), argValue);
    }

    private DefaultProperty resolveIncomeTag() {
        String incomeTag = argsIterator.next();
        
        if (context.isTagPresent(incomeTag)) {
            return DefaultProperty.getDefaultProperty(incomeTag);
        }
        throw new RuntimeException("The Provided tad: [%s] does not comply to incomeTag scheme".formatted(incomeTag));
    }

    private Serializable getPropertyOfFoundTag(DefaultProperty foundTag) {
        try {
            return foundTag.getProperty(argsIterator.next());
        } catch (Exception e) {
            throw new RuntimeException("Provided arg tag: [%s] does not have a value".formatted(foundTag.getTag()));
        }
    }

    private void chainNode(ArgProperty currentNode) {
        currentNode.setNextNode(argPropertyNode);
        argPropertyNode = currentNode;
    }

    private void updateContext() {
        argPropertyNode.interpret(context);
    }

    private void deleteInterpretTree() {
        argPropertyNode = null;
    }

    @Override
    public AppArgsPropertyContext getContext() {
        return context;
    }
}
