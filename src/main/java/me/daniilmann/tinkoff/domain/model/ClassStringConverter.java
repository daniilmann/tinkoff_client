package me.daniilmann.tinkoff.domain.model;

import javax.persistence.AttributeConverter;

public class ClassStringConverter implements AttributeConverter<Class<?>, String> {
    @Override
    public String convertToDatabaseColumn(Class<?> attribute) {
        return attribute.getName();
    }

    @Override
    public Class<?> convertToEntityAttribute(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unkown clas name: " + className);
        }
    }
}
