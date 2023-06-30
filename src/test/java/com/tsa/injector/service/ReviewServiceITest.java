package com.tsa.injector.service;

import com.tsa.injector.dao.BaseDao;
import com.tsa.injector.dao.MovieDao;
import com.tsa.injector.dao.ReviewDao;
import com.tsa.injector.dao.UserDao;
import com.tsa.injector.domain.MovieDto;
import com.tsa.injector.domain.ReviewDto;
import com.tsa.injector.domain.UserDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviewServiceITest extends BaseServiceTest{
    @Test
    void shouldInsertPosterToDb() {
        String userFirstName = "Serhii";
        String userLastName = "Trehubenko";
        String movieRusName = "Матрица";

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUserFirstName(userFirstName);
        reviewDto.setUserLastName(userLastName);
        reviewDto.setMovieRusName(movieRusName);
        reviewDto.setComment("Comment");

        List<ReviewDto> reviewDtos = List.of(reviewDto);

        BaseDao<ReviewDto> reviewDao = new ReviewDao(dbConnector);
        BaseDao<MovieDto> movieDao = new MovieDao(dbConnector);
        BaseDao<UserDto> userDao = new UserDao(dbConnector);

        BaseService<ReviewDto> reviewService = new ReviewService(reviewDao, movieDao, userDao);

        boolean result = reviewService.insert(reviewDtos);

        assertTrue(result);
    }
}
