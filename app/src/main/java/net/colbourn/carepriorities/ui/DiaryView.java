package net.colbourn.carepriorities.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.EventProvider;
import net.colbourn.carepriorities.api.Event;
import net.colbourn.carepriorities.api.Person;
import net.colbourn.carepriorities.model.Client;
import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseEventProvider;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DiaryView extends AppCompatActivity {
    private enum ViewType { HOURLY, DAILY, WEEKLY, LIST };

    EventProvider eventProvider;
    Person client;
    Date selectedDate = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.diaryview_toolbar);
        setSupportActionBar(myToolbar);


        client = (Person) getIntent().getSerializableExtra("client");
        Log.v(DiaryView.class.getName(),"Got client with id " + client.getId());

        eventProvider = new LocalDatabaseEventProvider();

        showDiary(ViewType.DAILY);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    protected void showDiary(ViewType viewType) {
        switch (viewType) {
            case HOURLY:
                viewType_hourly();
                break;
            case DAILY:
                viewType_daily();
                break;
            case WEEKLY:
                viewType_weekly();
                break;
            case LIST:
                viewType_list();
                break;
            default:
                throw new RuntimeException("can't identify viewtype " + viewType.name());
        }

    }

    private void viewType_list() {
        List<Event> events = eventProvider.getForDateAndClient(selectedDate,client.getId());
        showListOfEvents(events);


    }


    private SimpleAdapter adaptEventToListImageTextView(List<Event> events)
    {
        List<HashMap<String,String>> pList = new ArrayList<>();
        for (Event e: events) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("title", e.getName());
            hm.put("description", "");
            hm.put("time", formatDate(e.getTime()));
            hm.put("icon", e.getIcon()); // quick demo hack
            pList.add(hm);
        }

        String[] from = {"icon", "title", "description", "time"};
        int[] to = {R.id.diary_item_list_view_image,
                    R.id.diary_item_list_view_title,
                    R.id.diary_item_list_view_description,
                    R.id.diary_item_list_view_time};

        return new SimpleAdapter(getBaseContext(), pList, R.layout.diary_item_list_view, from, to);

    }

    private String formatDate(Date time) {
        return time.toString(); //TODO

    }


    private void showListOfEvents(List<Event> events)
    {
        ListView eventListView = (ListView) findViewById(R.id.listOfEvents);
        eventListView.setAdapter(adaptEventToListImageTextView(events));
        eventListView.setClickable(true);
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View arg1, int i, long arg3) {
                Log.v(DiaryView.class.getName(), "item at pos " + i);
                HashMap<String,String> p = (HashMap<String,String>) eventListView.getItemAtPosition(i);
                Log.v(DiaryView.class.getName(), "Client  " + p.get("listview_title"));
                openEvent(events.get(i));
            }
        });
    }

    private void viewType_weekly() {
        viewType_list();
    }

    private void viewType_daily() {
        viewType_list();
    }

    private void viewType_hourly() {
        viewType_list();
    }

    private void openEvent(Event event) {
        Intent intent = new Intent(this,EventView.class);
        startActivity(intent);
    }


}
