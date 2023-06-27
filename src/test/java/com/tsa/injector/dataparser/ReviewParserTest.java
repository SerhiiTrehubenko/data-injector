package com.tsa.injector.dataparser;

import com.tsa.injector.domain.ReviewDto;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReviewParserTest {
    @Test
    void shouldReturnListOfReviewDto() throws URISyntaxException {
        DataParser<ReviewDto> reviewParser = new ReviewParser();

        final URL resource = Thread.currentThread().getContextClassLoader().getResource("data/review.txt");
        final File file = new File(resource.toURI());

        List<ReviewDto> reviewDtos = reviewParser.parse(file);

        assertNotNull(reviewDtos);
        assertEquals(32, reviewDtos.size());
    }
}
