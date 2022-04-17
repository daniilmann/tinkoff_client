package me.daniilmann.tinkoff.domain.model.profile.exception;

import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import me.daniilmann.tinkoff.domain.model.profile.Profile;

public class EmptyProfileNameException extends NullValueException {
    public EmptyProfileNameException() {
        super(Profile.class, "name");
    }

    public EmptyProfileNameException(Throwable cause) {
        super(cause, Profile.class, "name");
    }
}
