package com.tsa.injector.dataparser;

import com.tsa.injector.domain.GenreDto;
import com.tsa.injector.domain.MovieDto;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieParser extends CyclicDataParser<MovieDto> {
    private final static int RUS_NAME = 0;
    private final static int ENG_NAME = 1;
    private final static Pattern MOVIE_NAMES = Pattern.compile("^[\\p{L} '–:\\-+.,\\d]{3,100}/[\\p{L} '–:\\-+.,\\d]{3,100}$");
    private final static Pattern RELEASE_YEAR = Pattern.compile("^\\d{4}$");
    private final static Pattern DESCRIPTION = Pattern.compile("^\\b\\p{L}+\\b.*[.]*$");
    private final static Pattern RATING_VALUE = Pattern.compile("\\d.\\d{1,2}$");
    private final static Pattern PRICE_VALUE = Pattern.compile("\\d{1,6}.\\d{2}$");

    private MovieDto movieDto = new MovieDto();

    public MovieParser() {
        methods.add(this::resolveMovieName);
        methods.add(this::resolveReleaseYear);
        methods.add(this::resolveCountry);
        methods.add(this::resolveGenre);
        methods.add(this::resolveDescription);
        methods.add(this::resolveRating);
        methods.add(this::resolvePrice);
        dataFieldsNumber = methods.size();
    }

    protected void checkCycle() {
        if (stepsNumber % dataFieldsNumber == 0) {
            if (movieDto.isFull()) {
                dtos.add(movieDto);
                movieDto = new MovieDto();
            } else {
                throw new RuntimeException(movieDto.toString());
            }
        }
    }

    private void resolveMovieName(String line) {
        Matcher matcherMovieName = MOVIE_NAMES.matcher(line);
        if (matcherMovieName.matches()) {
            String[] names = line.split("/");
            movieDto.setRusName(names[RUS_NAME].trim());
            movieDto.setEngName(names[ENG_NAME].trim());
        } else {
            throw new RuntimeException("Movie name: [%s] has wrong format".formatted(line));
        }
    }

    private void resolveReleaseYear(String line) {
        Matcher matcherReleaseYear = RELEASE_YEAR.matcher(line);
        if (matcherReleaseYear.matches()) {
            movieDto.setReleaseYear(Integer.parseInt(line));
        } else {
            throw new RuntimeException("Movie release year: [%s] has wrong format".formatted(line));
        }
    }

    private void resolveCountry(String line) {
        List<String> countries = Arrays.stream(line.split(","))
                .map(String::trim)
                .toList();
        movieDto.setCountries(countries);
    }

    private void resolveGenre(String line) {
        List<GenreDto> genres = Arrays.stream(line.split(","))
                .map(String::trim)
                .map(GenreDto::new)
                .toList();
        movieDto.setGenres(genres);
    }

    private void resolveDescription(String line) {
        Matcher matcherDescription = DESCRIPTION.matcher(line);
        if (matcherDescription.matches()) {
            movieDto.setDescription(line);
        } else {
            throw new RuntimeException("Movie description: [%s] has wrong format".formatted(line));
        }
    }

    private void resolveRating(String line) {
        Matcher matcherRating = RATING_VALUE.matcher(line);
        if (!matcherRating.find()) {
            throw new RuntimeException("Movie rating: [%s] has wrong format".formatted(line));
        }
        movieDto.setRating(Double.parseDouble(matcherRating.group()));
    }

    private void resolvePrice(String line) {
        Matcher matcherPrice = PRICE_VALUE.matcher(line);
        if (!matcherPrice.find()) {
            throw new RuntimeException("Movie price: [%s] has wrong format".formatted(line));
        }
        movieDto.setPrice(Double.parseDouble(matcherPrice.group()));
    }
}
