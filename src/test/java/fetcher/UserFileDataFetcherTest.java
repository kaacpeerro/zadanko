package fetcher;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class UserFileDataFetcherTest {

    private static File userData;
    private UserFileDataFetcher fileDataFetcher;

    @BeforeClass
    public static void setup() {
        userData = new File("userData.txt");
    }

    @Before
    public void initialize() {
        fileDataFetcher = new UserFileDataFetcher();
    }

    @Test
    public void shouldFetchAllUsersFromFile() {

        FileData<User> fileUserData = fileDataFetcher.fetch(userData, User::new);

        assertEquals(fileUserData.countAll(), 50);
    }
}
