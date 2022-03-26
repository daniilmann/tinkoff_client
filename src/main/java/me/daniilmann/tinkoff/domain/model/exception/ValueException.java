package me.daniilmann.tinkoff.domain.model.exception;

public abstract class ValueException extends RuntimeException {

    private Class<?> clazz;
    private String field;

    public ValueException(String message, Class<?> clazz, String field) {
        super(String.format("%s.%s; %s", clazz.getName(), field, message));
        this.clazz = clazz;
        this.field = field;
    }

    public ValueException(String message, Throwable cause, Class<?> clazz, String field) {
        super(String.format("%s.%s; %s", clazz.getName(), field, message), cause);
        this.clazz = clazz;
        this.field = field;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getField() {
        return field;
    }
}
