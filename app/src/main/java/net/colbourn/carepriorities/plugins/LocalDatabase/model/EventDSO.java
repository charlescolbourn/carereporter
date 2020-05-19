/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.plugins.LocalDatabase.model;

import net.colbourn.carepriorities.model.EventReoccurrence;

import java.io.Serializable;

import java.util.Date;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;

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
    private String description;

    @Convert(converter = ReoccurrenceConverter.class, dbType = String.class)
    private EventReoccurrence reoccurrence;


    public EventReoccurrence getReoccurrence() {
        return reoccurrence;
    }

    public void setReoccurrence(EventReoccurrence reoccurrence) {
        this.reoccurrence = reoccurrence;
    }


;


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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class ReoccurrenceConverter implements PropertyConverter<EventReoccurrence, String> {

        @Override
        public EventReoccurrence convertToEntityProperty(String databaseValue) {
            return databaseValue != null ? EventReoccurrence.valueOf(databaseValue) : EventReoccurrence.SINGLE_EVENT; // bad, remove this when we have clean consistent data TODO
        }

        @Override
        public String convertToDatabaseValue(EventReoccurrence entityProperty) {
            return entityProperty.name();
        }
    }
}