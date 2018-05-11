package fetcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class UserFileDataFetcher implements FileDataFetcher<User> {

    @Override
    public FileData<User> fetch(Path userDataFilePath, String delimiter) throws IOException {
        try (Stream<String> lines = Files.lines(userDataFilePath)) {
            return lines
                    .map(line -> line.split(delimiter))
                    .filter(this::convertibleToUser)
                    .map(User::new)
                    .collect(Collectors.collectingAndThen(Collectors.toSet(), FileData::new));
        }
    }

    private boolean convertibleToUser(String[] strings) {
        return strings.length >= 3;
    }

}
