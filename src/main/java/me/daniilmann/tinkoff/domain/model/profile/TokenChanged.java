package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.IdValue;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "TokenChanged")
public class TokenChanged extends Event {

    private String token;
    private Boolean real;

    private TokenChanged(){}

    protected TokenChanged(ProfileId aggregateId, Timestamp version, String token, Boolean real) {
        super(Profile.class, aggregateId, version);
        if (!StringUtils.hasLength(token)) {
            throw new NullValueException(this.getClass(), "token");
        }
        if (real != null) {
            throw new NullValueException(this.getClass(), "real");
        }
        this.token = token;
        this.real = real;
    }

    public String token() {
        return token;
    }

    public Boolean isReal() {
        return real;
    }

    @Override
    protected IdValue<?> createAggregateId(String id) {
        return new ProfileId(UUID.fromString(id));
    }
}
