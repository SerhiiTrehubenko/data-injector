package com.tsa.injector.dataparser;

import com.tsa.injector.domain.PosterDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PosterParser extends DataParser<PosterDto> {

    private final static Pattern LINE_PATTERN = Pattern.compile("^\\b[\\p{L} '–:\\-+.,\\d]{3,100} +https?://[\\D\\d]*\\b$");
    private final static Pattern MOVIE_NAME = Pattern.compile("^\\b[\\p{L} '–:\\-+.,\\d]{3,100} +");
    private final static Pattern POSTER_LINK = Pattern.compile(" +https?://[\\D\\d]*\\b$");

    private PosterDto posterDto = new PosterDto();
    @Override
    protected void parseOne(String line) {
        resolvePoster(line);
        checkCycle();
    }

    private void checkCycle() {
        if (posterDto.isFull()) {
            dtos.add(posterDto);
            posterDto = new PosterDto();
        } else {
            throw new RuntimeException(posterDto.toString());
        }
    }

    private void resolvePoster(String line) {
        final Matcher matcherLine = LINE_PATTERN.matcher(line);

        if (matcherLine.matches()) {
            resolveMovieName(line);
            resolveLink(line);
        } else {
            throw  new RuntimeException("Data line: [%s] has wrong format".formatted(line));
        }
    }

    private void resolveMovieName(String line) {
        final Matcher matcherMovieName = MOVIE_NAME.matcher(line);
        matcherMovieName.find();
        posterDto.setMovieRusName(matcherMovieName.group());
    }

    private void resolveLink(String line) {
        final Matcher matcherLink = POSTER_LINK.matcher(line);
        matcherLink.find();
        posterDto.setPosterLink(matcherLink.group());
    }
}
