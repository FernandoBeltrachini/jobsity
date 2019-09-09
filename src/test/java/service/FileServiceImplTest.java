package service;

import com.jobsity.challenge.service.FileService;
import com.jobsity.challenge.service.FileServiceImpl;
import com.jobsity.challenge.service.LineProcessor;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Test for FIle Service.
 */
public class FileServiceImplTest {

    private static final String VALID_FILE = "src/test/resources/jobsity-bowling-moves.txt";
    private static final String EMPTY_FILE = "src/test/resources/empty-file.txt";
    private static final String INVALID_FILE = "an invalid filename";


    private FileService fileService = new FileServiceImpl();
    @Mock
    private LineProcessor lineProcessor;

    @Test
    public void testReadFileShouldReturnNullDueNullPath() {
        File response = fileService.readFile(null);

        assertNull(response);
    }

    @Test
    public void testReadFileShouldReturnFile() {
        File response = fileService.readFile(VALID_FILE);

        assertNotNull(response);
        assertTrue(response.length() > 0);
    }

    @Test
    public void testReadFileShouldReturnNullFileDueEmptyFile() {
        File response = fileService.readFile(EMPTY_FILE);

        assertNull(response);
    }

    @Test
    public void testProcessFileShouldRunOK() {
        initMocks(this);

        fileService.processFile(fileService.readFile(VALID_FILE), lineProcessor);

        verify(lineProcessor, times(50)).processLine(Matchers.anyString());
    }

    @Test
    public void testProcessFileShouldRunOKWithInvalidFile() {

        fileService.processFile(fileService.readFile(INVALID_FILE), lineProcessor);

    }

    @Test
    public void testProcessFileShouldRunOKWithNullLineProcessor() {

        fileService.processFile(fileService.readFile(INVALID_FILE), null);

    }

}
