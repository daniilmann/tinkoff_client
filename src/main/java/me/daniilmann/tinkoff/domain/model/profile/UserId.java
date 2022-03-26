package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.IdValue;

import java.util.UUID;

public class UserId extends IdValue<UUID> {

    public UserId(UUID id) {
        super(id);
    }

    public UserId(UserId id) {
        this(id == null ? null : id.id());
    }

}
