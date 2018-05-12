package fetcher.service;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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

    private static Path filePath = Paths.get("src", "test", "resources", "userData.txt");

    private FileDataFacade fileDataFacade;

    @Before
    public void beforeEach() {
        FileDataValidator<String[]> fileDataValidator = new UserFileDataValidator();
        FileDataFetcher<User> fileDataFetcher = new UserFileDataFetcher(fileDataValidator);
        fileDataFacade = new FileDataFacade(fileDataFetcher);
    }

    @Test
    public void shouldFetchAllUsersFromFile() throws IOException {
        FileData<User> fileUserData = fileDataFacade.fetch(filePath, ",");
        assertEquals(50, fileUserData.countAll());
    }

    @Test
    public void shouldFindTheOldestUsersWithPhoneNumber() {
        User oldestUser1 = createUser("Happy", "Raith", LocalDate.of(1992, 7, 28), "4892948112");
        User oldestUser2 = createUser("Kacper", "Double", LocalDate.of(1992, 7, 28), "123456789");
        User oldestUserWithoutPhoneNumber = createUser("John", "Smith", LocalDate.of(1992, 7, 28), null);
        User user3 = createUser("Foo", "Bar", LocalDate.of(2000, 7, 28), "555999000");

        Set<User> allUsers = new HashSet<>();
        Collections.addAll(allUsers, oldestUser1, oldestUser2, oldestUserWithoutPhoneNumber, user3);

        FileData<User> fileUserData = new FileData<>(allUsers);

        FileData<User> expectedOldestUsersWithPhoneNumber = fileUserData
                        .filterBy(Collections.singleton(this::hasPhoneNumber))
                        .findMinBy(User::getBirthday);

        Set<User> oldestUsersWithPhoneNumber = new HashSet<>(Arrays.asList(oldestUser1, oldestUser2));

        assertEquals(oldestUsersWithPhoneNumber, expectedOldestUsersWithPhoneNumber.showData());
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
