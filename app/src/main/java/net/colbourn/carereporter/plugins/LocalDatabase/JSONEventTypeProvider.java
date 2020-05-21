package net.colbourn.carereporter.plugins.LocalDatabase;

import android.content.Context;

import net.colbourn.carereporter.api.EventType;
import net.colbourn.carereporter.utils.JSONUtils;

import java.util.List;

public class JSONEventTypeProvider {

    private static String EventTypeFilename = "EventTypes.json";
    private static List<EventType> EVENT_TYPES;

    public static List<EventType> getEventTypes(Context context) {
        if (EVENT_TYPES==null)
            EVENT_TYPES = JSONUtils.getListOfEventTypes(context, EventTypeFilename);
        return EVENT_TYPES;
    }

    public static EventType getEventTypeFromId(long id) {
        EventType returnType = null;
        for (EventType eventType: EVENT_TYPES){
            if (eventType.getId()==id){
                returnType = eventType;
            }
        }
        return returnType;
    }
}
