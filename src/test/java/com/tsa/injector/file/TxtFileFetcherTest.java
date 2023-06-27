package com.tsa.injector.file;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TxtFileFetcherTest {
    private final String directoryWithFiles ="data";
    private final FileFetcher fileFetcher = new TxtFileFetcher(directoryWithFiles);

    @Test
    void shouldReturnListOfTxtFiles() {
        String expectedExtension = ".txt";

        Iterable<File> files = fileFetcher.getFiles();
        int numberOfElementsInIterable = 0;
        assertNotNull(files);
        for (File file : files) {
            assertTrue(file.getName().endsWith(expectedExtension));
            numberOfElementsInIterable++;
        }
        assertEquals(8, numberOfElementsInIterable);
    }

    @Test
    void shouldReturnEmptyList() {
        String directoryEmpty = "data2";
        FileFetcher fileFetcher = new TxtFileFetcher(directoryEmpty);
        assertThrows(RuntimeException.class, fileFetcher::getFiles);
    }
}
