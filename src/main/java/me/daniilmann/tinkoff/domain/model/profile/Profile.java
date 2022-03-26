package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.Entity;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import org.springframework.util.StringUtils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Profile extends Entity<ProfileId> {

    private UserId userId;
    private ProfileId profileId;
    private String name;
    private String token;
    private Boolean real;

    public Profile(UserId userId, ProfileId profileId, String name, String token, Boolean real) {
        super(profileId);

    }

    public IProfileSnapshot toSnapshot(IProfileSnapshot snapshot) {
        snapshot.setProfileId(profileId.id());
        snapshot.setUserId(userId.id());
        snapshot.setName(name);
        snapshot.setToken(token);
        snapshot.setReal(real);
        return snapshot;
    }

    public static Profile fromSnapshot(IProfileSnapshot profileSnapshot) {
        Profile profile = new Profile(new UserId(profileSnapshot.getUserId()),
                new ProfileId(profileSnapshot.getProfileId()),
                profileSnapshot.getName(),
                profileSnapshot.getToken(),
                profileSnapshot.isReal());
        profile.markStale();
        return profile;
    }

    public void changeName(String name) {
        if (!StringUtils.hasLength(name)) {
            throw new NullValueException(this.getClass(), "name");
        }
        ProfileNameChanged profileNameChanged = new ProfileNameChanged(getId(), ZonedDateTime.now(ZoneOffset.UTC), getNextVersion(), name);
        applyNewEvent(profileNameChanged);
    }

    private void apply(ProfileNameChanged event) {
        this.name = event.getName();
    }

    public void changeToken(String token, Boolean isReal) {
        if (!StringUtils.hasLength(token)) {
            throw new NullValueException(this.getClass(), "name");
        }
        if (isReal == null) {
            throw new NullValueException(this.getClass(), "real");
        }
        TokenChanged tokenChanged = new TokenChanged(getId(), ZonedDateTime.now(ZoneOffset.UTC), getNextVersion(), token, isReal);
        applyNewEvent(tokenChanged);
    }

    private void apply(TokenChanged event) {
        this.token = event.getToken();
        this.real = event.isReal();
    }

    public UserId userId() {
        return userId;
    }

    public ProfileId profileId() {
        return profileId;
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
