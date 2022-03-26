package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.IEventSnapshot;
import me.daniilmann.tinkoff.domain.model.IdValue;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.UUID;

public class TokenChanged extends Event {

    private String token;
    private Boolean real;

    protected TokenChanged(ProfileId aggregateId, ZonedDateTime timestamp, Integer version, String token, Boolean real) {
        super(Profile.class, aggregateId, timestamp, version);
        if (!StringUtils.hasLength(token)) {
            throw new NullValueException(this.getClass(), "token");
        }
        if (real != null) {
            throw new NullValueException(this.getClass(), "real");
        }
        this.token = token;
        this.real = real;
    }

    public TokenChanged(IEventSnapshot snapshot) {
        super(snapshot);
        if (!StringUtils.hasLength(snapshot.getFields().getOrDefault("token", null))) {
            throw new NullValueException(this.getClass(), "token");
        }
        this.token = snapshot.getFields().get("token");
        String realValue = snapshot.getFields().getOrDefault("real", null);
        if (realValue == null || Boolean.parseBoolean(realValue)) {
            throw new NullValueException(this.getClass(), "real");
        }
        this.real = Boolean.valueOf(realValue);
    }

    public String getToken() {
        return token;
    }

    public Boolean isReal() {
        return real;
    }

    @Override
    public IEventSnapshot toSnapshot(IEventSnapshot snapshot) {
        snapshot.getFields().put("token", getToken());
        snapshot.getFields().put("real", isReal().toString());
        return super.toSnapshot(snapshot);
    }

    @Override
    protected IdValue<?> createAggregateId(String id) {
        return new ProfileId(UUID.fromString(id));
    }
}
