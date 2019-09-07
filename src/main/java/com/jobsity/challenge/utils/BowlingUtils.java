package com.jobsity.challenge.utils;

import com.jobsity.challenge.entities.Frame;
import com.jobsity.challenge.entities.SpecialFrame;
import com.jobsity.challenge.exception.BowlingException;

import java.util.List;
import java.util.Objects;

import static com.jobsity.challenge.utils.Constants.BOWLING_FAULT_VALUE;

/**
 * Class that holds bowling utils.
 */
public class BowlingUtils {

    /**
     * Get bowling score from an string.
     * Could be an integer or the default value.
     */
    public static Integer getBowlingScore(String integer) throws BowlingException {
        if (Objects.nonNull(integer) && !integer.isEmpty()) {
            if (BOWLING_FAULT_VALUE.equals(integer)) {
                return 0;
            }
            Integer score;
            try {
                score = Integer.valueOf(integer);
            } catch (NumberFormatException e) {
                throw new BowlingException("Invalid number");
            }
            if ((score > 10 || score < 0)) {
                throw new BowlingException("Invalid number");
            }
            return score;
        }
        return null;
    }

    /**
     * Method that calculates the bowling score.
     */
    public static void calculateBowlingScores(List<Frame> frames) {
        for (int pos = 0; pos < frames.size(); pos++) {
            Frame actualFrame = frames.get(pos);
            Integer partialScore = actualFrame.getFirstScore();
            if (actualFrame.isStrike()) {
                if (frames.size() > pos + 1) {
                    Frame nextFrame = frames.get(pos + 1);
                    partialScore = partialScore + nextFrame.getFirstScore();
                    if (nextFrame.isStrike()) {
                        if (frames.size() >= pos + 2) {
                            Frame thirdFrame = frames.get(pos + 1);
                            partialScore = partialScore + thirdFrame.getFirstScore();
                        }
                    } else {
                        partialScore = partialScore + nextFrame.getSecondScore();
                    }
                }
            } else if (actualFrame.isSpare()) {
                if (frames.size() > pos + 1) {
                    Frame nextFrame = frames.get(pos + 1);
                    partialScore = Frame.STRIKE_VALUE + nextFrame.getFirstScore();
                }
            } else {
                partialScore = actualFrame.getFirstScore() + actualFrame.getSecondScore();
            }
            if (pos > 0) {
                partialScore = partialScore + frames.get(pos - 1).getTotalScore();
            }
            if (pos == 9) {
                SpecialFrame specialFrame = (SpecialFrame) actualFrame;
                if (specialFrame.isStrike()) {
                    partialScore = partialScore + specialFrame.getSecondScore();
                }
                if (Objects.nonNull(specialFrame.getThirdValue())) {
                    partialScore = partialScore + specialFrame.getThirdValue();
                }
            }
            actualFrame.setTotalScore(partialScore);
        }
    }
}
