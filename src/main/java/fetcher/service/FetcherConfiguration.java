package fetcher.service;

public class FetcherConfiguration {

    public static FileDataFacade fileDataFacade() {
        FileDataValidator<String[]> fileDataValidator = new UserFileDataValidator();
        FileDataFetcher<User> fileDataFetcher = new UserFileDataFetcher(fileDataValidator);
        return new FileDataFacade(fileDataFetcher);
    }
}
