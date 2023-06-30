package com.tsa.injector.service;

import com.tsa.injector.dao.BaseDao;
import com.tsa.injector.domain.MovieDto;
import com.tsa.injector.domain.PosterDto;

import java.util.List;

public class PosterService implements BaseService<PosterDto> {
    private final BaseDao<PosterDto> posterDao;
    private final BaseDao<MovieDto> movieDao;

    public PosterService(BaseDao<PosterDto> posterDao, BaseDao<MovieDto> movieDao) {
        this.posterDao = posterDao;
        this.movieDao = movieDao;
    }

    @Override
    public boolean insert(List<PosterDto> posterDtos) {
        for (PosterDto posterDto : posterDtos) {
            PosterDto posterWithMovieId = setMovieId(posterDto);
            if (posterDao.notPresent(posterDto)) {
                posterDao.insert(posterWithMovieId);
            }

        }
        return true;
    }

    private PosterDto setMovieId(PosterDto posterDto) {
        int movieId = movieDao.findIdBy(posterDto.getMovieRusName());
        posterDto.setMovieId(movieId);
        return posterDto;
    }
}
