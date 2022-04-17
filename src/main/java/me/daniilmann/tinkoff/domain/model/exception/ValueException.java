package me.daniilmann.tinkoff.domain.model.exception;

public abstract class ValueException extends RuntimeException {

    private Class<?> sourceClass;
    private String field;

    public ValueException(String message, Class<?> sourceClass, String field) {
        super(String.format("%s.%s; %s", sourceClass.getName(), field, message));
        this.sourceClass = sourceClass;
        this.field = field;
    }

    public ValueException(String message, Throwable cause, Class<?> sourceClass, String field) {
        super(String.format("%s.%s; %s", sourceClass.getName(), field, message), cause);
        this.sourceClass = sourceClass;
        this.field = field;
    }

    public Class<?> getSourceClass() {
        return sourceClass;
    }

    public String getField() {
        return field;
    }
}
