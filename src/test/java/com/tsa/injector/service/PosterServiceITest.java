package com.tsa.injector.service;

import com.tsa.injector.dao.BaseDao;
import com.tsa.injector.dao.MovieDao;
import com.tsa.injector.dao.PosterDao;
import com.tsa.injector.domain.MovieDto;
import com.tsa.injector.domain.PosterDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PosterServiceITest extends BaseServiceTest {
    @Test
    void shouldInsertPosterToDB() {
        PosterDto posterDto = new PosterDto();
        posterDto.setMovieRusName("Матрица");
        posterDto.setPosterLink("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");

        List<PosterDto> posterDtos = List.of(posterDto);

        BaseDao<PosterDto> posterDao = new PosterDao(dbConnector);
        BaseDao<MovieDto> movieDao = mock(MovieDao.class);

        when(movieDao.findIdBy("Матрица")).thenReturn(1104);

        BaseService<PosterDto> posterService = new PosterService(posterDao, movieDao);

        boolean result = posterService.insert(posterDtos);

        assertTrue(result);
    }
}
