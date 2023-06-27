package com.tsa.injector.dataparser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public abstract class DataParser<T> {
    protected final List<T> dtos = new ArrayList<>();
    public List<T> parse(File data) {
        try {
            List<String> lines = Files.readAllLines(data.toPath());

            for (String line : lines) {
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    parseOne(trimmedLine);
                }
            }

            return dtos;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void parseOne(String line);
}
