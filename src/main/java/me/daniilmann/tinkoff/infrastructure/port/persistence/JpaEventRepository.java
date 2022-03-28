package me.daniilmann.tinkoff.infrastructure.port.persistence;

import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.EventStore;
import me.daniilmann.tinkoff.domain.model.IdValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaEventRepository extends EventStore, JpaRepository<Event, UUID> {

    @Override
    default void store(Event event) {
        save(event);
    }

    @Override
    default void storeAll(List<Event> newEvents) {
        saveAll(newEvents);
    }

    @Override
    default List<Event> findAllByAggregateId(IdValue<?> aggregateId)  {
        return findAllByAggregateIdString(String.format("%s;%s",
                aggregateId.getClass().getName(),
                aggregateId.id().toString()));
    }

    public List<Event> findAllByAggregateIdString(String aggregateIdString);
}
