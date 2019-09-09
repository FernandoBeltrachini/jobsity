package challenge.utils;

import com.jobsity.challenge.utils.LineUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test for line utils.
 */
public class LineUtilsTest {

    private static final String STRING_TO_SPLIT = "string to split";
    public static final String DELIMITOR = " ";
    public static final String FIRST_WORD = "first";
    public static final String SECOND_WORD = "second";

    @Test
    public void testSplitShouldRunOkWithNullValue() {
        String[] response = LineUtils.split(null, null);

        assertNotNull(response);
        assertEquals(response.length, 0);
    }

    @Test
    public void testSplitShouldRunOkWithEmptyDelimitor() {
        String[] response = LineUtils.split(STRING_TO_SPLIT, null);

        assertNotNull(response);
        assertEquals(response.length, 0);
    }

    @Test
    public void testSplitShouldRunOkWithDelimitor() {
        String[] response = LineUtils.split(STRING_TO_SPLIT, DELIMITOR);

        assertNotNull(response);
        assertEquals(response.length, 3);
    }

    @Test
    public void testParseLineShouldRunOKWithNullDelimitorAndWords() {
        String response = LineUtils.parseLine(null, null);

        assertNull(response);
    }

    @Test
    public void testParseLineShouldRunOKWithNullDelimitor() {
        String response = LineUtils.parseLine(new ArrayList<>(), null);

        assertNull(response);
    }

    @Test
    public void testParseLineShouldRunOKWithNullWordList() {
        String response = LineUtils.parseLine(null, DELIMITOR);

        assertNull(response);
    }

    @Test
    public void testParseLineShouldRunOK() {
        List<String> words = new ArrayList<>();
        words.add(FIRST_WORD);
        words.add(SECOND_WORD);

        String response = LineUtils.parseLine(words, DELIMITOR);

        assertNotNull(response);
        assertTrue(response.contains(FIRST_WORD));
        assertTrue(response.contains(SECOND_WORD));
    }
}
