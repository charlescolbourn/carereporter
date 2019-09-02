/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;

import org.joda.time.Duration;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;


public class Event implements Serializable
{
    private Date time;
    private EventType eventType;
    private String name;
    private Long eventDuration;
    private Reoccurrence reoccurrence;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

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

    public Reoccurrence getReoccurrence() {
        return reoccurrence;
    }

    public void setReoccurrence(Reoccurrence reoccurence) {
        this.reoccurrence = reoccurence;
    }


    //icon (cached & transient?)
}