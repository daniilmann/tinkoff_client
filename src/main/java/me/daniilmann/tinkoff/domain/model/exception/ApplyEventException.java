package me.daniilmann.tinkoff.domain.model.exception;

import me.daniilmann.tinkoff.domain.model.Entity;
import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.IdValue;

public class ApplyEventException extends RuntimeException {

    public ApplyEventException(Throwable cause, Class<? extends Event> eventClass, Class<? extends Entity> entityClass, IdValue<?> aggregateId) {
        super(String.format("Apply %s exception for %s with ID %s", eventClass.getName(), entityClass.getName(), aggregateId.id()), cause);
    }
}
