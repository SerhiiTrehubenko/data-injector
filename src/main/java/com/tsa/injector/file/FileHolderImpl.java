package com.tsa.injector.file;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FileHolderImpl implements FileHolder {

    private final Map<String, File> files;

    public FileHolderImpl(Iterable<File> files) {

        this.files = StreamSupport.stream(files.spliterator(), false)
                .collect(Collectors.toMap(File::getName, file -> file));
    }

    @Override
    public boolean isPresent(String fileNameWithExtension) {
        return files.containsKey(fileNameWithExtension);
    }

    @Override
    public File getFile(String fileNameWithExtension) {
        return files.get(fileNameWithExtension);
    }

    @Override
    public int size() {
        return files.size();
    }
}
