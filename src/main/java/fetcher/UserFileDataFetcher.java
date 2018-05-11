package fetcher;

import java.io.File;
import java.util.function.Supplier;

class UserFileDataFetcher implements FileDataFetcher<User> {

    @Override
    public FileData<User> fetch(File userData, Supplier<User> constructor) {
        return new FileData<>();
    }
}
