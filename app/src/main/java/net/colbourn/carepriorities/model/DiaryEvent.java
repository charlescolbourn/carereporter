/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;


import net.colbourn.carepriorities.api.Event;
import net.colbourn.carepriorities.api.EventType;

import java.util.Date;



public class DiaryEvent implements Event
{
    private Date time;
    private EventType eventType;
    private String name;
    private Long eventDuration;
    private EventReoccurrence reoccurrence;
    private String icon;
    private String description;

    public DiaryEvent() {
    }

    public DiaryEvent(String name) {
        this.name = name;
    }

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

    @Override
    public EventReoccurrence getReoccurrence() {
        return this.reoccurrence;
    }


    @Override
    public void setReoccurrence(EventReoccurrence reoccurrence) {
        this.reoccurrence = reoccurrence;
    }


    public enum REOCCURENCE_TYPES { MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS }

    public void setIcon( String icon ) { this.icon = icon; }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getIcon() { return this.icon; }


    //icon (cached & transient?)
}