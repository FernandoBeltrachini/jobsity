package com.jobsity.challenge.entities;

import com.jobsity.challenge.utils.LineUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jobsity.challenge.utils.Constants.TAB_CHARACTER_DELIMITER;
import static com.jobsity.challenge.utils.LineUtils.parseLine;

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
        List<String> values = new ArrayList<>();
        if (isStrike()) {
            values.add(STRIKE_FORMATED_VALUE);
            values.add(Frame.STRIKE_VALUE.equals(getSecondScore()) ? STRIKE_FORMATED_VALUE : String.valueOf(getSecondScore()));
            values.add(Frame.STRIKE_VALUE.equals(thirdValue) ? STRIKE_FORMATED_VALUE : String.valueOf(thirdValue));
        }
        if (isSpare()) {
            values.add(String.valueOf(getFirstScore()));
            values.add(SPARE_FORMATED_VALUE);
            values.add(Frame.STRIKE_VALUE.equals(thirdValue) ? STRIKE_FORMATED_VALUE : String.valueOf(thirdValue));
        }

        return (!values.isEmpty()) ? LineUtils.parseLine(values, TAB_CHARACTER_DELIMITER) : super.toString();
    }
}
