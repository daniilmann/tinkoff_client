package me.daniilmann.tinkoff.domain.model.exception;

public class EntityIsNotStaleException extends IllegalEntityStateException {

    public EntityIsNotStaleException(Class<?> aggregateClass) {
        super(aggregateClass, "Entity is not stale");
    }

    public EntityIsNotStaleException(Throwable cause, Class<?> aggregateClass) {
        super("Entity is not stale", cause, aggregateClass);
    }
}
