package com.tsa.injector.dataparser;

import com.tsa.injector.domain.MovieDto;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MovieParserTest {
    @Test
    void shouldReturnListOfMovieDto() throws URISyntaxException {
        DataParser<MovieDto> movieParser = new MovieParser();

        final URL resource = Thread.currentThread().getContextClassLoader().getResource("data/movie.txt");
        final File file = new File(resource.toURI());

        List<MovieDto> movieDtos = movieParser.parse(file);

        assertNotNull(movieDtos);
        assertEquals(25, movieDtos.size());
    }
}
