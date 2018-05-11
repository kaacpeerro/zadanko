package fetcher;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class UserFileDataFetcherTest {

    private static Path userDataFilePath;
    private UserFileDataFetcher fileDataFetcher;

    @BeforeClass
    public static void setup() {
        userDataFilePath = Paths.get("src", "test", "resources", "userData.txt");
    }

    @Before
    public void initialize() {
        fileDataFetcher = new UserFileDataFetcher();
    }

    @Test
    public void shouldFetchAllUsersFromFile() throws IOException {

        FileData<User> fileUserData = fileDataFetcher.fetch(userDataFilePath, ",");

        assertEquals(50, fileUserData.countAll());
    }
}
