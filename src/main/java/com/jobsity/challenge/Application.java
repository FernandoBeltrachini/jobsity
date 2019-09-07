package com.jobsity.challenge;

import com.jobsity.challenge.service.BowlingScoreBoardService;
import com.jobsity.challenge.service.FileService;
import com.jobsity.challenge.service.FileServiceImpl;

import java.io.File;
import java.util.Objects;

/**
 * Class that holds main function.
 */
public class Application {

    public static void main(String[] args) {
        if (Objects.nonNull(args) && args.length > 0) {
            FileService fileService = new FileServiceImpl();
            File file = fileService.readFile(args[0]);
            BowlingScoreBoardService bowlingScoreBoardService = new BowlingScoreBoardService();
            fileService.processFile(file, bowlingScoreBoardService);
            bowlingScoreBoardService.getBoard();
        }

    }

}
