package me.daniilmann.tinkoff.infrastructure.port.persistence;

import me.daniilmann.tinkoff.domain.model.profile.Profile;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;
import me.daniilmann.tinkoff.domain.model.profile.ProfileRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProfileRepository extends ProfileRepository, JpaRepository<Profile, ProfileId> {

}
