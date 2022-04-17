package me.daniilmann.tinkoff.domain.model.profile.exception;

import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import me.daniilmann.tinkoff.domain.model.profile.Profile;

public class EmptyProfileRealException extends NullValueException {
    public EmptyProfileRealException() {
        super(Profile.class, "real");
    }

    public EmptyProfileRealException(Throwable cause) {
        super(cause, Profile.class, "real");
    }
}
