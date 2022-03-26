package me.daniilmann.tinkoff.domain.model.exception;

import me.daniilmann.tinkoff.domain.model.Event;

public class IllegalVersionException extends IllegalValueException {

    public IllegalVersionException(Class<? extends Event> clazz, Integer currentVersion, Integer expectedVersion) {
        super(String.format("%s versions mismatch. Expected version: %d; Current version: %d", clazz.toString(), expectedVersion, currentVersion), clazz, "version");
    }

    public IllegalVersionException(Throwable cause, Class<? extends Event> clazz, Integer currentVersion, Integer expectedVersion) {
        super(String.format("%s versions mismatch. Expected version: %d; Current version: %d", clazz.toString(), expectedVersion, currentVersion), cause, clazz, "version");
    }
}
