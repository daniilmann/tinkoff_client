package me.daniilmann.tinkoff.domain.model.exception;

public class EntityIsStaleException  extends IllegalEntityStateException {

    public EntityIsStaleException(Class<?> aggregateClass) {
        super(aggregateClass, "Entity is stale");
    }

    public EntityIsStaleException(Throwable cause, Class<?> aggregateClass) {
        super("Entity is stale", cause, aggregateClass);
    }
}
