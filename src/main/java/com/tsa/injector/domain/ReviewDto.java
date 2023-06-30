package com.tsa.injector.domain;

import java.util.Objects;

public class ReviewDto {
    private int movieId;
    private int userId;
    private String movieRusName;
    private String userFirstName;
    private String userLastName;
    private String comment;

    public boolean isFull() {
        return Objects.nonNull(movieRusName) &&
                Objects.nonNull(userFirstName) &&
                Objects.nonNull(userLastName) &&
                Objects.nonNull(comment);
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMovieRusName() {
        return movieRusName;
    }

    public void setMovieRusName(String movieRusName) {
        this.movieRusName = movieRusName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
