package net.colbourn.carepriorities.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import net.colbourn.carepriorities.plugins.LocalDatabase.JSONEventTypeProvider;
import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseEventProvider;
import net.colbourn.carepriorities.utils.ImageUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        JSONEventTypeProvider.getEventTypes(this); // initialise the eventtypes cache
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
            Log.v(ViewDiary.class.getName(),"Adding event to hash at " + hour);
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
        // todo make this post hoc and asynchronous
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
            hm.put("time", formatDateTime(e.getTime()));
            hm.put("description", e.getDescription());
            Log.v(ViewDiary.class.getName(),"Displaying icon " + e.getIcon());
            hm.put("icon", ImageUtils.cacheIcon(this.getApplicationContext(),e.getIcon()));
            pList.add(hm);
        }

        String[] from = {"icon", "title", "description", "time"};
        int[] to = {R.id.diary_item_list_view_icon,
                    R.id.diary_item_list_view_title,
                    R.id.diary_item_list_view_time,
                    R.id.diary_item_list_view_description,
                    };

        return new SimpleAdapter(getBaseContext(), pList, R.layout.diary_item_list_view, from, to);
    }

    private String formatDateTime(Date time) {
        if (time==null) { return ""; } // this shouldn't happen once bugs are fixed TODO
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(time);
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
            ((View)lastParent).findViewById(R.id.diary_view_hour_icon_space).setVisibility(View.VISIBLE);
            ((ViewManager) currentHourView.getParent()).removeView(currentHourView);

        }
        if (currentHourView==null || lastParent != hourView ) {
            currentHourView = View.inflate(this, R.layout.diary_view_expanded_hour, null);
            ((LinearLayout) hourView).addView(currentHourView);
            hourView.findViewById(R.id.diary_view_hour_icon_space).setVisibility(View.GONE);
            populateHourView(hourView);
        }

    }

    private void populateHourView(View hourView) {

        String hourString = ((TextView)hourView.findViewById(R.id.diary_view_hour_time)).getText().toString();
        int hour = Integer.parseInt(hourString.substring(0,2));
        List<Event> eventList = eventListByTime.get(hour);


        if (eventList != null) {
            populateEventListExpanded(hourView, eventList);
        }
    }

    private void populateEventListExpanded(View hourView, List<Event> eventList) {
        LinearLayout listLayout = hourView.findViewById(R.id.diary_view_hour_expanded_eventlist);
        for (Event event : eventList) {
            populateExpandedRow(listLayout,event);
        }
    }

    private void populateExpandedRow(LinearLayout listLayout, Event event) {
        View eventView = View.inflate(this, R.layout.diary_item_list_view, null);
        //icon time title description
        String path = ImageUtils.cacheIcon(this.getApplicationContext(),event.getIcon()); //TODO this shouldn't cache the icon again
        Bitmap iconBitmap = BitmapFactory.decodeFile(path);
        ((ImageView) eventView.findViewById(R.id.diary_item_list_view_icon)).setImageBitmap(iconBitmap);
        ((TextView) eventView.findViewById(R.id.diary_item_list_view_title)).setText(event.getName());
        ((TextView) eventView.findViewById(R.id.diary_item_list_view_description)).setText(event.getDescription());
        ((TextView) eventView.findViewById(R.id.diary_item_list_view_time)).setText(formatDateTime(event.getTime()));
        listLayout.addView(eventView);
        eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(ViewDiary.class.getName(),"Event is " + event.getName());
                openEvent(event);
            }
        });
    }



    @Override
    public void onRestart() {
        super.onRestart();
    }
}
