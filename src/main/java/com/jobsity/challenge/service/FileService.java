package com.jobsity.challenge.service;

import java.io.File;

/**
 * Interface for file's interaction.
 */
public interface FileService {

    /**
     * Method used to read file given a file path.
     */
    File readFile(String filePath);

    /**
     * Method that iterate over every line on a file with a line processor.
     */
    void processFile(File file, LineProcessor lineProcessor);

}
