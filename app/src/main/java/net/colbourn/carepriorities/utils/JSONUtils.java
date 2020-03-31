package net.colbourn.carepriorities.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;

import net.colbourn.carepriorities.api.EventType;
import net.colbourn.carepriorities.model.DiaryEventType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    public static List<EventType> getListOfEventTypes(Context context, String filename) throws IOException, JSONException {
        List<EventType> eventTypes = new ArrayList<>();
        JSONObject parentobj = new JSONObject(readFileToString(context,filename));
        JSONArray eventTypesJson = parentobj.getJSONArray("EventTypes");

        for (int i=0; i<eventTypesJson.length(); i++) {
            EventType e = new DiaryEventType();
            JSONObject ob = eventTypesJson.getJSONObject(i);
            e.setName(ob.getString("name"));
            e.setDefaultDuration(ob.getInt("defaultDuration"));
            e.setDefaultIcon(ob.getString("defaultIcon"));

            eventTypes.add(e);
        }
        return eventTypes;
    }


    public static String readFileToString(Context context, String filename) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(filename);
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }
}
