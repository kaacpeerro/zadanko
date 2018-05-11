package fetcher;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

class UserDataFileReaderTest {

    private static File userData;

    @BeforeClass
    void setup() {
        userData = new File("userData.txt");
    }

    @Test
    void shouldFetchAllUsersFromFile() {
        FileDataFetcher fileDataFetcher = new FileDataFetcher();

        FileData<User> fileUserData = fileDataFetcher.fetchFromFile(userData);

        assertEquals(fileUserData.countAll(), 50);
    }
}
