package net.colbourn.carepriorities.plugins.LocalDatabase;

import net.colbourn.carepriorities.api.Event;
import net.colbourn.carepriorities.api.EventProvider;
import net.colbourn.carepriorities.model.DiaryEvent;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalDatabaseEventProvider implements EventProvider {
    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        for (int i=0;i<10;i++){
            Event e = new DiaryEvent();
            e.setName("badger");
            e.setTime(new Date(System.currentTimeMillis()));
            events.add(e);
        }
        return events;
    }
}
