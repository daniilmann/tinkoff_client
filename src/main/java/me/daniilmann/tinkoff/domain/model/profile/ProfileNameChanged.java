package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.IdValue;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import org.springframework.util.StringUtils;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity(name = "ProfileNameChanged")
public class ProfileNameChanged extends Event {

    private String name;

    public ProfileNameChanged(){}

    public ProfileNameChanged(ProfileId aggregateId, Timestamp version, String name) {
        super(Profile.class, aggregateId, version);
        if (!StringUtils.hasLength(name)) {
            throw new NullValueException(this.getClass(), "name");
        }
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    @Override
    protected IdValue<?> createAggregateId(String id) {
        return new ProfileId(UUID.fromString(id));
    }
}
