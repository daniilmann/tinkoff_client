package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.IdValue;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "ProfileCreated")
public class ProfileCreated extends Event {

    private ProfileCreated(){}

    public ProfileCreated(ProfileId aggregateId, Timestamp version) {
        super(Profile.class, aggregateId, version);
    }

    @Override
    protected IdValue<?> createAggregateId(String id) {
        return new ProfileId(UUID.fromString(id));
    }

}
