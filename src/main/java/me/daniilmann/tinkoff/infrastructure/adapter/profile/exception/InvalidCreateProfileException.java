package me.daniilmann.tinkoff.infrastructure.adapter.profile.exception;

import me.daniilmann.tinkoff.infrastructure.adapter.common.InvalidCommandException;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.CreateProfile;

public class InvalidCreateProfileException extends InvalidCommandException {

    public InvalidCreateProfileException() {
        super(CreateProfile.class);
    }

    public InvalidCreateProfileException(Throwable cause) {
        super(cause, CreateProfile.class);
    }

    public InvalidCreateProfileException(String message) {
        super(message, CreateProfile.class);
    }

    public InvalidCreateProfileException(String message, Throwable cause) {
        super(message, cause, CreateProfile.class);
    }
}
