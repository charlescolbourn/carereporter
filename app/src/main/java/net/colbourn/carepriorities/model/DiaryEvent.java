/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;


import net.colbourn.carepriorities.api.Event;
import net.colbourn.carepriorities.api.EventType;
import net.colbourn.carepriorities.api.Reoccurrence;

import java.util.Date;



public class DiaryEvent implements Event
{
    private Date time;
    private EventType eventType;
    private String name;
    private Long eventDuration;
    private Reoccurrence reoccurrence;
    private String icon;

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

    public enum REOCCURENCE_TYPES { MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS }

    public String getIcon() { return this.icon; }


    //icon (cached & transient?)
}