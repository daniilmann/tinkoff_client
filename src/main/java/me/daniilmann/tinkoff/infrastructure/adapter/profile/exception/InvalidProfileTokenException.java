package me.daniilmann.tinkoff.infrastructure.adapter.profile.exception;

public class InvalidProfileTokenException extends Exception{

    private static String message = "Invalid Profile token";

    public InvalidProfileTokenException() {
        super(message);
    }

    public InvalidProfileTokenException(Throwable cause) {
        super(message, cause);
    }
}
