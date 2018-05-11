package fetcher;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

final class FileData<T> {

    private final Set<T> rowData;

    FileData(Set<T> rowData) {
        this.rowData = rowData;
    }

    int countAll() {
        return rowData.size();
    }

    Set<T> showUsers() {
        return this.rowData;
    }

    FileData<T> filterBy(Set<Predicate<T>> predicate) {

        Set<T> filteredRowData = rowData.stream()
                .filter(predicate.stream().reduce(Predicate::or).orElse(t -> true))
                .collect(Collectors.toSet());

        return new FileData<>(filteredRowData);
    }

    <R extends Comparable<R>> FileData<T> findMinBy(Function<T, R> attribute) {

        return rowData.stream()
                .map(attribute)
                .min(Comparable::compareTo)
                .map(min -> matchRows(min, attribute))
                .map(FileData<T>::new)
                .orElseGet(() -> new FileData<>(Collections.emptySet()));
    }

    private <R extends Comparable<R>> Set<T> matchRows(R min, Function<T, R> attribute) {

        Predicate<T> matchingRow = s -> attribute.apply(s).equals(min);

        return rowData.stream().filter(matchingRow).collect(Collectors.toSet());
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
