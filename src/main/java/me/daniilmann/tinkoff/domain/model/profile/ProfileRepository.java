package me.daniilmann.tinkoff.domain.model.profile;

import java.util.List;

public interface ProfileRepository {

    public void save(Profile profile);

    public Profile loadProfileById(ProfileId profileId);

    public List<Profile> loadAllProfilesByUserId(UserId userId);

}
