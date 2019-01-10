package net.colbourn.carepriorities.plugins.LocalDatabase;

import android.util.Pair;

import net.colbourn.carepriorities.api.DiaryProvider;
import net.colbourn.carepriorities.model.Event;
import net.colbourn.carepriorities.model.EventType;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.EventDSO;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.EventTypeDSO;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

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
        event.setEventDuration(Duration.millis(dso.getEventDuration()));
        Pair<Integer, String> reoccurence = dso.getReoccurence();
        event.setReoccurence(convertReoccurencePairToDuration(reoccurence.first, reoccurence.second));
        return event;
    }

    public EventDSO eventToDSO(Event event)
    {
        EventDSO eventDSO = new EventDSO();
        eventDSO.setTime(event.getTime());
        eventDSO.setEventType(event.getEventType().getId());
        eventDSO.setName(event.getName());
        eventDSO.setEventDuration(event.getEventDuration().getMillis());
        Pair<Integer, String> reoccurnce = convertDurationToReoccurencePair(event.getReoccurence());
        eventDSO.setReoccurence(reoccurnce.first,reoccurnce.second);
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

    public Duration convertReoccurencePairToDuration(int number, String reoccurenceType)
    {
        Event.REOCCURENCE_TYPES reoccurenceTypeValue = getEnumReoccurenceValue(reoccurenceType);
        Period period = new Period();
        switch (reoccurenceTypeValue)
        {
            case DAYS:
                period.withDays(number);
                break;
            case WEEKS:
                period.withWeeks(number);
                break;
            case MONTHS:
                period.withMonths(number);
                break;
            case YEARS:
                period.withYears(number);
                break;
            case HOURS:
                period.withHours(number);
                break;
            case MINUTES:
                period.withMinutes(number);
                break;
            default:
                throw new RuntimeException("Unsupported date type used");
        }
        return new Duration(period);
    }

    public Event.REOCCURENCE_TYPES getEnumReoccurenceValue(String type)
    {
        for (Event.REOCCURENCE_TYPES candidateType : Event.REOCCURENCE_TYPES.values())
        {
            if (candidateType.name().equals(type))
                return candidateType;

        }
        return null;
    }
    public Pair<Integer, String> convertDurationToReoccurencePair(Duration duration)
    {
        return new Pair<>(0,"foo");
    }
}
