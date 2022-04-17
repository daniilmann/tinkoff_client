package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.IdValue;
import me.daniilmann.tinkoff.domain.model.profile.exception.ProfileIdParseException;

import java.util.UUID;

public class ProfileId extends IdValue<UUID> {

    private ProfileId(){}

    public ProfileId(UUID id) {
        super(id);
    }

    public ProfileId(ProfileId id) {
        this(id == null ? null : id.id());
    }

    @Override
    public ProfileId replicate() {
        return new ProfileId(this);
    }

    public static ProfileId fromString(String id) {
        try {
            return new ProfileId(UUID.fromString(id));
        } catch (Exception e) {
            throw new ProfileIdParseException(id);
        }
    }
}
