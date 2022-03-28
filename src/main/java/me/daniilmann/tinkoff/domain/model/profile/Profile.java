package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.AggregateRoot;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "profiles")
@Access(value = AccessType.FIELD)
public class Profile extends AggregateRoot<ProfileId> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "userId"))
    private UserId userId;

    private String name;
    private String token;
    private Boolean real;

    public Profile() {
        super();
    }

    public Profile(UserId userId, ProfileId profileId, String name, String token, Boolean real) {
        super(profileId);

    }

    public void changeName(String name) {
        if (!StringUtils.hasLength(name)) {
            throw new NullValueException(this.getClass(), "name");
        }
        ProfileNameChanged profileNameChanged = new ProfileNameChanged(id(), Timestamp.from(Instant.now()), name);
        applyNewEvent(profileNameChanged);
    }

    private void apply(ProfileNameChanged event) {
        this.name = event.name();
    }

    public void changeToken(String token, Boolean isReal) {
        if (!StringUtils.hasLength(token)) {
            throw new NullValueException(this.getClass(), "name");
        }
        if (isReal == null) {
            throw new NullValueException(this.getClass(), "real");
        }
        TokenChanged tokenChanged = new TokenChanged(id(), Timestamp.from(Instant.now()), token, isReal);
        applyNewEvent(tokenChanged);
    }

    private void apply(TokenChanged event) {
        this.token = event.token();
        this.real = event.isReal();
    }

    public UserId userId() {
        return userId;
    }

    public String name() {
        return name;
    }

    public String token() {
        return token;
    }

    public Boolean isReal() {
        return real;
    }
}
