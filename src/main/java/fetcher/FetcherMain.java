package fetcher;

import java.io.IOException;

import fetcher.service.FetcherConfiguration;
import fetcher.service.FileData;
import fetcher.service.FileDataFacade;
import fetcher.service.User;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Collections;
import java.util.Set;

class FetcherMain {

    public static void main(String[] args) throws IOException {
        if (args.length < 1)
            return;

        Path filePath = Paths.get(args[0]);

        FileDataFacade fileDataFacade = FetcherConfiguration.fileDataFacade();
        FileData<User> fileData = fileDataFacade.fetch(filePath, ",");

        int allUsers = fileData.countAll();

        Set<User> oldestUsersWithPhoneNumber = fileData
                .filterBy(Collections.singleton(FetcherMain::hasPhoneNumber))
                .findMinBy(User::getBirthday)
                .showData();

        System.out.printf("File contains %d valid users\n", allUsers);
        System.out.println("The oldest user(s) with phone number are:");
        oldestUsersWithPhoneNumber.forEach(System.out::println);
    }

    private static boolean hasPhoneNumber(User user) {
        return user.getPhoneNumber() != null;
    }
}
