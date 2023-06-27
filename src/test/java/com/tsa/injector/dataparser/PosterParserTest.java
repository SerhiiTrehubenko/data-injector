package com.tsa.injector.dataparser;

import com.tsa.injector.domain.PosterDto;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PosterParserTest {
    @Test
    void name() throws URISyntaxException {
        final URL resource = Thread.currentThread().getContextClassLoader().getResource("data/poster.txt");
        final File file = new File(resource.toURI());

        DataParser<PosterDto> posterParser = new PosterParser();

        List<PosterDto> posterDtos = posterParser.parse(file);

        assertNotNull(posterDtos);
        assertEquals(25, posterDtos.size());
    }
}
