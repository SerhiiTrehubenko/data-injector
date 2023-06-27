package com.tsa.injector.domain;

@FunctionalInterface
public interface ResolveConsumer<T> {
    void resolve(T t);
}
