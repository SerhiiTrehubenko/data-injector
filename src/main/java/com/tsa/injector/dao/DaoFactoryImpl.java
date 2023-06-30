package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;
import com.tsa.injector.domain.*;

public class DaoFactoryImpl implements DaoFactory {

    private final static String COUNTRY_JOIN_TABLE = "country";
    private final static String GENRE_JOIN_TABLE = "genre";
    private final DbConnector connector;

    public DaoFactoryImpl(DbConnector connector) {
        this.connector = connector;
    }

    @Override
    public BaseDao<UserDto> userDao() {
        return new UserDao(connector);
    }

    @Override
    public BaseDao<GenreDto> genreDao() {
        return new GenreDao(connector);
    }

    @Override
    public BaseDao<MovieDto> movieDao() {
        return new MovieDao(connector);
    }

    @Override
    public BaseDao<PosterDto> posterDao() {
        return new PosterDao(connector);
    }

    @Override
    public BaseDao<ReviewDto> reviewDao() {
        return new ReviewDao(connector);
    }

    @Override
    public BaseDao<String> countryDao() {
        return new CountryDao(connector);
    }

    @Override
    public BaseDao<RatingDto> ratingDao() {
        return new RatingDao(connector);
    }

    @Override
    public BaseDao<TwoNumberHolder> movieCountryDao() {
        return new MovieJoinTableDao(connector, COUNTRY_JOIN_TABLE);
    }

    @Override
    public BaseDao<TwoNumberHolder> movieGenreDao() {
        return new MovieJoinTableDao(connector, GENRE_JOIN_TABLE);
    }
}
