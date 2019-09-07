package com.jobsity.challenge.entities;

import com.jobsity.challenge.utils.LineUtils;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static com.jobsity.challenge.utils.Constants.TAB_CHARACTER_DELIMITER;
import static com.jobsity.challenge.utils.LineUtils.parseLine;

/**
 * Class that represents a frame at bowling.
 */
@Data
public class Frame {
    public static final Integer STRIKE_VALUE = 10;
    public static final String STRIKE_FORMATED_VALUE = "X";
    public static final String SPARE_FORMATED_VALUE = "/";

    private Integer firstScore;
    private Integer secondScore;
    private Integer totalScore;

    public Frame(Integer firstScore) {
        this.firstScore = firstScore;
    }

    public Boolean isStrike() {
        return STRIKE_VALUE.equals(firstScore);
    }

    public Boolean isSpare() {
        if (Objects.nonNull(firstScore) && Objects.nonNull(secondScore)) {
            return STRIKE_VALUE.equals(firstScore + secondScore);
        }
        return false;
    }

    @Override
    public String toString() {
        return isStrike() ? parseLine(Collections.singletonList(STRIKE_FORMATED_VALUE), TAB_CHARACTER_DELIMITER) :
                isSpare() ? LineUtils.parseLine(Arrays.asList(String.valueOf(firstScore), SPARE_FORMATED_VALUE), TAB_CHARACTER_DELIMITER) :
                        LineUtils.parseLine(Arrays.asList(String.valueOf(firstScore), String.valueOf(secondScore)), TAB_CHARACTER_DELIMITER);

    }
}
