package net.colbourn.carepriorities.api;

public interface EventType {

    long getId();

    void setParentType(EventType eventType);

    EventType getParentType();
}
