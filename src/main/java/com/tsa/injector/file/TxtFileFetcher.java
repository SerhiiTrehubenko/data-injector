package com.tsa.injector.file;

import java.io.File;
import java.util.*;

public class TxtFileFetcher extends FileFetcher {
    private final static String TXT_EXTENSION = ".txt";
    public TxtFileFetcher(String incomePathArg) {
        super(incomePathArg);
    }

    @Override
    public Iterable<File> filterFiles(File[] files) {
        return Arrays.stream(files)
                .filter(File::isFile)
                .filter(filteredFile -> filteredFile.getName().endsWith(TXT_EXTENSION))
                .toList();
    }
}
