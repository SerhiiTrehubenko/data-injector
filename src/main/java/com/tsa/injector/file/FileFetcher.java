package com.tsa.injector.file;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

public abstract class FileFetcher {

    protected final URI resource;

    public FileFetcher(String incomePathArg) {
        try {
            URL res = Thread.currentThread().getContextClassLoader().getResource(incomePathArg);
            if (Objects.nonNull(res)) {
                resource = res.toURI();
            } else {
                resource = Path.of(incomePathArg).toUri();
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<File> getFiles() {
        File file = new File(resource);
        File[] files = file.listFiles();
        if (Objects.nonNull(files) && files.length != 0) {
            Iterable<File> result = filterFiles(files);
            checkOnEmptyResult(result);
            return result;
        }
        throw new RuntimeException("Provided path [%s] does not contain files".formatted(resource.getPath()));
    }

    private void checkOnEmptyResult(Iterable<File> result) {
        if (!result.iterator().hasNext()) {
            throw new RuntimeException("There were not files according to your request");
        }
    }

    protected abstract Iterable<File> filterFiles(File[] files);
}
