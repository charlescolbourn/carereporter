package net.colbourn.carepriorities.api;

public interface EventType {

    long getId();

    void setParentType(EventType eventType);

    EventType getParentType();

    String getDefaultIcon();

    long getDefaultDuration();

    String getName();

    void setName(String name);

    void setDefaultIcon(String iconName);

    void setDefaultDuration(int minutes);
}
