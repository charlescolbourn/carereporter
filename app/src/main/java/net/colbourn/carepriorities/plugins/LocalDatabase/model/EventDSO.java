/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.plugins.LocalDatabase.model;

import java.io.Serializable;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class EventDSO implements Serializable
{
    @Id
    private long id;


    private Date time;
    private long eventType;
    private String name;
    private Long eventDuration;
    private String icon;

    private ToOne<ReoccurrenceDSO> reoccurrenceDSO;


    //icon (cached & transient?)

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public long getEventType() {
        return eventType;
    }

    public void setEventType(long eventType) {
        this.eventType = eventType;
    }

    public void setIcon(String icon) { this.icon = icon; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Long eventDuration) {
        this.eventDuration = eventDuration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIcon () { return this.icon; }


    public ToOne<ReoccurrenceDSO> getReoccurrenceDSO() {
        return reoccurrenceDSO;
    }

    public void setReoccurrenceDSO(ReoccurrenceDSO reoccurrenceDSO) {
        this.reoccurrenceDSO.setTarget(reoccurrenceDSO);
    }
}