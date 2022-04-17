package me.daniilmann.tinkoff.infrastructure.adapter.profile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.daniilmann.tinkoff.domain.model.profile.Profile;
import me.daniilmann.tinkoff.domain.model.profile.UserId;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect
@Setter
@Getter
public class JsonProfile {

    private String id;
    private String userId;
    private String name;
    private String token;
    private Boolean real;

    private JsonProfile(){}

    public JsonProfile(String id, String userId, String name, String token, Boolean real) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.token = token;
        this.real = real;
    }

    public JsonProfile(Profile profile) {
        id = profile.id().toString();
        userId = profile.userId().toString();
        name = profile.name();
        token = profile.token();
        real = profile.isReal();
    }
}
