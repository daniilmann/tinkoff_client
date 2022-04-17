package me.daniilmann.tinkoff.domain.model.profile.exception;

import me.daniilmann.tinkoff.domain.model.exception.IllegalValueException;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;
import me.daniilmann.tinkoff.domain.model.profile.UserId;

public class ProfileIdParseException extends IllegalValueException {

    public ProfileIdParseException(String id) {
        super(String.format("Unable to parse %s", id), ProfileId.class, "id");
    }

    public ProfileIdParseException(String id, Throwable cause, Class<?> clazz, String field) {
        super(String.format("Unable to parse %s", id), cause, ProfileId.class, "id");
    }
}
