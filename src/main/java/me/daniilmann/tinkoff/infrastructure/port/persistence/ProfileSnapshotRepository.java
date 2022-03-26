package me.daniilmann.tinkoff.infrastructure.port.persistence;

import me.daniilmann.tinkoff.infrastructure.snapshot.ProfileSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfileSnapshotRepository extends JpaRepository<ProfileSnapshot, UUID> {

    public List<ProfileSnapshot> findByUserId(UUID userId);

}
