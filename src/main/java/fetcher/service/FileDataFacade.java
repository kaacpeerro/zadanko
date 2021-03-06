package fetcher.service;

import java.io.IOException;

import java.nio.file.Path;

public class FileDataFacade {

    private final FileDataFetcher<User> fileDataFetcher;

    FileDataFacade(FileDataFetcher<User> fileDataFetcher) {
        this.fileDataFetcher = fileDataFetcher;
    }

    public FileData<User> fetch(Path userDataFilePath, String delimiter) throws IOException {
        return fileDataFetcher.fetch(userDataFilePath, delimiter);
    }
}
