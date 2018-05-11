package fetcher;

import java.util.Objects;
import java.util.Set;

final class FileData <T> {

    private final Set<T> rowData;

    FileData(Set<T> rowData) {
        this.rowData = rowData;
    }

    int countAll() {
        return rowData.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileData<?> fileData = (FileData<?>) o;
        return Objects.equals(rowData, fileData.rowData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowData);
    }
}
