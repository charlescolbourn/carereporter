package net.colbourn.carepriorities.api;

import java.util.Date;
import java.util.List;

public interface EventProvider {
    List<Event> getAll();
    List<Event> getForDateAndClient(Date date, long clientid);
    void save(Event event);
}
