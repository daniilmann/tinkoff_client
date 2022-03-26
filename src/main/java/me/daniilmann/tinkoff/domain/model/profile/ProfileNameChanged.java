package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.IEventSnapshot;
import me.daniilmann.tinkoff.domain.model.IdValue;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ProfileNameChanged extends Event {

    private String name;

    public ProfileNameChanged(ProfileId aggregateId, ZonedDateTime timestamp, Integer version, String name) {
        super(Profile.class, aggregateId, timestamp, version);
        if (!StringUtils.hasLength(name)) {
            throw new NullValueException(this.getClass(), "name");
        }
        this.name = name;
    }

    public ProfileNameChanged(IEventSnapshot snapshot) {
        super(snapshot);
        if (!StringUtils.hasLength(snapshot.getFields().getOrDefault("name", null))) {
            throw new NullValueException(this.getClass(), "name");
        }
        this.name = snapshot.getFields().get("name");
    }

    public String getName() {
        return name;
    }

    @Override
    public IEventSnapshot toSnapshot(IEventSnapshot snapshot) {
        snapshot.getFields().put("name", getName());
        return super.toSnapshot(snapshot);
    }

    @Override
    protected IdValue<?> createAggregateId(String id) {
        return new ProfileId(UUID.fromString(id));
    }
}
