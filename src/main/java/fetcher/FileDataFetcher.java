package fetcher;

import java.io.File;
import java.util.function.Supplier;

public interface FileDataFetcher <T> {

    FileData<T> fetch(File userData, Supplier<T> constructor);
}
