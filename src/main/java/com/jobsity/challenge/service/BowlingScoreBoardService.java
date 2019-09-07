package com.jobsity.challenge.service;

import com.jobsity.challenge.entities.Frame;
import com.jobsity.challenge.entities.SpecialFrame;
import com.jobsity.challenge.exception.BowlingException;
import com.jobsity.challenge.utils.BowlingUtils;
import com.jobsity.challenge.utils.Constants;
import com.jobsity.challenge.utils.LineUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class that holds the implementation of a Bowling Score Board.
 */
public class BowlingScoreBoardService implements ScoreBoard, LineProcessor {

    public static final String PINFALLS_OUTPUT = "Pinfalls";
    public static final String SCORE_OUTPUT = "Score";
    public static final String FRAME_OUTPUT = "Frame";

    private Map<String, List<Frame>> players = new HashMap<>();

    @Override
    public void getBoard() {
        String printDelimitor = Constants.TAB_CHARACTER_DELIMITER.concat(Constants.TAB_CHARACTER_DELIMITER);
        //Pinting headerline
        LineUtils.printList(FRAME_OUTPUT, Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), printDelimitor);

        players.forEach((player, frames) -> {
            BowlingUtils.calculateBowlingScores(frames);
            System.out.println(player);
            LineUtils.printList(PINFALLS_OUTPUT, frames.stream().map(frame -> LineUtils.parseLine(Collections.singletonList(frame.toString()), Constants.TAB_CHARACTER_DELIMITER)).collect(Collectors.toList()), Constants.TAB_CHARACTER_DELIMITER);
            LineUtils.printList(SCORE_OUTPUT, frames.stream().map(f -> LineUtils.parseLine(Collections.singletonList(f.getTotalScore().toString()), Constants.TAB_CHARACTER_DELIMITER)).collect(Collectors.toList()), printDelimitor);
        });
    }

    /**
     * Method used to get the bowling score.
     */
    private Integer getBowlingScore(String score) {
        try {
            return BowlingUtils.getBowlingScore(score);
        } catch (BowlingException e) {
            //Here we could log something like "Line could not be processed" and the exception.
        }
        return null;
    }

    @Override
    public void processLine(String line) {
        if (Objects.nonNull(line) && !line.isEmpty()) {
            String[] splittedLine = LineUtils.split(line, Constants.TAB_CHARACTER_DELIMITER);
            if (!(splittedLine.length < 2)) {
                String playerName = splittedLine[0];
                Integer score = getBowlingScore(splittedLine[1]);
                if (Objects.nonNull(score)) {
                    if (players.containsKey(playerName)) {
                        List<Frame> frames = players.get(playerName);
                        Integer frameSize = frames.size();
                        if (frameSize <= 10) {
                            Frame lastFrame = frames.get(frameSize - 1);
                            if (Objects.isNull(lastFrame.getSecondScore()) && !lastFrame.isStrike()) {
                                if (Frame.STRIKE_VALUE >= lastFrame.getFirstScore() + score) {
                                    lastFrame.setSecondScore(score);
                                }
                            } else {
                                if (frameSize == 10) {
                                    SpecialFrame specialFrame = (SpecialFrame) lastFrame;
                                    if (!specialFrame.isSpare() && Objects.isNull(specialFrame.getThirdValue())) {
                                        specialFrame.setThirdValue(score);
                                    }
                                } else {
                                    frames.add((frameSize == 9) ? new SpecialFrame(score) : new Frame(score));
                                }
                            }
                        }
                    } else {
                        List<Frame> frames = new ArrayList<>();
                        frames.add(new Frame(score));
                        players.put(playerName, frames);
                    }
                }
            }
        }
    }

}