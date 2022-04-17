package me.daniilmann.tinkoff.domain.model.profile.exception;

import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import me.daniilmann.tinkoff.domain.model.profile.Profile;

public class EmptyProfileTokenException extends NullValueException {
    public EmptyProfileTokenException() {
        super(Profile.class, "token");
    }

    public EmptyProfileTokenException(Throwable cause) {
        super(cause, Profile.class, "token");
    }
}
