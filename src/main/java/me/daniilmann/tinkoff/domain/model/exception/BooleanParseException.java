package me.daniilmann.tinkoff.domain.model.exception;

public class BooleanParseException extends Exception {

    public BooleanParseException(String value) {
        super(String.format("Unable to parse %s for Boolean", value));
    }

    public BooleanParseException(String value, Throwable cause) {
        super(String.format("Unable to parse %s for Boolean", value), cause);
    }
}
