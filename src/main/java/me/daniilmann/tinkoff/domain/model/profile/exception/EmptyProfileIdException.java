package me.daniilmann.tinkoff.domain.model.profile.exception;

import me.daniilmann.tinkoff.domain.model.exception.NullIdException;
import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import me.daniilmann.tinkoff.domain.model.profile.Profile;

public class EmptyProfileIdException extends NullIdException {
    public EmptyProfileIdException() {
        super(Profile.class);
    }

    public EmptyProfileIdException(Throwable cause) {
        super(cause, Profile.class);
    }
}
