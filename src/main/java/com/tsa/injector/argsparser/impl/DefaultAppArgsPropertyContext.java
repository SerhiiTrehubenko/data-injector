package com.tsa.injector.argsparser.impl;

import com.tsa.injector.argsparser.interfaces.AppArgsPropertyContext;
import com.tsa.injector.domain.DefaultProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultAppArgsPropertyContext implements AppArgsPropertyContext {

    private final Map<String, Serializable> properties;

    public DefaultAppArgsPropertyContext(DefaultProperty... defaultProperty) {
        properties = Arrays.stream(defaultProperty)
                .collect(Collectors.toMap(DefaultProperty::getTag, DefaultProperty::getProperty));
    }

    @Override
    public Serializable getProperty(DefaultProperty defaultProperty) {
        return properties.get(defaultProperty.getTag());
    }

    @Override
    public void setProperty(String tag, Serializable content) {
            properties.put(tag, content);
    }

    @Override
    public boolean isTagPresent(String tagFromArgs) {
        return properties.containsKey(tagFromArgs);
    }
}
