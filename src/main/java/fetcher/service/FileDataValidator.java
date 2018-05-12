package fetcher.service;

@FunctionalInterface
interface FileDataValidator<T> {
    boolean isValid(T data);
}
