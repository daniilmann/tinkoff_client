package me.daniilmann.tinkoff.domain.model.exception;

import java.util.Optional;

public class EntityEventsSyncronizeException extends IllegalArgumentException {

    private final Class<?> aggregateClass;

    public EntityEventsSyncronizeException(Class<?> aggregateClass) {
        super(String.format("%s; %s", aggregateClass == null ? null : aggregateClass.getName(), "Entity is older than first event"));
        this.aggregateClass = aggregateClass;
    }

    public EntityEventsSyncronizeException(Throwable cause, Class<?> aggregateClass) {
        super(String.format("%s; %s", aggregateClass == null ? null : aggregateClass.getName(), "Entity is older than first event"), cause);
        this.aggregateClass = aggregateClass;
    }
}
