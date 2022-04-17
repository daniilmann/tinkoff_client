package me.daniilmann.tinkoff.domain.model;

import me.daniilmann.tinkoff.domain.model.exception.BooleanParseException;

public class DomainUtils {

    public static Boolean parseBoolean(String value) throws BooleanParseException {
        if (value == null || value.trim().isEmpty()) {
            throw new BooleanParseException(value);
        }
        try {
            String tempValue = value.trim().toLowerCase();
            if (tempValue.equalsIgnoreCase("true") || tempValue.equalsIgnoreCase("false")) {
                return Boolean.valueOf(tempValue);
            }
        } catch (Exception e) {
            throw new BooleanParseException(value, e);
        }
        throw new BooleanParseException(value);
    }

}
