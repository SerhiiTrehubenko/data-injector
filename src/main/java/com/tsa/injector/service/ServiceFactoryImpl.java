package com.tsa.injector.service;

import com.tsa.injector.dao.DaoFactory;
import com.tsa.injector.domain.*;

public class ServiceFactoryImpl implements ServiceFactory {
    private final DaoFactory daoFactory;

    public ServiceFactoryImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public BaseService<UserDto> userService() {
        return new UserService(daoFactory.userDao());
    }

    @Override
    public BaseService<GenreDto> genreService() {
        return new GenreService(daoFactory.genreDao());
    }

    @Override
    public BaseService<MovieDto> movieService() {
        return new MovieService(daoFactory.movieDao(), daoFactory.countryDao(), daoFactory.genreDao(), daoFactory.ratingDao(), daoFactory.userDao(), daoFactory.movieCountryDao(), daoFactory.movieGenreDao());
    }

    @Override
    public BaseService<ReviewDto> reviewService() {
        return new ReviewService(daoFactory.reviewDao(), daoFactory.movieDao(), daoFactory.userDao());
    }

    @Override
    public BaseService<PosterDto> posterService() {
        return new PosterService(daoFactory.posterDao(), daoFactory.movieDao());
    }
}
