package me.daniilmann.tinkoff.infrastructure.adapter.profile.exception;

public class NotEmptyProfileIdException extends Exception {

    private static String message = "Profile ID must be empty";

    public NotEmptyProfileIdException() {
        super(message);
    }

    public NotEmptyProfileIdException(Throwable cause) {
        super(message, cause);
    }
}
