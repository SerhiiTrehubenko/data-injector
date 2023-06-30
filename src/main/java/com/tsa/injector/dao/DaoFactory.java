package com.tsa.injector.dao;

import com.tsa.injector.domain.*;

public interface DaoFactory {
    BaseDao<UserDto> userDao();
    BaseDao<GenreDto> genreDao();
    BaseDao<MovieDto> movieDao();
    BaseDao<PosterDto> posterDao();
    BaseDao<ReviewDto> reviewDao();
    BaseDao<String> countryDao();
    BaseDao<RatingDto> ratingDao();
    BaseDao<TwoNumberHolder> movieCountryDao();
    BaseDao<TwoNumberHolder> movieGenreDao();
}
