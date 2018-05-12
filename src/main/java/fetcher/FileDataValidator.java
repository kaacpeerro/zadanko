package fetcher;

@FunctionalInterface
interface FileDataValidator<T> {
    boolean isValid(T data);
}
