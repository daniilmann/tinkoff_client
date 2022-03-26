package me.daniilmann.tinkoff.domain.model;

import java.util.List;

public interface EventStore {

  public void store(Class<? extends Entity> entityClass, IdValue<?> aggregateId, List<Event> newEvents, int baseVersion)
      throws OptimisticLockingException;

  public List<Event> load(IdValue<?> aggregateId);

}
