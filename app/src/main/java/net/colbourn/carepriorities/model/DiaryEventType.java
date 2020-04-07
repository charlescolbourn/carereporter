/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;

import net.colbourn.carepriorities.api.EventType;

import java.io.Serializable;

public class DiaryEventType implements EventType, Serializable
{
    private long id;

    private EventType parentType;
    private String name;
    private String iconName;
    private int defaultDuration;

    public EventType getParentType() {
        return parentType;
    }

    @Override
    public String getDefaultIcon() {
        return iconName;
    }

    @Override
    public long getDefaultDuration() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDefaultIcon(String iconName) {
        this.iconName = iconName;
    }

    @Override
    public void setDefaultDuration(int minutes) {
        this.defaultDuration = minutes;
    }

    public void setParentType(EventType parentType) {
        this.parentType = parentType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //Icon
}