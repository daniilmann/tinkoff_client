package me.daniilmann.tinkoff.domain.model;

import com.google.common.collect.ImmutableList;
import me.daniilmann.tinkoff.domain.model.exception.*;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@MappedSuperclass
public abstract class AggregateRoot<ID extends IdValue<?>> {

    @EmbeddedId
    private ID id;

    @Version
    private Timestamp version;

    @Transient
    private List<Event> events;

    @Transient
    private Boolean stale = true;

    protected AggregateRoot(){}

    protected AggregateRoot(ID id) {
        this(id, new ArrayList<>());
    }

    protected AggregateRoot(ID id, List<Event> events) {
        setId(id);
        propagate(events);
    }

    protected void setId(ID id) {
        if (id == null) {
            throw new NullIdException(this.getClass());
        }
        this.id = id;
    }

    private void setVersion(Timestamp version) {
        this.version = version;
    }

    protected void markStale() {
        this.stale = true;
    }

    public void propagate(List<Event> events) {
        if (!stale) {
            throw new EntityIsNotStaleException(this.getClass());
        }
        if (events == null) {
            throw new NullValueException(this.getClass(), "events");
        }
        if (events.size() > 0) {
            events.sort(Comparator.comparing(Event::version));
            if (version.after(events.get(0).version())) {
                throw new EntityEventsSyncronizeException(this.getClass());
            }
            events.forEach(this::applyNewEvent);
        }
        this.stale = false;
    }

    protected void applyNewEvent(Event event) {
        checkVersion(event);
        apply(event);
        events.add(event);
    }

    private void apply(Event event) {
        try {
            Method method = this.getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
            setVersion(event.version());
        } catch (InvocationTargetException e) {
            throw new ApplyEventException(e, event.getClass(), this.getClass(), id());
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new UnsupportedEventException(e, event.getClass(), this.getClass(), id());
        }
    }

    public ID id() {
        return id.replicate();
    }

    public List<Event> getNewEvents() {
        return ImmutableList.copyOf(events);
    }

    private void checkVersion(Event event) {
        if (this.version.after(event.version())) {
            throw new IllegalVersionException(event.getClass(), this.version, event.version());
        }
    }

}
