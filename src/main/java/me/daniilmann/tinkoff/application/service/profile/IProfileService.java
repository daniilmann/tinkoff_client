package me.daniilmann.tinkoff.application.service.profile;

import me.daniilmann.tinkoff.domain.model.profile.Profile;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;
import me.daniilmann.tinkoff.domain.model.profile.UserId;

import java.util.List;

public interface IProfileService {

    public Profile loadProfileWithId(ProfileId profileId);

    public List<Profile> loadProfileWithUserId(UserId userId);



}
