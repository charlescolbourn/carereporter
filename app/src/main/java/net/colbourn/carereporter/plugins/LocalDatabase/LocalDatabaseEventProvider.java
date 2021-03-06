package net.colbourn.carereporter.plugins.LocalDatabase;

import android.util.Log;

import net.colbourn.carereporter.api.Event;
import net.colbourn.carereporter.api.EventProvider;
import net.colbourn.carereporter.model.DiaryEvent;
import net.colbourn.carereporter.plugins.LocalDatabase.model.EventDSO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class LocalDatabaseEventProvider implements EventProvider {
    Box<EventDSO> box;

    public LocalDatabaseEventProvider() {
        BoxStore boxStore = new LocalDatabaseProvider().getBoxStore();
        box = boxStore.boxFor(EventDSO.class);
    }



    @Override
    public List<Event> getAll() {
        return convertDSOsToEvents(box.getAll());
    }

    @Override
    public List<Event> getForDateAndClient(Date date, long clientid) {
        return getAll();
    }

    @Override
    public void save(Event event) {
        Log.v(LocalDatabaseEventProvider.class.getName(),"writing event with name " + event.getName());
        EventDSO eventDSO = convertEventToDSO(event);
        box.put(eventDSO);
    }

    private List<Event> convertDSOsToEvents(List<EventDSO> eventDSOs) {
        Log.v(LocalDatabaseEventProvider.class.getName(),"Converting " + eventDSOs.size() +" dsos to Events");
        List<Event> events = new ArrayList<>();
        for (EventDSO dso : eventDSOs)
        {
            events.add(convertDSOToEvent(dso));
        }
        return events;
    }

    private Event convertDSOToEvent(EventDSO eventDSO)
    {
        Event event = new DiaryEvent(eventDSO.getName());
        event.setIcon(eventDSO.getIcon());
        event.setTime(eventDSO.getTime());
        event.setDescription(eventDSO.getDescription());
        Log.v(LocalDatabaseEventProvider.class.getName(),"Id of eventtype is " + eventDSO.getEventType());
        event.setEventType(JSONEventTypeProvider.getEventTypeFromId(eventDSO.getEventType()));
        event.setReoccurrence(eventDSO.getReoccurrence());
//        event.setPhoto(clientDSO.getPhoto());
        return event;
    }
    private EventDSO convertEventToDSO(Event event)
    {
        EventDSO dso = new EventDSO();
        dso.setName(event.getName());
        dso.setEventDuration(event.getEventDuration());
        dso.setEventType(event.getEventType().getId());
        dso.setIcon(event.getIcon());
        dso.setTime(event.getTime());
        dso.setDescription(event.getDescription());
        //TODO sort out reoccurence
        dso.setReoccurrence(event.getReoccurrence());
//        dso.setPhoto(client.getPhoto());
        return dso;
    }

}
