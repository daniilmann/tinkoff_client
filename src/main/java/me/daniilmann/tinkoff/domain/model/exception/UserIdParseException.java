package me.daniilmann.tinkoff.domain.model.exception;

import me.daniilmann.tinkoff.domain.model.exception.IllegalValueException;
import me.daniilmann.tinkoff.domain.model.profile.UserId;

public class UserIdParseException extends IllegalValueException {

    public UserIdParseException(String id) {
        super(String.format("Unable to parse %s", id), UserId.class, "id");
    }

    public UserIdParseException(String id, Throwable cause, Class<?> clazz, String field) {
        super(String.format("Unable to parse %s", id), cause, UserId.class, "id");
    }
}
