/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.plugins.LocalDatabase.model;

import android.util.Pair;

import java.io.Serializable;

import java.util.Date;

import org.joda.time.Duration;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class EventDSO implements Serializable
{
    @Id
    private Long id;

    private Date time;
    private long eventType;
    private String name;

    public long getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(long eventDuration) {
        this.eventDuration = eventDuration;
    }

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

    public Pair<Integer, String> getReoccurence() {
        return Pair.create(getReoccurenceNumber(), getReoccurenceUnits());
    }

    public void setReoccurence(int reoccurenceNumber, String reoccurenceUnits) {
        this.setReoccurenceNumber(reoccurenceNumber);
        this.setReoccurenceUnits(reoccurenceUnits);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReoccurenceNumber() {
        return reoccurenceNumber;
    }

    public void setReoccurenceNumber(int reoccurenceNumber) {
        this.reoccurenceNumber = reoccurenceNumber;
    }

    public String getReoccurenceUnits() {
        return reoccurenceUnits;
    }

    public void setReoccurenceUnits(String reoccurenceUnits) {
        this.reoccurenceUnits = reoccurenceUnits;
    }
}