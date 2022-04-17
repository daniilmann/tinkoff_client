package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.IdValue;
import me.daniilmann.tinkoff.domain.model.exception.UserIdParseException;

import java.util.UUID;

public class UserId extends IdValue<UUID> {

    private UserId(){}

    public UserId(UUID id) {
        super(id);
    }

    public UserId(UserId id) {
        this(id == null ? null : id.id());
    }

    @Override
    public UserId replicate() {
        return new UserId(this);
    }

    public static UserId fromString(String id) {
        try {
            return new UserId(UUID.fromString(id));
        } catch (Exception e) {
            throw new UserIdParseException(id);
        }
    }

}
