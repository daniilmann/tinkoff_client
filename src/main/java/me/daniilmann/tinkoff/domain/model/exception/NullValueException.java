package me.daniilmann.tinkoff.domain.model.exception;

public class NullValueException extends IllegalValueException {

    public NullValueException(Class<?> clazz, String field) {
        super(field + " must be not null", clazz, field);
    }

    public NullValueException(Throwable cause, Class<?> clazz, String field) {
        super(field + " must be not null", cause, clazz, field);
    }
}
