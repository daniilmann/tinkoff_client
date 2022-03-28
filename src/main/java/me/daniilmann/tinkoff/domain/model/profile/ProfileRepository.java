package me.daniilmann.tinkoff.domain.model.profile;

import java.util.List;

public interface ProfileRepository {

    public Profile save(Profile profile);

    public Profile getById(ProfileId profileId);

    public List<Profile> findAllByUserId(UserId userId);

}
