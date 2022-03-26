package me.daniilmann.tinkoff.domain.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

public interface IEventSnapshot {

    public UUID getId();
    public Class<? extends Entity> getAggregateClass();
    public String getAggregateId();
    public ZonedDateTime getTimestamp();
    public Integer getVersion();
    public Map<String, String> getFields();

    public void setAggregateClass(Class<? extends Entity> entityClass);
    public void setAggregateId(String aggregateId);
    public void setTimestamp(ZonedDateTime timestamp);
    public void setVersion(Integer version);

}
