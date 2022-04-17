package me.daniilmann.tinkoff.domain.model.profile;

import me.daniilmann.tinkoff.domain.model.Event;
import me.daniilmann.tinkoff.domain.model.exception.NullIdException;
import me.daniilmann.tinkoff.domain.model.profile.exception.EmptyProfileIdException;
import me.daniilmann.tinkoff.domain.model.profile.exception.ProfileIdParseException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    private static Stream<Arguments> getValidProfileArguments() {
        return Stream.of(
                Arguments.of(UUID.randomUUID(),
                        UUID.randomUUID(),
                        RandomStringUtils.random(10),
                        RandomStringUtils.random(10),
                        true),
                Arguments.of(UUID.randomUUID(),
                        UUID.randomUUID(),
                        RandomStringUtils.random(10),
                        RandomStringUtils.random(10),
                        false)
                );
    }

    @ParameterizedTest
    @MethodSource(value = "getValidProfileArguments")
    void profileCreation_ValidArgs_Success(UUID profileUUID, UUID userUUID, String name, String token, Boolean real) {
        ProfileId profileId = new ProfileId(profileUUID);
        UserId userId = new UserId(userUUID);
        Profile profile = new Profile(profileId, userId, name, token, real);

        assertNotNull(profile.getNewEvents(), "No events generated");
        assertEquals(1, profile.getNewEvents().size(), "More than 1 event were generated");

        Event event = profile.getNewEvents().get(0);
        assertEquals(ProfileCreated.class, event.getClass(), "Wrong event was generated");

        assertEquals(profileId, profile.id(), "ProfileId mismatch");
        assertEquals(userId, profile.userId(), "UserId mismatch");
        assertEquals(name, profile.name(), "Name mismatch");
        assertEquals(token, profile.token(), "Token mismatch");
        assertEquals(real, profile.isReal(), "Real mismatch");
    }

    @Test
    @DisplayName("Успешный тест изменения имени профиля")
    void changeName_ValidArgs_Succes() {

        String originalName = "testName";
        String newName = "newName";

        Profile profile = new Profile(new ProfileId(UUID.randomUUID()),
                new UserId(UUID.randomUUID()),
                originalName,
                "testToken",
                false);
        profile.changeName(newName);

        assertNotNull(profile.getNewEvents(), "No events generated");
        assertEquals(2, profile.getNewEvents().size(), "More than 1 event were generated");

        Event event = profile.getNewEvents().get(1);
        assertEquals(ProfileNameChanged.class, event.getClass(), "Wrong event was generated");

        ProfileNameChanged profileNameChanged = (ProfileNameChanged) event;
        assertEquals(newName, profileNameChanged.name(), "Wrong name in event");
        assertEquals(newName, profile.name(), "Name is not changed");

    }

    @Test
    @Disabled("Not implemented yet")
    void changeToken() {
    }

    @RepeatedTest(10)
    void profileIdCreation_ValidArgs_Success() {
        UUID randomId = UUID.randomUUID();
        ProfileId profileId = new ProfileId(randomId);
        assertEquals(randomId, profileId.id());
    }

    @ParameterizedTest
    @NullSource
    void profileIdCreation_NullValue_NullIdException(UUID uuid) {
        NullIdException exception = assertThrows(NullIdException.class, () -> new ProfileId(uuid));
        assertEquals(ProfileId.class, exception.getSourceClass());
    }

    @RepeatedTest(10)
    void profileIdCreationfromString_ValidUUIDStrings_Success() {
        String randomId = UUID.randomUUID().toString();
        ProfileId profileId = ProfileId.fromString(randomId);
        assertEquals(randomId, profileId.id().toString());
    }

    @RepeatedTest(10)
    void profileIdfromString_InvalidArgs_ProfileIdParseException() {
        String id = RandomStringUtils.random(10);
        assertThrows(ProfileIdParseException.class, () -> ProfileId.fromString(id));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void profileIdfromString_NullAndEmptyArgs_ProfileIdParseException(String id) {
        assertThrows(ProfileIdParseException.class, () -> ProfileId.fromString(id));
    }

    @Test
    void profileCreation_NullId_EmptyProfileIdException() {
        assertThrows(EmptyProfileIdException.class,
                () -> new Profile(null, null, null, null, null));
    }
}