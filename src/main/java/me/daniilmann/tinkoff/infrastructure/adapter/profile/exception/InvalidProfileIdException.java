package me.daniilmann.tinkoff.infrastructure.adapter.profile.exception;

public class InvalidProfileIdException extends Exception {

    private static String message = "Invalid Profile ID";

    public InvalidProfileIdException() {
        super(message);
    }

    public InvalidProfileIdException(Throwable cause) {
        super(message, cause);
    }
}
