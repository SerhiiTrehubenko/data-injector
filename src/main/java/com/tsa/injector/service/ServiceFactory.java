package com.tsa.injector.service;

import com.tsa.injector.domain.*;

public interface ServiceFactory {
    BaseService<UserDto> userService();
    BaseService<GenreDto> genreService();
    BaseService<MovieDto> movieService();
    BaseService<ReviewDto> reviewService();
    BaseService<PosterDto> posterService();
}
