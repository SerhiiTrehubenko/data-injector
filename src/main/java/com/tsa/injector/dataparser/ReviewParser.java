package com.tsa.injector.dataparser;

import com.tsa.injector.domain.ReviewDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewParser extends CyclicDataParser<ReviewDto> {

    private final static int USER_FIRSTNAME = 0;
    private final static int USER_LASTNAME = 1;
    private final static Pattern MOVIE_NAME = Pattern.compile("^[\\p{L} '–:\\-+.,\\d]{3,100}$");
    private final static Pattern USER_NAME = Pattern.compile("^\\p{L}{3,50} +\\p{L}{3,50}$");
    private final static Pattern COMMENT = Pattern.compile("^.*\\b\\p{L}+\\b.*[.]*$");

    private ReviewDto reviewDto = new ReviewDto();

    public ReviewParser() {
        methods.add(this::resolveMovieName);
        methods.add(this::resolveUserName);
        methods.add(this::resolveComment);
        dataFieldsNumber = methods.size();
    }

    protected void checkCycle() {
        if (stepsNumber % dataFieldsNumber == 0) {
            if (reviewDto.isFull()) {
                dtos.add(reviewDto);
                reviewDto = new ReviewDto();
            } else {
                throw new RuntimeException(reviewDto.toString());
            }
        }
    }

    private void resolveMovieName(String line) {
        Matcher matcherMovieName = MOVIE_NAME.matcher(line);
        if (matcherMovieName.matches()) {
            reviewDto.setMovieRusName(line);
        } else {
            throw new RuntimeException("Movie name: [%s] has wrong format".formatted(line));
        }
    }
    private void resolveUserName(String line) {
        Matcher matcherMovieName = USER_NAME.matcher(line);
        if (matcherMovieName.matches()) {
            String[] names = line.split(" ");
            reviewDto.setUserFirstName(names[USER_FIRSTNAME].trim());
            reviewDto.setUserLastName(names[USER_LASTNAME].trim());
        } else {
            throw new RuntimeException("User name: [%s] has wrong format".formatted(line));
        }
    }
    private void resolveComment(String line) {
        Matcher matcherMovieName = COMMENT.matcher(line);
        if (matcherMovieName.matches()) {
            reviewDto.setComment(line);
        } else {
            throw new RuntimeException("Comment: [%s] has wrong format".formatted(line));
        }
    }
}
