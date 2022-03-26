package me.daniilmann.tinkoff.infrastructure.port.persistence;

import me.daniilmann.tinkoff.domain.model.*;
import me.daniilmann.tinkoff.infrastructure.snapshot.EventSnapshot;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventStore implements me.daniilmann.tinkoff.domain.model.EventStore {

    private EventSnapshotRepository eventSnapshotRepository;

    @Override
    public void store(Class<? extends Entity> entityClass, IdValue<?> aggregateId, List<Event> newEvents, int baseVersion) throws OptimisticLockingException {
        List<EventSnapshot> snapshots = newEvents.stream().map((Event e) -> e.toSnapshot(new EventSnapshot())).map(EventSnapshot.class::cast).toList();
        eventSnapshotRepository.saveAll(snapshots);
    }

    @Override
    public List<Event> load(IdValue<?> aggregateId) {
        List<EventSnapshot> eventSnapshots = eventSnapshotRepository.findActualByAggregateId(aggregateId.id().toString());
        List<Event> events = CollectionUtils.isEmpty(eventSnapshots)
                ? new ArrayList<>()
                : eventSnapshots.parallelStream().map(Event::new).toList();
        return events;
    }

}
