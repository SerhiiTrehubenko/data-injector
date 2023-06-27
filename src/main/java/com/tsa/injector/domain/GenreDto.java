package com.tsa.injector.domain;

public class GenreDto {
    private final String name;

    public GenreDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GenreDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
