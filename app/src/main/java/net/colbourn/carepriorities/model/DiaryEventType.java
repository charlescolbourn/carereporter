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

    public EventType getParentType() {
        return parentType;
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