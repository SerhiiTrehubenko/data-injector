package com.tsa.injector.dataparser;

import com.tsa.injector.domain.GenreDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenreParser extends DataParser<GenreDto> {

    private final static Pattern GENRE_NAME = Pattern.compile("^\\p{L}{3,50}$");

    protected void parseOne(String line) {
        dtos.add(resolveEmail(line));
    }

    private GenreDto resolveEmail(String trimmedLine) {
        Matcher matcherGenreName = GENRE_NAME.matcher(trimmedLine);
        if (matcherGenreName.matches()) {
            return new GenreDto(trimmedLine);
        } else {
            throw new RuntimeException("Genre name: [%s] has wrong format".formatted(trimmedLine));
        }
    }
}
