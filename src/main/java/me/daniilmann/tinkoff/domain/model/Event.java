package me.daniilmann.tinkoff.domain.model;

import me.daniilmann.tinkoff.domain.model.exception.NullValueException;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Table(name = "events")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "eventType")
public class Event {

    @Id
    private UUID id;

    @Convert(converter = ClassStringConverter.class)
    private Class<? extends AggregateRoot> aggregateClass;

    @Transient
    private IdValue<?> aggregateId;

    @Column(name = "aggregateId")
    private String aggregateIdString;

    private Timestamp version;

    protected Event(){}

    protected Event(Class<? extends AggregateRoot> aggregateClass, IdValue<?> aggregateId, Timestamp version) {
        if (aggregateClass == null) {
            throw new NullValueException(this.getClass(), "aggregateClass");
        }
        this.aggregateClass = aggregateClass;

        if (aggregateId == null || aggregateId.id() == null) {
            throw new NullValueException(this.getClass(), "aggregateId");
        }
        this.aggregateId = aggregateId;
        this.aggregateIdString = String.format("%s;%s",
                aggregateId.getClass().getName(),
                aggregateId.id().toString());

        if (version == null) {
            throw new NullValueException(this.getClass(), "version");
        }
        this.version = version;
    }

//    public Event(IEventSnapshot snapshot) {
//        if (snapshot.getAggregateClass() == null) {
//            throw new NullValueException(this.getClass(), "aggregateClass");
//        }
//        this.aggregateClass = snapshot.getAggregateClass();
//
//        IdValue<?> newAggregateId = createAggregateId(snapshot.getAggregateId());
//        if (newAggregateId == null || newAggregateId.id() == null) {
//            throw new NullValueException(this.getClass(), "aggregateId");
//        }
//        this.aggregateId = newAggregateId;
//
//        if (snapshot.getTimestamp() == null) {
//            throw new NullValueException(this.getClass(), "timestamp");
//        }
//        this.timestamp = snapshot.getTimestamp();
//
//        if (snapshot.getVersion() == null) {
//            throw new NullValueException(this.getClass(), "version");
//        }
//        this.version = snapshot.getVersion();
//
//    }

    protected IdValue<?> createAggregateId(String id) {
        throw new IllegalStateException("Unsupported method");
    }

    public UUID id() {
        return id;
    }

    public Class<? extends AggregateRoot> aggregateClass() {
        return aggregateClass;
    }

    public IdValue<?> aggregateId() {
        if (aggregateId == null) {
            String[] values = aggregateIdString.split(";");
            try {
                aggregateId = (IdValue<?>) Class.forName(values[0]).getConstructor(String.class).newInstance(values[1]);
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("Unknown value in aggregateId column");
            }
        }
        return aggregateId;
    }

    public Timestamp version() {
        return version;
    }

    //    public IEventSnapshot toSnapshot(IEventSnapshot snapshot) {
//        snapshot.setAggregateClass(getAggregateClass());
//        snapshot.setAggregateId(getAggregateId().id().toString());
//        snapshot.setTimestamp(getTimestamp());
//        snapshot.setVersion(getVersion());
//        return snapshot;
//    }



}
