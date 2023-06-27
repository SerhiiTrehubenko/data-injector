package com.tsa.injector.file;

import org.junit.jupiter.api.Test;


import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileHolderITest {
    @Test
    void shouldConvertIterableOfFilesIntoMapOfStringAndFile() {
        FileFetcher fileFetcher = new TxtFileFetcher("data");

        FileHolder fileHolder = new FileHolderImpl(fileFetcher.getFiles());

        assertEquals(8, fileHolder.size());
    }

    @Test
    void shouldContainAskedFiles() {
        FileFetcher fileFetcher = new TxtFileFetcher("data");

        FileHolder fileHolder = new FileHolderImpl(fileFetcher.getFiles());

        File genreFile = fileHolder.getFile("genre.txt");
        assertEquals("genre.txt", genreFile.getName());

        File movieFile = fileHolder.getFile("movie.txt");
        assertEquals("movie.txt", movieFile.getName());

        File posterFile = fileHolder.getFile("poster.txt");
        assertEquals("poster.txt", posterFile.getName());

        File reviewFile = fileHolder.getFile("review.txt");
        assertEquals("review.txt", reviewFile.getName());

        File userFile = fileHolder.getFile("user.txt");
        assertEquals("user.txt", userFile.getName());
    }
}
