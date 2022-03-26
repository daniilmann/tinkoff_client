package me.daniilmann.tinkoff.application.controller.profile;

import me.daniilmann.tinkoff.application.service.profile.IProfileService;
import me.daniilmann.tinkoff.domain.model.profile.Profile;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(path = "/profiles")
public class ProfileController {

    private IProfileService profileService;

    @Autowired
    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(path = "/{id}")
    public Mono<Profile> getProfileById(@PathVariable UUID id) {
        return Mono.just(profileService.loadProfileWithId(new ProfileId(id)));
    }

}
