package challenge.utils;

import com.jobsity.challenge.entities.Frame;
import com.jobsity.challenge.entities.SpecialFrame;
import com.jobsity.challenge.exception.BowlingException;
import com.jobsity.challenge.utils.BowlingUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jobsity.challenge.utils.Constants.BOWLING_FAULT_VALUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test for bowling utils.
 */
public class BowlingUtilsTest {


    public static final String INTEGER_VALUE = "2";
    public static final String WORDS_VALUE = "words";
    public static final String HIGH_NUMBER = "11";

    @Test
    public void testGetBowlingScoreWithNullValueShouldRunOk() throws BowlingException {
        Integer response = BowlingUtils.getBowlingScore(null);

        assertNull(response);
    }

    @Test
    public void testGetBowlingScoreWithEmptyValueShouldRunOk() throws BowlingException {
        Integer response = BowlingUtils.getBowlingScore("");

        assertNull(response);
    }

    @Test
    public void testGetBowlingScoreShouoldRunOkWithIntValue() throws BowlingException {
        Integer response = BowlingUtils.getBowlingScore(INTEGER_VALUE);

        assertEquals(response, Integer.valueOf(INTEGER_VALUE));
    }

    @Test(expected = BowlingException.class)
    public void testGetBowlingScoreShouldDispatchExceptionDueLettersOnNumber() throws BowlingException {
        BowlingUtils.getBowlingScore(WORDS_VALUE);
    }

    @Test(expected = BowlingException.class)
    public void testGetBowlingScoreShouldDispatchExceptionDueHighNumber() throws BowlingException {
        BowlingUtils.getBowlingScore(HIGH_NUMBER);
    }

    @Test
    public void testGetBowlingScoreShouldRunOkSendingValidChar() throws BowlingException {
        Integer response = BowlingUtils.getBowlingScore(BOWLING_FAULT_VALUE);

        assertEquals(response, Integer.valueOf(0));
    }

    @Test
    public void testCalculateBowlingScoresShouldRunOK() {
        List<Frame> frames = generateFrames();

        BowlingUtils.calculateBowlingScores(frames);

        assertNotNull(frames);
        assertEquals(frames.get(0).getTotalScore(), Integer.valueOf(30));

    }

    /**
     * Method used to create 10 frames.
     */
    private List<Frame> generateFrames() {
        Frame frame1 = new Frame(10);
        Frame frame2 = new Frame(10);
        Frame frame3 = new Frame(10);
        Frame frame4 = new Frame(5);
        frame4.setSecondScore(5);
        Frame frame5 = new Frame(5);
        Frame frame6 = new Frame(5);
        Frame frame7 = new Frame(5);
        Frame frame8 = new Frame(5);
        Frame frame9 = new Frame(5);
        Frame specialFrame = new SpecialFrame(5);

        List<Frame> frames = new ArrayList<>();
        frames.add(frame1);
        frames.add(frame2);
        frames.add(frame3);
        frames.add(frame4);
        frames.add(frame5);
        frames.add(frame6);
        frames.add(frame7);
        frames.add(frame8);
        frames.add(frame9);
        frames.add(specialFrame);
        return frames;
    }

}
