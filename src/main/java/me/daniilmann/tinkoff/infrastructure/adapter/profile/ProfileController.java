package me.daniilmann.tinkoff.infrastructure.adapter.profile;

import me.daniilmann.tinkoff.application.service.profile.IProfileService;
import me.daniilmann.tinkoff.domain.model.profile.Profile;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.converter.ProfileCommandConverters;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/profiles")
public class ProfileController {

    private IProfileService profileService;

    @Autowired
    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public JsonProfile createProfile(@RequestBody CreateProfile createProfile) throws InvalidProfileTokenException, InvalidProfileIdException, InvalidProfileNameException, InvalidProfileUserIdException, InvalidCreateProfileException {
        createProfile.validate();
        Profile profile = ProfileCommandConverters.convert(createProfile);
        profileService.save(profile);
        return new JsonProfile(profile);
    }

    @PatchMapping(path = "/{id}")
    public JsonProfile patchProfile(@PathVariable String id, @RequestBody UpdateProfile updateProfile) throws InvalidUpdateProfileException, InvalidProfileTokenException, InvalidProfileIdException, InvalidProfileNameException, InvalidProfileUserIdException {
        updateProfile.setId(id);
        updateProfile.validate();
        Profile profile = ProfileCommandConverters.convert(updateProfile);
        profileService.save(profile);
        return new JsonProfile(profile);
    }

    @GetMapping
    public List<JsonProfile> getAllProfiles() {
        return profileService.findAll().parallelStream().map(JsonProfile::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public JsonProfile getProfileById(@PathVariable String id) throws InvalidProfileIdException {
        try {
            return new JsonProfile(profileService.loadProfileWithId(ProfileId.fromString(id)));
        } catch (Exception e) {
            throw new InvalidProfileIdException(e);
        }
    }

}
