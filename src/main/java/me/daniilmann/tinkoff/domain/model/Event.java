package me.daniilmann.tinkoff.domain.model;

import lombok.Getter;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import me.daniilmann.tinkoff.domain.model.exception.UnsupportedEventException;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
public class Event {

    private final Class<? extends Entity> aggregateClass;
    private final IdValue<?> aggregateId;
    private final ZonedDateTime timestamp;
    private final Integer version;

    protected Event(Class<? extends Entity> aggregateClass, IdValue<?> aggregateId, ZonedDateTime timestamp, Integer version) {
        if (aggregateClass == null) {
            throw new NullValueException(this.getClass(), "aggregateClass");
        }
        this.aggregateClass = aggregateClass;

        if (aggregateId == null || aggregateId.id() == null) {
            throw new NullValueException(this.getClass(), "aggregateId");
        }
        this.aggregateId = aggregateId;

        if (timestamp == null) {
            throw new NullValueException(this.getClass(), "timestamp");
        }
        this.timestamp = timestamp;

        if (version == null) {
            throw new NullValueException(this.getClass(), "version");
        }
        this.version = version;
    }

    public Event(IEventSnapshot snapshot) {
        if (snapshot.getAggregateClass() == null) {
            throw new NullValueException(this.getClass(), "aggregateClass");
        }
        this.aggregateClass = snapshot.getAggregateClass();

        IdValue<?> newAggregateId = createAggregateId(snapshot.getAggregateId());
        if (newAggregateId == null || newAggregateId.id() == null) {
            throw new NullValueException(this.getClass(), "aggregateId");
        }
        this.aggregateId = newAggregateId;

        if (snapshot.getTimestamp() == null) {
            throw new NullValueException(this.getClass(), "timestamp");
        }
        this.timestamp = snapshot.getTimestamp();

        if (snapshot.getVersion() == null) {
            throw new NullValueException(this.getClass(), "version");
        }
        this.version = snapshot.getVersion();

    }

    protected IdValue<?> createAggregateId(String id) {
        throw new IllegalStateException("Unsupported method");
    }

    public IEventSnapshot toSnapshot(IEventSnapshot snapshot) {
        snapshot.setAggregateClass(getAggregateClass());
        snapshot.setAggregateId(getAggregateId().id().toString());
        snapshot.setTimestamp(getTimestamp());
        snapshot.setVersion(getVersion());
        return snapshot;
    }



}
