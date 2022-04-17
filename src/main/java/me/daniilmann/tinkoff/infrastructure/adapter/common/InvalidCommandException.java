package me.daniilmann.tinkoff.infrastructure.adapter.common;

public abstract class InvalidCommandException extends Exception {

    private Class commandClass;

    public InvalidCommandException(Class<?> commandClass) {
        super(String.format("%s is invalid.",
                commandClass == null ? "Command" : commandClass.getName()));
        setCommandClass(commandClass);
    }

    public InvalidCommandException(Throwable cause, Class<?> commandClass) {
        super(String.format("%s is invalid.",
                commandClass == null ? "Command" : commandClass.getName()),
                cause);
        setCommandClass(commandClass);
    }

    public InvalidCommandException(String message, Class<?> commandClass) {
        super(String.format("%s is invalid. %s",
                commandClass == null ? "Command" : commandClass.getName(),
                message));
        setCommandClass(commandClass);
    }

    public InvalidCommandException(String message, Throwable cause, Class<?> commandClass) {
        super(String.format("%s is invalid. %s",
                commandClass == null ? "Command" : commandClass.getName(),
                message), cause);
        setCommandClass(commandClass);
    }

    private void setCommandClass(Class<?> commandClass) {
        this.commandClass = commandClass;
    }
}
