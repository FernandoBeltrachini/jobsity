import com.jobsity.challenge.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;

/**
 * Test end to end application.
 */
public class ApplicationIntegrationTest {

    private static final String BOWLING_FILE = "src/test/resources/jobsity-bowling-moves.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testApplicationShouoldRunOk() {

        Application.main(new String[]{BOWLING_FILE});

        Assert.assertEquals("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n" +
                "Pedro\n" +
                "Pinfalls\tX\t0\t0\tX\t7\t/\tX\tX\tX\tX\tX\t3\t3\n" +
                "Score\t\t10\t\t10\t\t30\t\t50\t\t80\t\t110\t\t140\t\t170\t\t186\t\t195\n" +
                "Carlos\n" +
                "Pinfalls\t2\t5\t2\t6\t2\t2\t4\t2\t2\t6\t2\t2\t2\t2\t2\t2\t2\t2\t2\t2\n" +
                "Score\t\t7\t\t15\t\t19\t\t25\t\t33\t\t37\t\t41\t\t45\t\t49\t\t55\n", outContent.toString());
    }


}
