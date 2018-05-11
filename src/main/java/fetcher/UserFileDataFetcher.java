package fetcher;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Supplier;

class UserFileDataFetcher implements FileDataFetcher<User> {

    @Override
    public FileData<User> fetch(Path userDataFilePath, Supplier<User> constructor) {
        return new FileData<>();
    }
}
