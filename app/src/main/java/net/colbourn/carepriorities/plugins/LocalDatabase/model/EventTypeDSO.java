/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.plugins.LocalDatabase.model;

import net.colbourn.carepriorities.model.Event;
import net.colbourn.carepriorities.model.EventType;

import java.io.Serializable;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;

@Entity
public class EventTypeDSO implements Serializable
{
    @Id
    private long id;

    private long parentType;

    private String icon;

    public long getParentType() {
        return parentType;
    }

    public void setParentType(long parentType) {
        this.parentType = parentType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}