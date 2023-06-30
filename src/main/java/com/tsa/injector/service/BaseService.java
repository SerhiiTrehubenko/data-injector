package com.tsa.injector.service;

import java.util.List;

public interface BaseService<T> {
    boolean insert(List<T> dtos);
}
