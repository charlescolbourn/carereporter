/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;

import java.io.Serializable;

public class EventType implements Serializable
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