package com.jobsity.challenge.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Implementation of FileService.
 */
public class FileServiceImpl implements FileService {

    @Override
    public File readFile(String filePath) {
        if (Objects.nonNull(filePath)) {
            File file = new File(filePath);
            if (file.length() > 0) {
                return file;
            }
            System.out.println("File does not exist or is empty");
        }
        return null;
    }

    @Override
    public void processFile(File file, LineProcessor lineProcessor) {
        BufferedReader bf = null;
        if (Objects.nonNull(file) && Objects.nonNull(lineProcessor)) {
            try {
                bf = new BufferedReader(new FileReader(file));
                bf.lines().forEachOrdered(lineProcessor::processLine);
            } catch (IOException e) {
                System.out.println("Couldnt read file");
            } finally {
                if (Objects.nonNull(bf)) {
                    try {
                        bf.close();
                    } catch (IOException e) {
                        System.out.println("Couldnt close BufferedReader");
                    }
                }
            }
        }
    }
}
