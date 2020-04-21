package net.colbourn.carepriorities.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.Event;
import net.colbourn.carepriorities.api.EventProvider;
import net.colbourn.carepriorities.api.EventType;
import net.colbourn.carepriorities.api.Reoccurrence;
import net.colbourn.carepriorities.model.DiaryEvent;
import net.colbourn.carepriorities.model.EventReoccurrence;
import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseEventProvider;
import net.colbourn.carepriorities.utils.ImageUtils;
import net.colbourn.carepriorities.utils.JSONUtils;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewEditEvent extends Activity {


    private EventProvider eventProvider;
    private static String EventTypeFilename = "EventTypes.json";
    private List<EventType> eventTypes;
    private EventType selectedEventType;
    private Calendar eventDate = Calendar.getInstance();
    private Event selectedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_entry_edit);
        eventProvider = new LocalDatabaseEventProvider();
        selectedEvent = (Event) getIntent().getSerializableExtra("event");
        if (selectedEvent == null) {
            setContentView(R.layout.diary_entry_edit);
            populateReoccurenceSelector();
            initialiseEventTypeSelector();
            initialiseDatePicker();
            initialiseTimePicker();
            initialiseButtons();
        }
        else {
            setContentView(R.layout.diary_entry_view);
            populateExistingView();
        }



    }

    private void populateExistingView() {
        ((TextView) findViewById(R.id.eventNameView)).setText(selectedEvent.getName());
        setIcon( ((ImageView) findViewById(R.id.eventIconView)), selectedEvent.getIcon());
    }


    private void populateReoccurenceSelector() {
        List<String> spinnerArray =  new ArrayList<String>();
        for (Reoccurrence.PeriodicityOption option : EventReoccurrence.PeriodicityOption.values()) {
            spinnerArray.add(option.name());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.eventReoccurrence);
        sItems.setAdapter(adapter);
    }

    private void initialiseEventTypeSelector() {
        //TODO fetch from DB
//        String addNewTypeString = "Add new type";
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.addAll(getEventTypeNames());


        //TODO get the maltaisn icon picker working
//        spinnerArray.add(addNewTypeString);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.selectorEventType);
        Log.v(ViewEditEvent.class.getName(),"Setting adapter with " + spinnerArray.size() + " elements");
        sItems.setAdapter(adapter);

        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View arg1, int i, long arg3) {
                Log.v(ViewEditEvent.class.getName(), "item at pos " + i);
//                if (sItems.getItemAtPosition(i).equals(addNewTypeString)) {
//                    createNewEventType();
//                }
                setEventType(spinnerArray.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
    }

    private void createNewEventType() {
        Intent intent = new Intent(this, ViewEditEventType.class);
        startActivity(intent);
    }

    private List<String> getEventTypeNames() {
        List<String> eventTypeNameList = new ArrayList<>();
        if (eventTypes==null) {
            try {
                eventTypes = JSONUtils.getListOfEventTypes(this, EventTypeFilename);
            } catch (IOException e) {
                Log.e(ViewEditEvent.class.getName(), "Can't get built in event types", e);
            } catch (JSONException e) {
                Log.e(ViewEditEvent.class.getName(), "Can't get built in event types", e);
            }
        }

        if (eventTypes != null) {
            for (EventType e : eventTypes) {
                Log.v(ViewEditEvent.class.getName(),"adding name " + e.getName());
                eventTypeNameList.add(e.getName());
            }
        }
        return eventTypeNameList;
    }

    private void initialiseDatePicker() {
        View eventDateText = findViewById(R.id.textEventDate);
        Calendar today = Calendar.getInstance();
        eventDateText.setOnClickListener(v ->
                new DatePickerDialog( ViewEditEvent.this, (view, year, month, dayOfMonth) ->
                        setDate(year, month, dayOfMonth), today.get(Calendar.YEAR),today.get(Calendar.MONTH)+1,today.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void initialiseTimePicker() {
        View eventTimeText = findViewById(R.id.textEventTime);
        Calendar today = Calendar.getInstance();
        eventTimeText.setOnClickListener(v ->
                new TimePickerDialog( ViewEditEvent.this, (view, hourOfDay, minute) ->
                        setTime(hourOfDay, minute), today.get(Calendar.HOUR), today.get(Calendar.MINUTE), true).show());
    }


    public void setDate(int year, int month, int dayOfMonth) {
        eventDate.set(Calendar.YEAR,year);
        eventDate.set(Calendar.MONTH,month);
        eventDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        ((TextView)findViewById(R.id.textEventDate)).setText(DateFormat.getDateInstance().format(eventDate.getTime()));
    }

    public void setTime(int hour, int minute) {
        eventDate.set(Calendar.HOUR,hour);
        eventDate.set(Calendar.MINUTE, minute);
        ((TextView)findViewById(R.id.textEventTime)).setText(hour < 10 ? "0" + hour : hour + ":" + minute);
    }

    public void setEventType(String eventName) {
        for(EventType e : eventTypes) {
            if (e.getName().equals(eventName))
                    selectedEventType = e;
        }
        if (selectedEventType != null) {
            ImageView icon = (ImageView) findViewById(R.id.eventIcon);
            setIcon(icon,selectedEventType.getDefaultIcon());
        }
    }

    private void setIcon (ImageView icon, String iconName) {
        String path = ImageUtils.cacheIcon(this.getApplicationContext(),iconName);
        Bitmap iconBitmap = BitmapFactory.decodeFile(path);
        icon.setImageBitmap(iconBitmap);
    }




    private void save() {
        Log.v(ViewEditEvent.class.getName(),"Writing event");
        Event e = new DiaryEvent();
        e.setName(((TextView)findViewById(R.id.eventName)).getText().toString());
        e.setEventDuration(Long.getLong( ((TextView)findViewById(R.id.textEventDuration)).getText().toString()));
        e.setEventType(selectedEventType);
        e.setTime(eventDate.getTime());
        e.setIcon(selectedEventType.getDefaultIcon());
//        e.setReoccurrence(Reoccurrence.PeriodicityOption.valueOf( ((Spinner)findViewById(R.id.eventReoccurrence)).getSelectedItem().toString()) );
        Log.v(ViewEditEvent.class.getName(),"Event name = " + e.getName());
        eventProvider.save(e);
    }

    public void cancel() {
        this.finish();
    }

    protected void initialiseButtons()
    {
        Button save = findViewById(R.id.editEventSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }


}
