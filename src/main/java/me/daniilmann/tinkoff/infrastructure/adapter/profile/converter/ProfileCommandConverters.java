package me.daniilmann.tinkoff.infrastructure.adapter.profile.converter;

import me.daniilmann.tinkoff.domain.model.exception.UserIdParseException;
import me.daniilmann.tinkoff.domain.model.profile.Profile;
import me.daniilmann.tinkoff.domain.model.profile.ProfileId;
import me.daniilmann.tinkoff.domain.model.profile.UserId;
import me.daniilmann.tinkoff.domain.model.profile.exception.EmptyProfileNameException;
import me.daniilmann.tinkoff.domain.model.profile.exception.EmptyProfileTokenException;
import me.daniilmann.tinkoff.domain.model.profile.exception.ProfileIdParseException;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.CreateProfile;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.UpdateProfile;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.exception.InvalidProfileIdException;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.exception.InvalidProfileNameException;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.exception.InvalidProfileTokenException;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.exception.InvalidProfileUserIdException;

import java.util.UUID;

public class ProfileCommandConverters {

    public static Profile convert(CreateProfile createProfile) throws InvalidProfileIdException, InvalidProfileUserIdException, InvalidProfileNameException, InvalidProfileTokenException {
        Profile.Builder profileBuilder = Profile.createBuilder();
        try {
            profileBuilder.setId(new ProfileId(UUID.randomUUID()));
            profileBuilder.setUserId(UserId.fromString(createProfile.userId()));
            profileBuilder.setName(createProfile.name());
            profileBuilder.setToken(createProfile.token());
            profileBuilder.setReal(createProfile.isReal());
            return profileBuilder.build();
        } catch (ProfileIdParseException e) {
            throw new InvalidProfileIdException(e);
        } catch (UserIdParseException e) {
            throw new InvalidProfileUserIdException(e);
        } catch (EmptyProfileNameException e) {
            throw new InvalidProfileNameException(e);
        } catch (EmptyProfileTokenException e) {
            throw new InvalidProfileTokenException(e);
        }
    }

    public static Profile convert(UpdateProfile updateProfile) throws InvalidProfileIdException, InvalidProfileUserIdException, InvalidProfileNameException, InvalidProfileTokenException {
        Profile.Builder profileBuilder = Profile.createBuilder();
        try {
            profileBuilder.setId(ProfileId.fromString(updateProfile.id()));
            profileBuilder.setUserId(UserId.fromString(updateProfile.userId()));
            profileBuilder.setName(updateProfile.name());
            profileBuilder.setToken(updateProfile.token());
            profileBuilder.setReal(updateProfile.isReal());
            return profileBuilder.build();
        } catch (ProfileIdParseException e) {
            throw new InvalidProfileIdException(e);
        } catch (UserIdParseException e) {
            throw new InvalidProfileUserIdException(e);
        } catch (EmptyProfileNameException e) {
            throw new InvalidProfileNameException(e);
        } catch (EmptyProfileTokenException e) {
            throw new InvalidProfileTokenException(e);
        }
    }

}
