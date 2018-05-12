package fetcher;

interface FileDataValidator<T> {
    boolean isValid(T data);
}
