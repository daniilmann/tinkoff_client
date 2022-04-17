package me.daniilmann.tinkoff.infrastructure.adapter.profile;

import me.daniilmann.tinkoff.application.service.profile.IProfileService;
import me.daniilmann.tinkoff.domain.model.exception.UserIdParseException;
import me.daniilmann.tinkoff.domain.model.profile.UserId;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.exception.InvalidProfileUserIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/users")
public class UserProfileController {

    private IProfileService profileService;

    @Autowired
    public UserProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(path = "/{userId}/profiles")
    public List<JsonProfile> getProfilesByUserId(@PathVariable String userId) throws InvalidProfileUserIdException {
        try {
            return profileService.loadProfileWithUserId(UserId.fromString(userId)).stream()
                    .map(JsonProfile::new)
                    .collect(Collectors.toList());
        } catch (UserIdParseException e) {
            throw new InvalidProfileUserIdException(e);
        }
    }

}
