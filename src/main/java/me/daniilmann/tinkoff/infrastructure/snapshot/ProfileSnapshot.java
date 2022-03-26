package me.daniilmann.tinkoff.infrastructure.snapshot;

import lombok.Getter;
import lombok.Setter;
import me.daniilmann.tinkoff.domain.model.profile.IProfileSnapshot;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity(name = "Profile")
@Table(name = "profile")
@Getter
@Setter
public class ProfileSnapshot implements IProfileSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @NotNull(message = "Id must be not null")
    private UUID profileId;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer version;

    @NotNull
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private ZonedDateTime versionTimestamp;

    @NotNull(message = "UserId must be not null")
    @Column(updatable = false)
    private UUID userId;

    @NotNull(message = "Name must be not null")
    @Column(length = 50)
    private String name;

    // TODO Find parameters of Tinkoff Token
    @NotNull(message = "Token must be not null")
    private String token;

    @Column(name = "is_real", nullable = false)
    private Boolean real;

    @Override
    public Boolean isReal() {
        return real;
    }
}
