package net.colbourn.carepriorities.plugins.LocalDatabase;

import net.colbourn.carepriorities.api.DiaryProvider;
import net.colbourn.carepriorities.model.Event;
import net.colbourn.carepriorities.model.EventType;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.EventDSO;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.EventTypeDSO;

import javax.inject.Singleton;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class LocalDatabaseDiaryProvider implements DiaryProvider {

    private Box<EventDSO> eventbox;
    private Box<EventTypeDSO> eventTypeBox;

    LocalDatabaseDiaryProvider()
    {
        BoxStore boxStore = new LocalDatabaseProvider().getBoxStore();
        eventbox = boxStore.boxFor(EventDSO.class);
        eventTypeBox = boxStore.boxFor(EventTypeDSO.class);
    }

    private EventTypeDSO getEventTypeDSO(long id)
    {
        return eventTypeBox.get(id);
    }


    public Event dsoToEvent(EventDSO dso)
    {
        Event event = new Event();
        event.setTime(dso.getTime());
        event.setEventType(dsoToEventType(getEventTypeDSO(dso.getEventType())));
        event.setName(dso.getName());
        event.setEventDuration(dso.getEventDuration());
        event.setReoccurence(dso.getReoccurence());
        return event;
    }

    public EventDSO eventToDSO(Event event)
    {
        EventDSO eventDSO = new EventDSO();
        eventDSO.setTime(event.getTime());
        eventDSO.setEventType(event.getEventType().getId());
        eventDSO.setName(event.getName());
        eventDSO.setEventDuration(event.getEventDuration());
        eventDSO.setReoccurence(event.getReoccurence());
        return eventDSO;
    }


    public EventType dsoToEventType(EventTypeDSO dso)
    {
        EventType eventtype = new EventType();
        eventtype.setParentType(dsoToEventType(getEventTypeDSO(dso.getParentType())));
        return eventtype;
    }


    public EventTypeDSO eventTypeToDSO(EventType eventType)
    {
        EventTypeDSO dso = new EventTypeDSO();
        dso.setParentType(eventType.getParentType().getId());
        return dso;
    }

}
