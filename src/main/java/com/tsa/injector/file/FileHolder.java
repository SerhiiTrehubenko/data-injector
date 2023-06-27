package com.tsa.injector.file;

import java.io.File;

public interface FileHolder {

    boolean isPresent(String fileNameWithExtension);
    File getFile(String fileNameWithExtension);
    int size();
}
