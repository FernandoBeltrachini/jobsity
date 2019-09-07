package service;

import com.jobsity.challenge.entities.Frame;
import com.jobsity.challenge.service.BowlingScoreBoardService;
import com.jobsity.challenge.utils.Constants;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test for BowlingScoreBoardService.
 */
public class BowlingScoreBoardServiceTest {

    private static final String PLAYER_NAME = "JUAN";
    private static final String SCORE = "3";
    private static final String PLAYER_STRIKE_LINE = PLAYER_NAME.concat(Constants.TAB_CHARACTER_DELIMITER).concat(String.valueOf(Frame.STRIKE_VALUE));
    private static final String PLAYERS_FIELD = "players";
    private static final String NOT_A_NUMBER = "not a number";
    private Map<String, List<Frame>> players = new HashMap<>();

    private BowlingScoreBoardService bowlingScoreBoardService = new BowlingScoreBoardService();

    @Before
    public void setPlayersVisible() throws NoSuchFieldException, IllegalAccessException {
        Field field = BowlingScoreBoardService.class.getDeclaredField(PLAYERS_FIELD);
        field.setAccessible(true);
        field.set(bowlingScoreBoardService, players);
    }

    @Test
    public void testProcessEmptyLineShouldRunOk() {
        bowlingScoreBoardService.processLine("");
    }

    @Test
    public void testProcessNullLineShouldRunOk() {
        bowlingScoreBoardService.processLine(null);
    }

    @Test
    public void testProcessOneLineShouldRunOk() throws NoSuchFieldException, IllegalAccessException {

        bowlingScoreBoardService.processLine(PLAYER_NAME.concat(Constants.TAB_CHARACTER_DELIMITER).concat(SCORE));

        assertNotNull(players);
        assertTrue(players.containsKey(PLAYER_NAME));
        assertEquals(players.get(PLAYER_NAME).get(0).getFirstScore(), Integer.valueOf(SCORE));
    }

    @Test
    public void testProcess11StrikesShouldOnlyHave10() throws NoSuchFieldException, IllegalAccessException {

        processTenStrikes();

        assertNotNull(players);
        assertTrue(players.containsKey(PLAYER_NAME));
        assertEquals(players.get(PLAYER_NAME).size(), 10);
        assertEquals(players.get(PLAYER_NAME).get(0).getFirstScore(), Frame.STRIKE_VALUE);
        assertEquals(players.get(PLAYER_NAME).get(9).getFirstScore(), Frame.STRIKE_VALUE);
    }

    @Test
    public void testProcessLineWithNoTabsShuoldRunOk() {
        bowlingScoreBoardService.processLine(PLAYER_NAME);

        assertTrue(players.isEmpty());
    }

    @Test
    public void testProcessNotANumberLineShouldRunOK() {
        bowlingScoreBoardService.processLine(PLAYER_NAME.concat(Constants.TAB_CHARACTER_DELIMITER).concat(NOT_A_NUMBER));

        assertTrue(players.isEmpty());

    }

    /**
     * Private method to process 10 strkes for a player.
     */
    private void processTenStrikes() {
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
        bowlingScoreBoardService.processLine(PLAYER_STRIKE_LINE);
    }

}
