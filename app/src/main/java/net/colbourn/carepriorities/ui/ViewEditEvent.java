package net.colbourn.carepriorities.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.EventProvider;
import net.colbourn.carepriorities.api.EventType;
import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseEventProvider;
import net.colbourn.carepriorities.utils.JSONUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewEditEvent extends AppCompatActivity {


    private EventProvider eventProvider;
    private static String EventTypeFilename = "EventTypes.json";
    private List<EventType> eventTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_entry_edit);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.diaryentry_toolbar);
//        setSupportActionBar(myToolbar);

        eventProvider = new LocalDatabaseEventProvider();

        populateReoccurenceSelector();
        initialiseEventTypeSelector();



//        showDiary(ViewDiary.ViewType.DAILY);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    private void populateReoccurenceSelector() { //TODO fetch from DB
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Daily");
        spinnerArray.add("Weekly");
        spinnerArray.add("Monthly");
        spinnerArray.add("Yearly");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.selectorReoccurrence);
        sItems.setAdapter(adapter);
    }

    private void initialiseEventTypeSelector() {
        //TODO fetch from DB
        String addNewTypeString = "Add new type";
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.addAll(getEventTypeNames());


        //TODO get the maltaisn icon picker working
//        spinnerArray.add(addNewTypeString);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.selectorEventType);
        sItems.setAdapter(adapter);

        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> av, View arg1, int i, long arg3) {
                Log.v(ViewEditEvent.class.getName(), "item at pos " + i);

                if (sItems.getItemAtPosition(i).equals(addNewTypeString)) {
                    createNewEventType();
                }
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
                eventTypeNameList.add(e.getName());
            }
        }
        return eventTypeNameList;
    }

}
