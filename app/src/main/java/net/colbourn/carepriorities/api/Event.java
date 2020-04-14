package net.colbourn.carepriorities.api;


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

    public Reoccurrence getReoccurrence();

    public void setReoccurrence(Reoccurrence reoccurence);

    String getIcon();

    public void setIcon(String icon);

    public enum REOCCURENCE_TYPES { MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS }

}
