package com.tsa.injector.dataparser;

import com.tsa.injector.domain.GenreDto;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GenreParserTest {
    @Test
    void shouldReturnListOfGenresDto() throws Exception {
        final URL resource = Thread.currentThread().getContextClassLoader().getResource("data/genre.txt");
        final File file = new File(resource.toURI());

        DataParser<GenreDto> genreParser = new GenreParser();

        List<GenreDto> genreDtos = genreParser.parse(file);

        assertNotNull(genreDtos);
        assertEquals(15, genreDtos.size());
    }
}
