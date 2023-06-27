package com.tsa.injector.dataparser;

import com.tsa.injector.domain.ResolveConsumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CyclicDataParser<T> extends DataParser<T> {
    private final static int CURRENT_METHOD = 0;
    protected int dataFieldsNumber;
    protected int stepsNumber = 0;

    protected final List<ResolveConsumer<String>> methods = new ArrayList<>();

    @Override
    protected void parseOne(String line) {
        stepsNumber++;
        process(line);
        checkCycle();
    }

    private void process(String line) {
        ResolveConsumer<String> dataResolvingMethod = methods.get(CURRENT_METHOD);
        dataResolvingMethod.resolve(line);
        adjustMethod();
    }

    private void adjustMethod() {
        Collections.rotate(methods, -1);
    }

    protected abstract void checkCycle();
}
