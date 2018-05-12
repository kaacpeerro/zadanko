package fetcher;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void shouldFindTheOldestUsersWithPhoneNumber() throws IOException {
        User actualOldestUser1 = createUser("Happy","Raith",LocalDate.of(1992, 7, 28),"4892948112");
        User actualOldestUser2 = createUser("Kacper", "Double",LocalDate.of(1992, 7, 28), "123456789");

        Set<User> oldestUsersWithPhoneNumber = new HashSet<>(Arrays.asList(actualOldestUser1, actualOldestUser2));

        FileData<User> fileUserData = fileDataFetcher.fetch(userDataFilePath, ",");
        FileData<User> usersWithPhoneNumber = fileUserData.filterBy(Collections.singleton(this::hasPhoneNumber));
        FileData<User> expectedOldestUserWithPhoneNumber = usersWithPhoneNumber.findMinBy(User::getBirthday);

        assertEquals(oldestUsersWithPhoneNumber, expectedOldestUserWithPhoneNumber.showUsers());
    }

    private boolean hasPhoneNumber(User user) {
        return user.getPhoneNumber() != null;
    }

    private User createUser(String firstName, String lastName, LocalDate birthday, String phoneNumber) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .phoneNumber(phoneNumber)
                .build();
    }
}
