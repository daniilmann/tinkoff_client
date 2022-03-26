package me.daniilmann.tinkoff.infrastructure.port.persistence;

import me.daniilmann.tinkoff.infrastructure.snapshot.EventSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventSnapshotRepository extends JpaRepository<EventSnapshot, UUID> {

//    @Query("SELECT e from Event e where e.timestamp >=" +
//            "(SELECT CASE WHEN COUNT(a.versionTimestamp) != 0 THEN a.versionTimestamp ELSE 0 END FROM Entity a where a.id == :aggregateId)")
    public List<EventSnapshot> findActualByAggregateId(String aggregateId);

    public List<EventSnapshot> findByAggregateId(String aggregateId);

}
