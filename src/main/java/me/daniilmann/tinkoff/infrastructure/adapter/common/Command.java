package me.daniilmann.tinkoff.infrastructure.adapter.common;

public interface Command {

    public void validate() throws InvalidCommandException;

}
