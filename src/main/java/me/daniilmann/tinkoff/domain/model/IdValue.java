package me.daniilmann.tinkoff.domain.model;

import me.daniilmann.tinkoff.domain.model.exception.NullIdException;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;

import java.util.Objects;

public abstract class IdValue<T> implements ValueObject {

    private T id;

    private IdValue(){};

    public IdValue(T id) {
        setId(id);
    }

    public T id() {
        return id;
    }

    private void setId(T id) {
        if (id == null) {
            throw new NullIdException(this.getClass());
        }
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileId)) return false;
        IdValue idValue = (IdValue) o;
        return id.equals(idValue.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
