import com.jobsity.challenge.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Test end to end application.
 */
public class ApplicationIntegrationTest {

    private static final String BOWLING_FILE = "src/test/resources/jobsity-bowling-moves.txt";
    private static final String OUTPUT_FILE = "src/test/resources/output.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    private static String readFileAsString() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(OUTPUT_FILE));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        return stringBuilder.toString();
    }

    @Test
    public void testApplicationShouoldRunOk() throws IOException {
        Application.main(new String[]{BOWLING_FILE});

        Assert.assertEquals(readFileAsString(), outContent.toString());
    }
}
