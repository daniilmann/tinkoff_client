package me.daniilmann.tinkoff.domain.model;

import javax.persistence.AttributeConverter;
import java.lang.reflect.InvocationTargetException;

public class ValueStringConverter implements AttributeConverter<IdValue<?>, String> {

    @Override
    public String convertToDatabaseColumn(IdValue<?> attribute) {
        return String.format("%s;%s",
                attribute.getClass().getName(),
                attribute.id().toString());
    }

    @Override
    public IdValue<?> convertToEntityAttribute(String dbData) {
        String[] values = dbData.split(";");
        try {
            return (IdValue<?>) Class.forName(values[0]).getConstructor(String.class).newInstance(values[1]);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Unknown value in IdValue column");
        }
    }

}
