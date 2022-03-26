package me.daniilmann.tinkoff.domain.model.exception;

import me.daniilmann.tinkoff.domain.model.Entity;
import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.IdValue;

public class UnsupportedEventException extends RuntimeException {

    public UnsupportedEventException(Throwable cause, Class<? extends Event> eventClass, Class<? extends Entity> entityClass, IdValue<?> aggregateId) {
        super(String.format("Unsupported %s exception for %s with ID %s", eventClass.getName(), entityClass.getName(), aggregateId.id()), cause);
    }
}
