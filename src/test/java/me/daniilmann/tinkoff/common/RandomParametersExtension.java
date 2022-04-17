package me.daniilmann.tinkoff.common;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RandomParametersExtension implements ParameterResolver {

    private final java.util.Random random = new java.util.Random();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface Random {

    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(Random.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class<?> paramClass = parameterContext.getClass();
        // TODO Write other types
        if (Integer.class.equals(paramClass) || int.class.equals(paramClass)) {
            return random.nextInt();
        }

        throw new ParameterResolutionException(String.format("%s is not supported", paramClass.getName()));
    }

}
