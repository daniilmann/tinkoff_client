package me.daniilmann.tinkoff.domain.model.profile.exception;

import me.daniilmann.tinkoff.domain.model.exception.NullValueException;
import me.daniilmann.tinkoff.domain.model.profile.Profile;

public class EmptyProfileUserIdException extends NullValueException {
    public EmptyProfileUserIdException() {
        super(Profile.class, "userId");
    }

    public EmptyProfileUserIdException(Throwable cause) {
        super(cause, Profile.class, "userId");
    }
}
