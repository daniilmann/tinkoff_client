package me.daniilmann.tinkoff.infrastructure.adapter.profile.exception;

import me.daniilmann.tinkoff.infrastructure.adapter.common.InvalidCommandException;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.CreateProfile;

public class InvalidUpdateProfileException extends InvalidCommandException {

    public InvalidUpdateProfileException() {
        super(CreateProfile.class);
    }

    public InvalidUpdateProfileException(Throwable cause) {
        super(cause, CreateProfile.class);
    }

    public InvalidUpdateProfileException(String message) {
        super(message, CreateProfile.class);
    }

    public InvalidUpdateProfileException(String message, Throwable cause) {
        super(message, cause, CreateProfile.class);
    }
}
