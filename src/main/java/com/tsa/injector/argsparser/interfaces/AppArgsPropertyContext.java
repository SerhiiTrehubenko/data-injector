package com.tsa.injector.argsparser.interfaces;

import com.tsa.injector.domain.DefaultProperty;

import java.io.Serializable;

public interface AppArgsPropertyContext {
    Serializable getProperty(DefaultProperty defaultProperty);
    void setProperty(String tag, Serializable content);

    boolean isTagPresent(String tagFromArgs);
}
