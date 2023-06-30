package com.tsa.injector.service;

import com.tsa.injector.dao.BaseDao;
import com.tsa.injector.domain.GenreDto;

import java.util.List;

public class GenreService implements BaseService<GenreDto> {
    private final BaseDao<GenreDto> genreDao;

    public GenreService(BaseDao<GenreDto> genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public boolean insert(List<GenreDto> genreDtos) {
        genreDtos.stream()
                .filter(genreDao::notPresent)
                .forEach(genreDao::insert);
        return true;
    }
}
