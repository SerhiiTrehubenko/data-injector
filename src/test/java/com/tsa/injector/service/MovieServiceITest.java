package com.tsa.injector.service;

import com.tsa.injector.dao.*;
import com.tsa.injector.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieServiceITest extends  BaseServiceTest {
    @Test
    void shouldInsertMovie() {
        MovieDto movieDto = new MovieDto();
        movieDto.setRusName("Матрица");
        movieDto.setEngName("Matrix");
        movieDto.setReleaseYear(1994);
        movieDto.setCountries(List.of("USA", "Germany"));
        movieDto.setGenres(List.of(new GenreDto("comedy"), new GenreDto("thriller")));
        movieDto.setDescription("description");
        movieDto.setRating(8.2);
        movieDto.setPrice(125.25);

        List<MovieDto> movies = List.of(movieDto);

        BaseDao<RatingDto> ratingDao = new RatingDao(dbConnector);
        BaseDao<MovieDto> movieDao = new MovieDao(dbConnector);
        BaseDao<String> countryDao = new CountryDao(dbConnector);
        BaseDao<GenreDto> genreDao = new GenreDao(dbConnector);
        BaseDao<UserDto> userDao = new UserDao(dbConnector);
        BaseDao<TwoNumberHolder> movieCountryDao = new MovieJoinTableDao(dbConnector, "country");
        BaseDao<TwoNumberHolder> movieGenreDao = new MovieJoinTableDao(dbConnector, "genre");


        BaseService<MovieDto> movieService = new MovieService(movieDao, countryDao, genreDao, ratingDao, userDao, movieCountryDao, movieGenreDao);

        boolean result = movieService.insert(movies);

        assertTrue(result);
    }
}
