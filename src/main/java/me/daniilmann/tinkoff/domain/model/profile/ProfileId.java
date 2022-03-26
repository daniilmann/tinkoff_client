package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.IdValue;

import java.util.UUID;

public class ProfileId extends IdValue<UUID> {

    public ProfileId(UUID id) {
        super(id);
    }

    public ProfileId(ProfileId id) {
        this(id == null ? null : id.id());
    }

}
