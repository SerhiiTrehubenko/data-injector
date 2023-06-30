package com.tsa.injector.service;

import com.tsa.injector.dao.BaseDao;
import com.tsa.injector.dao.GenreDao;
import com.tsa.injector.domain.GenreDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenreServiceITest extends BaseServiceTest {
    @Test
    void shouldInsertGenre() {
        GenreDto genreDto = new GenreDto("боевик");

        List<GenreDto> genreDtos = List.of(genreDto);

        BaseDao<GenreDto> genreDao = new GenreDao(dbConnector);
        BaseService<GenreDto> genreService = new GenreService(genreDao);

        boolean result = genreService.insert(genreDtos);

        assertTrue(result);
    }
}
