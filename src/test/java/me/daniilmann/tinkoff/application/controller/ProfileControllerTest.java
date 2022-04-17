package me.daniilmann.tinkoff.application.controller;

import me.daniilmann.tinkoff.infrastructure.adapter.profile.JsonProfile;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.ProfileController;
import me.daniilmann.tinkoff.application.service.profile.ProfileService;
import me.daniilmann.tinkoff.domain.model.EventStore;
import me.daniilmann.tinkoff.domain.model.profile.Profile;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;
import me.daniilmann.tinkoff.domain.model.profile.ProfileRepository;
import me.daniilmann.tinkoff.domain.model.profile.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;
import java.util.UUID;

@WebFluxTest(controllers = ProfileController.class)
@Import(value = {ProfileService.class})
public class ProfileControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProfileRepository profileRepository;

    @MockBean
    private EventStore eventStore;

    private Profile generateProfile() {
        Profile profile = new Profile(
                new ProfileId(UUID.randomUUID()),
                new UserId(UUID.randomUUID()),
                "name",
                "token",
                false
        );
        return profile;
    }

    @Test
    @WithMockUser
    void findAll_NoProfiles() {
        webTestClient.get()
                .uri("/profiles")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(JsonProfile.class);
    }

    @Test
    @WithMockUser
    void findAll_WithProfiles() {
        Profile profile = generateProfile();
        Mockito.when(profileRepository.findAll()).thenReturn(Collections.singletonList(profile));

        webTestClient.get()
                .uri("/profiles")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(JsonProfile.class);
    }


    @Test
    @WithMockUser
    void findByProfileId_Success() {
        Profile profile = generateProfile();
        Mockito.when(profileRepository.getById(Mockito.any(ProfileId.class))).thenReturn(profile);

        webTestClient.get()
                .uri("/profiles/" + profile.id())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(profile.id().toString())
                .jsonPath("$.userId").isEqualTo(profile.userId().toString())
                .jsonPath("$.name").isEqualTo(profile.name())
                .jsonPath("$.token").isEqualTo(profile.token())
                .jsonPath("$.real").isEqualTo(profile.isReal().toString());
    }

}
