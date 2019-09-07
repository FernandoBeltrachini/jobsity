package com.jobsity.challenge.entities;

import com.jobsity.challenge.utils.LineUtils;
import lombok.Data;

import java.util.Arrays;

import static com.jobsity.challenge.utils.Constants.TAB_CHARACTER_DELIMITER;

/**
 * Class that represents a Special Frame at bowling.
 */
@Data
public class SpecialFrame extends Frame {

    private Integer thirdValue;

    /**
     * Constructor.
     */
    public SpecialFrame(Integer firstValue) {
        super(firstValue);
    }

    @Override
    public String toString() {
        return (!isStrike() && !isSpare()) ? LineUtils.parseLine(Arrays.asList(super.toString(), String.valueOf(getThirdValue())), TAB_CHARACTER_DELIMITER) : super.toString();
    }
}
