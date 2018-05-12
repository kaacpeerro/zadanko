package fetcher.service;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class UserFileDataFetcher implements FileDataFetcher<User> {

    private final FileDataValidator<String[]> fileDataValidator;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    UserFileDataFetcher(FileDataValidator<String[]> fileDataValidator) {
        this.fileDataValidator = fileDataValidator;
    }

    @Override
    public FileData<User> fetch(Path userDataFilePath, String delimiter) throws IOException {
        try (Stream<String> lines = Files.lines(userDataFilePath)) {
            return lines
                    .map(line -> line.split(delimiter))
                    .map(this::trimArray)
                    .filter(fileDataValidator::isValid)
                    .map(this::covertToUser)
                    .collect(Collectors.collectingAndThen(Collectors.toSet(), FileData::new));
        }
    }

    private String[] trimArray(String[] strings) {
        return Stream.of(strings)
                .map(String::trim)
                .toArray(String[]::new);
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
}
