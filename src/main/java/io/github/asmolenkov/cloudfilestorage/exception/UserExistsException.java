package io.github.asmolenkov.cloudfilestorage.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }
}
