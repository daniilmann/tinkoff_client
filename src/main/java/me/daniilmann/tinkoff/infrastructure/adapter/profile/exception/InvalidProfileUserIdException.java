package me.daniilmann.tinkoff.infrastructure.adapter.profile.exception;

public class InvalidProfileUserIdException extends Exception{

    private static String message = "Invalid Profile User ID";

    public InvalidProfileUserIdException() {
        super(message);
    }

    public InvalidProfileUserIdException(Throwable cause) {
        super(message, cause);
    }
}
