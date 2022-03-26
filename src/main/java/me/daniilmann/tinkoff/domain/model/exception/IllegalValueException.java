package me.daniilmann.tinkoff.domain.model.exception;

public class IllegalValueException extends ValueException {

    public IllegalValueException(String message, Class<?> clazz, String field) {
        super(message, clazz, field);
    }

    public IllegalValueException(String message, Throwable cause, Class<?> clazz, String field) {
        super(message, cause, clazz, field);
    }
}
