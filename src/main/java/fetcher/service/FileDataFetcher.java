package fetcher.service;

import java.io.IOException;
import java.nio.file.Path;

interface FileDataFetcher <T> {
    FileData<T> fetch(Path filePath, String delimiter) throws IOException;
}
