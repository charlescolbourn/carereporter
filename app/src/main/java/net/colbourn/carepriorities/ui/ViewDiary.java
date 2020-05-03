package net.colbourn.carepriorities.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.EventProvider;
import net.colbourn.carepriorities.api.Event;
import net.colbourn.carepriorities.api.Person;
import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseEventProvider;
import net.colbourn.carepriorities.utils.ImageUtils;

import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ViewDiary extends Activity {

    private View currentHourView;

    private enum ViewType { HOURLY, DAILY, WEEKLY, LIST };

    EventProvider eventProvider;
    Person client;
    Date selectedDate = new Date();
    Map<Integer,List<Event>> eventListByTime = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_view);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.diaryview_toolbar);
        setActionBar(myToolbar);


        client = (Person) getIntent().getSerializableExtra("client");
        Log.v(ViewDiary.class.getName(),"Got client with id " + client.getId());

        eventProvider = new LocalDatabaseEventProvider();
        getEventsForDate();

        showDiary(ViewType.DAILY);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEvent();
            }
        });
    }

    private void getEventsForDate() {
        List<Event> events = eventProvider.getForDateAndClient(selectedDate,client.getId());
        for (Event event : events) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(event.getTime());
            int hour = cal.get(Calendar.HOUR);
            if (eventListByTime.get(hour) == null) {
                eventListByTime.put(hour, new ArrayList<>());
            }
            eventListByTime.get(hour).add(event);
        }
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
//        List<Event> events = eventProvider.getForDateAndClient(selectedDate,client.getId());
//        Log.v(ViewDiary.class.getName(),"Showing " + events.size() + " events");
//        showListOfEvents(events);


    }

    private void viewType_day() {
        ArrayAdapter<ViewElementDiaryHour> adapter = new ViewElementDiaryHourAdapter(this.getApplicationContext(), getHourItems());
        ListView eventListView = findViewById(R.id.list_of_events);
        eventListView.setAdapter(adapter);
        eventListView.setClickable(true);
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v(ViewDiary.class.getName(),"position = " + position + " and id = " + id );
                selectHour(view);
            }
        });

//        eventListView.setSelection(); //TODO show hour position of now,but don't SELECT it

    }

    private List<ViewElementDiaryHour> getHourItems() {
        List<String> hours = createListOfHours();
        List<ViewElementDiaryHour> items = new ArrayList<>();
        for (String hour : hours) {
            items.add(new ViewElementDiaryHour(hour, getIconsForHour(Integer.parseInt(hour.substring(0,2)))));
        }
        return items;
    }

    private List<Bitmap> getIconsForHour(int hour) {

        List<Bitmap> icons = new ArrayList<>();
        Log.v(ViewDiary.class.getName(),"Checking for events in hour " + hour);
        List<Event> events = eventListByTime.get(hour) != null ? eventListByTime.get(hour) : new ArrayList<>();
        for (Event event : events) {
            Calendar cal = Calendar.getInstance();
//            if (event.getTime()==null) {
//                continue;
//            }
            cal.setTime(event.getTime());
            if (cal.get(Calendar.HOUR)==hour) {
                Log.v(ViewDiary.class.getName(),"Found event " + event.getName() + " at time " + hour);
                String path = ImageUtils.cacheIcon(this.getApplicationContext(),event.getIcon());
                Bitmap iconBitmap = BitmapFactory.decodeFile(path);
                icons.add(iconBitmap);
            }
        }
        Log.v(ViewDiary.class.getName(),"Returning " + icons.size() + " icons for hour " + hour);
        return icons;
    }





    private List<String> createListOfHours() {
        List<String> hours = new ArrayList<>();
        for (int i=0; i<24; i++) {
            hours.add(String.format("%02d:00",i));
        }
        return hours;
    }


    private SimpleAdapter adaptEventToListImageTextView(List<Event> events)
    {
        List<HashMap<String,String>> pList = new ArrayList<>();
        for (Event e: events) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("title", e.getName());
            hm.put("description", "");
            Log.v(ViewDiary.class.getName(),"Displaying icon " + e.getIcon());
            hm.put("icon", ImageUtils.cacheIcon(this.getApplicationContext(),e.getIcon()));
            pList.add(hm);
        }

        String[] from = {"icon", "title", "description", "time"};
        int[] to = {R.id.diary_item_list_view_image,
                    R.id.diary_item_list_view_title,
                    R.id.diary_item_list_view_description,
                    };

        return new SimpleAdapter(getBaseContext(), pList, R.layout.diary_item_list_view, from, to);
    }

    private String formatDate(Date time) {
        return time != null ? time.toString() : ""; //TOdo
    }


//    private void showListOfEvents(List<Event> events)
//    {
//        ListView eventListView = findViewById(R.id.list_of_events);
//        eventListView.setAdapter(adaptEventToListImageTextView(events));
//        eventListView.setClickable(true);
//        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> av, View arg1, int i, long arg3) {
//                Log.v(ViewDiary.class.getName(), "item at pos " + i);
//                HashMap<String,String> p = (HashMap<String,String>) eventListView.getItemAtPosition(i);
//                Log.v(ViewDiary.class.getName(), "Client  " + p.get("listview_title"));
//                openEvent(events.get(i));
//            }
//        });
//    }

    private void viewType_weekly() {
        viewType_list();
    }

    private void viewType_daily() {
        viewType_day();
    }

    private void viewType_hourly() {
        viewType_list();
    }

    private void createEvent() {
        Intent intent = new Intent(this,ViewEditEvent.class);
        startActivity(intent);
    }

    private void openEvent(Event event) {
        Intent intent = new Intent(this,ViewEditEvent.class);
        intent.putExtra("event", event);
        startActivity(intent);
    }
    private String getIconPathForIcon(String iconName) {
        return "file:///android_assets/images/"+iconName;
    }

    private void selectHour(View hourView){
        Log.v(ViewDiary.class.getName(),"Viewing hour " + hourView.toString());
        ViewParent lastParent = null;
        if (currentHourView != null && currentHourView.getParent() != null) {
            lastParent = currentHourView.getParent();
            ((ViewManager) currentHourView.getParent()).removeView(currentHourView);
        }
        if (currentHourView==null || lastParent != hourView ) {
            currentHourView = View.inflate(this, R.layout.diary_view_expanded_hour, null);
            ((LinearLayout) hourView).addView(currentHourView);
            populateHourView(hourView);
        }

    }

    private void populateHourView(View hourView) {

        String hourString = ((TextView)hourView.findViewById(R.id.diary_view_hour_time)).getText().toString();
        int hour = Integer.parseInt(hourString.substring(0,2));
        List<Event> eventList = eventListByTime.get(hour);
        ((TextView)currentHourView.findViewById(R.id.diary_view_expanded_hour_time)).setText(hourString);

        if (eventList != null) {
            ListView eventListView = hourView.findViewById(R.id.diary_view_hour_expanded_eventlist);
            eventListView.setAdapter(adaptEventToListImageTextView(eventList));
            eventListView.setClickable(true);
            eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> av, View arg1, int i, long arg3) {
                    Log.v(ViewDiary.class.getName(), "item at pos " + i);
                    HashMap<String, String> p = (HashMap<String, String>) eventListView.getItemAtPosition(i);
                    Log.v(ViewDiary.class.getName(), "Client  " + p.get("listview_title"));
                    openEvent(eventList.get(i));
                }
            });
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }
}
