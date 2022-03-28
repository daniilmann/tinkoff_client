package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.IdValue;

import java.util.UUID;

public class ProfileId extends IdValue<UUID> {

    public ProfileId(){}

    public ProfileId(UUID id) {
        super(id);
    }

    public ProfileId(String id) {
        super(UUID.fromString(id));
    }

    public ProfileId(ProfileId id) {
        this(id == null ? null : id.id());
    }

    @Override
    public ProfileId replicate() {
        return new ProfileId(this);
    }
}
