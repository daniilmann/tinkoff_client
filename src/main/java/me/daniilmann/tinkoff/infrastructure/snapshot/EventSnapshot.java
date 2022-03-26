package me.daniilmann.tinkoff.infrastructure.snapshot;

import lombok.Getter;
import lombok.Setter;
import me.daniilmann.tinkoff.domain.model.IEventSnapshot;
import me.daniilmann.tinkoff.domain.model.IdValue;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity(name = "Event")
@Table(name = "event")
@NotNull
@Getter
@Setter
public class EventSnapshot implements IEventSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private Class<? extends me.daniilmann.tinkoff.domain.model.Entity> aggregateClass;
    private String aggregateId;

    private ZonedDateTime timestamp;
    private Integer version;

    @ElementCollection
    @CollectionTable(name = "event_fields",
    joinColumns = {
            @JoinColumn(name = "event_id", referencedColumnName = "id")
    })
    @MapKeyColumn(name = "field")
    @Column(name = "value")
    private Map<String, String> fields = new HashMap<>();

}
