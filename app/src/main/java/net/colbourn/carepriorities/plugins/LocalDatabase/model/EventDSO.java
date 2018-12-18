/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.plugins.LocalDatabase.model;

import java.io.Serializable;

import java.time.ZonedDateTime;
import java.util.Date;

import net.colbourn.carepriorities.model.Event;
import net.colbourn.carepriorities.model.EventType;

import org.joda.time.Duration;

import io.objectbox.annotation.Entity;

@Entity
public class EventDSO implements Serializable
{
    private Date time;
    private long eventType;
    private String name;
    private long eventDuration;
    private int reoccurenceNumber;
    private String reoccurenceUnits;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getEventDuration() {
        return Duration.millis(eventDuration);
    }

    public void setEventDuration(Duration eventDuration) {
        this.eventDuration = eventDuration.getMillis();
    }

    public Duration getReoccurence() {
        return Duration.of(reoccurenceNumber, reoccurenceUnits);
    }

    public void setReoccurence(Duration reoccurence) {
        this.reoccurence = reoccurence;
    }



}