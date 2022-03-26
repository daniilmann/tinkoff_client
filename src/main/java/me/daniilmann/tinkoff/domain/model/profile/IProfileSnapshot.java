package me.daniilmann.tinkoff.domain.model.profile;

import java.util.UUID;

public interface IProfileSnapshot {

    public UUID getProfileId();
    public UUID getUserId();
    public String getName();
    public String getToken();
    public Boolean isReal();

    public void setProfileId(UUID profileId);
    public void setUserId(UUID userId);
    public void setName(String name);
    public void setToken(String token);
    public void setReal(Boolean real);

}
