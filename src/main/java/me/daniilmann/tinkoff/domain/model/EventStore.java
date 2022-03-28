package me.daniilmann.tinkoff.domain.model;

import java.util.List;

public interface EventStore {

    public void store(Event event);

    public void storeAll(List<Event> newEvents);

    public List<Event> findAllByAggregateId(IdValue<?> aggregateId);

}
