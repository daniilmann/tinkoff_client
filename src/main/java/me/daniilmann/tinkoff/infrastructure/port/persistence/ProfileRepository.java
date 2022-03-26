package me.daniilmann.tinkoff.infrastructure.port.persistence;

import me.daniilmann.tinkoff.domain.model.profile.Profile;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;
import me.daniilmann.tinkoff.domain.model.profile.UserId;
import me.daniilmann.tinkoff.infrastructure.snapshot.ProfileSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileRepository implements me.daniilmann.tinkoff.domain.model.profile.ProfileRepository {

    private ProfileSnapshotRepository profileSnapshotRepository;

    @Autowired
    public ProfileRepository(ProfileSnapshotRepository jpaProfileRepository) {
        this.profileSnapshotRepository = jpaProfileRepository;
    }

    @Override
    public void save(Profile profile) {
        profileSnapshotRepository.save((ProfileSnapshot) profile.toSnapshot(new ProfileSnapshot()));
    }

    @Override
    public Profile loadProfileById(ProfileId profileId) {
        ProfileSnapshot profileSnapshot = profileSnapshotRepository.getById(profileId.id());
        Profile profile = Profile.fromSnapshot(profileSnapshot);
        return profile;
    }

    @Override
    public List<Profile> loadAllProfilesByUserId(UserId userId) {
        List<ProfileSnapshot> profileSnapshots = profileSnapshotRepository.findByUserId(userId.id());
        List<Profile> profiles = profileSnapshots.parallelStream().map(Profile::fromSnapshot).toList();
        return profiles;
    }
}
