package me.daniilmann.tinkoff.domain.model.exception;

import me.daniilmann.tinkoff.domain.model.Event;

import java.sql.Timestamp;

public class IllegalVersionException extends IllegalValueException {

    public IllegalVersionException(Class<? extends Event> clazz, Timestamp currentVersion, Timestamp newVersion) {
        super(String.format("%s versions mismatch. Next version: %s; Current version: %s",
                    clazz.toString(),
                    newVersion.toString(),
                    currentVersion.toString()),
                clazz, "version");
    }

    public IllegalVersionException(Throwable cause, Class<? extends Event> clazz, Timestamp currentVersion, Timestamp newVersion) {
        super(String.format("%s versions mismatch. Next version: %d; Current version: %d",
                    clazz.toString(),
                    newVersion.toString(),
                    currentVersion.toString()),
                cause, clazz, "version");
    }
}
