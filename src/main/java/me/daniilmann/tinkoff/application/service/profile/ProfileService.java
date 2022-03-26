package me.daniilmann.tinkoff.application.service.profile;

import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.EventStore;
import me.daniilmann.tinkoff.domain.model.profile.Profile;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;
import me.daniilmann.tinkoff.domain.model.profile.ProfileRepository;
import me.daniilmann.tinkoff.domain.model.profile.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService  implements IProfileService {

    private ProfileRepository profileRepository;
    private EventStore eventStore;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, EventStore eventStore) {
        this.profileRepository = profileRepository;
        this.eventStore = eventStore;
    }

    @Override
    public Profile loadProfileWithId(ProfileId profileId) {
        Profile profile = profileRepository.loadProfileById(profileId);
        List<Event> events = eventStore.load(profileId);
        profile.propagate(events);
        return profile;
    }

    @Override
    public List<Profile> loadProfileWithUserId(UserId userId) {
        return null;
    }
}
