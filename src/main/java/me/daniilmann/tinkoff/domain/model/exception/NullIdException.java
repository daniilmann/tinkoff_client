package me.daniilmann.tinkoff.domain.model.exception;

public class NullIdException extends NullValueException {

    public NullIdException(Class<?> clazz) {
        super(clazz, "id");
    }

    public NullIdException(Throwable cause, Class<?> clazz) {
        super(cause, clazz, "id");
    }
}
