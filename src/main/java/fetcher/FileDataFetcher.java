package fetcher;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Supplier;

public interface FileDataFetcher <T> {

    FileData<T> fetch(Path filePath, Supplier<T> constructor);
}
