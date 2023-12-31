package com.tsa.injector.domain;

import java.util.Objects;

public class PosterDto {
    private int movieId;
    private String movieRusName;
    private String posterLink;

    public boolean isFull() {
        return Objects.nonNull(movieRusName) &&
                Objects.nonNull(posterLink);
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieRusName() {
        return movieRusName;
    }

    public void setMovieRusName(String movieRusName) {
        this.movieRusName = movieRusName;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    @Override
    public String toString() {
        String asString =  "PosterDto{" +
                "movieRusName='" + movieRusName + '\'' +
                ", posterLink='" + posterLink + '\'' +
                '}';
        return asString.replaceAll("null", "[WRONG FORMAT]");
    }
}
