package me.daniilmann.tinkoff.domain.model;

import com.google.common.collect.ImmutableList;
import me.daniilmann.tinkoff.domain.model.exception.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Entity<ID extends IdValue<?>> {

    private ID id;
    private Integer baseVersion;
    private ZonedDateTime versionTimestamp;
    private List<Event> events;
    private Boolean stale;

    protected Entity(ID id) {
        this(id, new ArrayList<>());
    }

    protected Entity(ID id, List<Event> events) {
        setId(id);
        setEvents(events);
    }

    private void setId(ID id) {
        if (id == null) {
            throw new NullIdException(this.getClass());
        }
        this.id = id;
    }

    private void setEvents(List<Event> events) {
        if (events == null) {
            throw new NullValueException(this.getClass(), "events");
        }
        this.events = new ArrayList<>(events);
    }

    protected void markStale() {
        this.stale = true;
    }

    public void propagate(List<Event> events) {
        if (!stale) {
            throw new EntityIsNotStaleException(this.getClass());
        }
        events.sort(Comparator.comparing(Event::getTimestamp));
        if (versionTimestamp.compareTo(events.get(0).getTimestamp()) > 0) {
            throw new EntityEventsSyncronizeException(this.getClass());
        }
        events.forEach(this::applyNewEvent);
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
        } catch (InvocationTargetException e) {
            throw new ApplyEventException(e, event.getClass(), this.getClass(), getId());
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new UnsupportedEventException(e, event.getClass(), this.getClass(), getId());
        }
    }

    public ID getId() {
        return id;
    }

    public int getBaseVersion() {
        return baseVersion;
    }

    public List<Event> getNewEvents() {
        return ImmutableList.copyOf(events);
    }

    protected int getNextVersion() {
        return getCurrentVersion() + 1;
    }

    private int getCurrentVersion() {
        return getBaseVersion() + events.size();
    }

    private void checkVersion(Event event) {
        if (event.getVersion() != getNextVersion()) {
            throw new IllegalVersionException(event.getClass(), getCurrentVersion(), event.getVersion());
        }
    }

}
