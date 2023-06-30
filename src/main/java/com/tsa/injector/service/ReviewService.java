package com.tsa.injector.service;

import com.tsa.injector.dao.BaseDao;
import com.tsa.injector.domain.MovieDto;
import com.tsa.injector.domain.ReviewDto;
import com.tsa.injector.domain.UserDto;

import java.util.List;

public class ReviewService implements BaseService<ReviewDto> {
    private final BaseDao<ReviewDto> reviewDao;
    private final BaseDao<MovieDto> movieDao;
    private final BaseDao<UserDto> userDao;

    public ReviewService(BaseDao<ReviewDto> reviewDao,
                         BaseDao<MovieDto> movieDao,
                         BaseDao<UserDto> userDao) {
        this.reviewDao = reviewDao;
        this.movieDao = movieDao;
        this.userDao = userDao;
    }

    @Override
    public boolean insert(List<ReviewDto> reviewDtos) {
        for (ReviewDto reviewDto : reviewDtos) {
            reviewDto.setMovieId(movieDao.findIdBy(reviewDto.getMovieRusName()));
            reviewDto.setUserId(userDao.findIdBy(reviewDto.getUserFirstName(), reviewDto.getUserLastName()));
            if (reviewDao.notPresent(reviewDto)) {
                reviewDao.insert(reviewDto);
            }
        }
        return true;
    }
}
