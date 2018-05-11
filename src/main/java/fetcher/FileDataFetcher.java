package fetcher;


import java.io.IOException;
import java.nio.file.Path;

public interface FileDataFetcher <T> {

    FileData<T> fetch(Path filePath, String delimiter) throws IOException;
}
