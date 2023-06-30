package com.tsa.injector.dataparser;

import com.tsa.injector.domain.*;

public interface ParserFactory {
    DataParser<UserDto> getUserParser();
    DataParser<MovieDto> getMovieParser();
    DataParser<ReviewDto> getReviewParser();
    DataParser<PosterDto> getPosterParser();
    DataParser<GenreDto> getGenreParser();
}
