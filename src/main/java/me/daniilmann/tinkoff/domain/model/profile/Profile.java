package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.AggregateRoot;
import me.daniilmann.tinkoff.domain.model.DomainUtils;
import me.daniilmann.tinkoff.domain.model.exception.BooleanParseException;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import me.daniilmann.tinkoff.domain.model.profile.exception.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Locale;

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

    private Profile() {
        super();
    }

    public Profile(ProfileId profileId, UserId userId, String name, String token, Boolean real) {
        super(profileId);
        setUserId(userId);
        setName(name);
        setToken(token);
        setReal(real);
        ProfileCreated profileCreated = new ProfileCreated(profileId, Timestamp.from(Instant.now()));
        applyNewEvent(profileCreated);
    }

    protected void checkId(ProfileId id) {
        if (id == null) {
            throw new EmptyProfileIdException();
        }
    }

    public void changeName(String name) {
        if (!StringUtils.hasLength(name)) {
            throw new NullValueException(this.getClass(), "name");
        }
        ProfileNameChanged profileNameChanged = new ProfileNameChanged(id(), Timestamp.from(Instant.now()), name);
        applyNewEvent(profileNameChanged);
    }

    private void apply(ProfileCreated event) {}

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

    private void setUserId(UserId userId) {
        if (userId == null || userId.id() == null) {
            throw new EmptyProfileUserIdException();
        }
        this.userId = userId;
    }

    public String name() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new EmptyProfileNameException();
        }
        this.name = name;
    }

    public String token() {
        return token;
    }

    private void setToken(String token) {
        if (token == null || token.trim().length() == 0) {
            throw new EmptyProfileTokenException();
        }
        this.token = token;
    }

    public Boolean isReal() {
        return real;
    }

    private void setReal(Boolean real) {
        if (real == null) {
            throw new EmptyProfileRealException();
        }
        this.real = real;
    }

    private void setReal(String real) {
        try {
            setReal(DomainUtils.parseBoolean(real));
        } catch (BooleanParseException e) {
            throw new EmptyProfileRealException(e);
        }
    }

    public static Builder createBuilder() {
        return new Profile().new Builder();
    }

    public class Builder {

        private Builder(){}

        public Builder setId(ProfileId id) {
            Profile.this.setId(id);
            return this;
        }

        public Builder setUserId(UserId userId) {
            Profile.this.setUserId(userId);
            return this;
        }

        public Builder setName(String name) {
            Profile.this.setName(name);
            return this;
        }

        public Builder setToken(String token) {
            Profile.this.setToken(token);
            return this;
        }

        public Builder setReal(Boolean real) {
            Profile.this.setReal(real);
            return this;
        }

        public Builder setReal(String real) {
            Profile.this.setReal(real);
            return this;
        }

        public Profile build() {
            return Profile.this;
        }

    }

}
