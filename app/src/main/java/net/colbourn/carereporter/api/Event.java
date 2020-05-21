package net.colbourn.carereporter.api;


import net.colbourn.carereporter.model.EventReoccurrence;

import java.io.Serializable;
import java.util.Date;

public interface Event extends Serializable {

    public Date getTime();

    public void setTime(Date time);

    public EventType getEventType();

    public void setEventType(EventType eventType);

    public String getName();

    public void setName(String name);

    public Long getEventDuration();

    public void setEventDuration(Long eventDuration);

    public EventReoccurrence getReoccurrence();

    public void setReoccurrence(EventReoccurrence reoccurence);

    String getIcon();

    public void setIcon(String icon);

    public void setDescription(String description);

    public String getDescription();

    public enum REOCCURENCE_TYPES { MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS }

}
