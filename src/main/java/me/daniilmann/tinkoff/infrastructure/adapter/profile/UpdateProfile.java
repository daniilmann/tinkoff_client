package me.daniilmann.tinkoff.infrastructure.adapter.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Setter;
import me.daniilmann.tinkoff.infrastructure.adapter.common.Command;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.exception.InvalidCreateProfileException;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.exception.InvalidUpdateProfileException;
import org.springframework.util.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Setter
public class UpdateProfile implements Command {

    private String id;
    private String userId;
    private String name;
    private String token;
    private String real;

    private UpdateProfile() {}

    public String id() {
        return id;
    }

    public String userId() {
        return userId;
    }

    public String name() {
        return name;
    }

    public String token() {
        return token;
    }

    public String isReal() {
        return real;
    }

    public void validate() throws InvalidUpdateProfileException {
        if (!StringUtils.hasLength(this.id)
            || (!StringUtils.hasLength(this.userId)
            && !StringUtils.hasLength(this.name)
            && !StringUtils.hasLength(this.token)
            && !StringUtils.hasLength(this.real))) {
            throw new InvalidUpdateProfileException();
        }
    }
}
