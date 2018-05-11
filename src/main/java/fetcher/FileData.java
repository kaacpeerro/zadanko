package fetcher;

import java.util.Set;

class FileData <T> {

    Set<T> rowData;

    int countAll() {
        return rowData.size();
    }
}
