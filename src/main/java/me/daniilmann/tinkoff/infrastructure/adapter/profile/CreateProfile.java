package me.daniilmann.tinkoff.infrastructure.adapter.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Setter;
import me.daniilmann.tinkoff.infrastructure.adapter.common.Command;
import me.daniilmann.tinkoff.infrastructure.adapter.profile.exception.InvalidCreateProfileException;
import org.springframework.util.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Setter
public class CreateProfile implements Command {

    private String userId;
    private String name;
    private String token;
    private String real;

    private CreateProfile() {}

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

    public void validate() throws InvalidCreateProfileException {
        if (!StringUtils.hasLength(this.userId)
            || !StringUtils.hasLength(this.name)
            || !StringUtils.hasLength(this.token)
            || !StringUtils.hasLength(this.real)) {
            throw new InvalidCreateProfileException();
        }
    }
}
