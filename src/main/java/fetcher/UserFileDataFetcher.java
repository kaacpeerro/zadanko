package fetcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class UserFileDataFetcher implements FileDataFetcher<User> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public FileData<User> fetch(Path userDataFilePath, String delimiter) throws IOException {
        try (Stream<String> lines = Files.lines(userDataFilePath)) {
            return lines
                    .map(line -> line.trim().split(delimiter))
                    .filter(this::hasRequiredColumnValues)
                    .map(this::covertToUser)
                    .collect(Collectors.collectingAndThen(Collectors.toSet(), FileData::new));
        }
    }

    private User covertToUser(String[] validLines) {

        String phoneNumber = validLines.length >= 4 ? validLines[3] : null;

        return User.builder()
                .firstName(validLines[0])
                .lastName(validLines[1])
                .birthday(LocalDate.parse(validLines[2], formatter))
                .phoneNumber(phoneNumber)
                .build();
    }

    private boolean hasRequiredColumnValues(String[] columns) {
        return columns.length >= 3;
    }



}
