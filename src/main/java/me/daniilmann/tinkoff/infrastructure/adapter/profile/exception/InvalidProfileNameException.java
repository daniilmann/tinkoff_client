package me.daniilmann.tinkoff.infrastructure.adapter.profile.exception;

public class InvalidProfileNameException extends Exception{

    private static String message = "Invalid Profile name";

    public InvalidProfileNameException() {
        super(message);
    }

    public InvalidProfileNameException(Throwable cause) {
        super(message, cause);
    }
}
