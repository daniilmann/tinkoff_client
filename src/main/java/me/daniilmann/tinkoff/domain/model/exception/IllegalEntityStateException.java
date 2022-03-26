package me.daniilmann.tinkoff.domain.model.exception;

public class IllegalEntityStateException extends IllegalStateException {

    private Class<?> aggregateClass;

    public IllegalEntityStateException(Class<?> aggregateClass, String message) {
        super(String.format("%s; %s", aggregateClass.getName(), message));
        this.aggregateClass = aggregateClass;
    }

    public IllegalEntityStateException(String message, Throwable cause, Class<?> aggregateClass) {
        super(String.format("%s; %s", aggregateClass.getName(), message), cause);
        this.aggregateClass = aggregateClass;
    }
}
