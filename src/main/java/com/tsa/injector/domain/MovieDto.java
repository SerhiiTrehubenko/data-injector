package com.tsa.injector.domain;

import java.util.List;
import java.util.Objects;

public class MovieDto {
    private String rusName;
    private String engName;
    private Integer releaseYear;
    private List<String> countries;
    private List<GenreDto> genres;
    private String description;
    private Double rating;
    private Double price;

    public boolean isFull() {
        return Objects.nonNull(rusName) &&
                Objects.nonNull(engName) &&
                Objects.nonNull(releaseYear) &&
                Objects.nonNull(countries) &&
                Objects.nonNull(genres) &&
                Objects.nonNull(description) &&
                Objects.nonNull(rating) &&
                Objects.nonNull(price);
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String asString = "MovieDto{" +
                "rusName='" + rusName + '\'' +
                ", engName='" + engName + '\'' +
                ", country='" + countries + '\'' +
                ", genres=" + genres +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                '}';
        return asString.replaceAll("null", "[WRONG FORMAT]");
    }
}
