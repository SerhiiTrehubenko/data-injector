package com.tsa.injector.dataparser;

import com.tsa.injector.domain.*;

public class ParserFactoryImpl implements ParserFactory {
    @Override
    public DataParser<UserDto> getUserParser() {
        return new UserParser();
    }

    @Override
    public DataParser<MovieDto> getMovieParser() {
        return new MovieParser();
    }

    @Override
    public DataParser<ReviewDto> getReviewParser() {
        return new ReviewParser();
    }

    @Override
    public DataParser<PosterDto> getPosterParser() {
        return new PosterParser();
    }

    @Override
    public DataParser<GenreDto> getGenreParser() {
        return new GenreParser();
    }
}
