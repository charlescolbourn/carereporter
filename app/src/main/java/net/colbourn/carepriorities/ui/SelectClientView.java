package net.colbourn.carepriorities.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.ClientProvider;
import net.colbourn.carepriorities.api.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class SelectClientView extends AppCompatActivity {

    private static final String L = SelectClientView.class.getName();

    @Inject
    private ClientProvider clientProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientselect);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.select_client_toolbar);
        setSupportActionBar(myToolbar);
        showListOfClients();
    }

    private List<Person> getAllClients() {
        return clientProvider.getAllClients();
    }

    private List<String> getClientNames(List<Person> clients)
    {
        List<String> retList = new ArrayList<>();
        for (Person p: clients)
        {
            retList.add(p.getName());
        }
        return retList;
    }

    private SimpleAdapter adaptPersonToListImageTextView(List<Person> people)
    {
        List<HashMap<String,String>> pList = new ArrayList<>();
        for (Person p: people) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", p.getName());
            hm.put("listview_description", "");
            hm.put("listview_image", Integer.toString(R.drawable.dumpling)); // quick demo hack
            pList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_description"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        return new SimpleAdapter(getBaseContext(), pList, R.layout.listview_textandphoto, from, to);

    }


    private void showListOfClients()
    {
        List<Person> clients = getAllClients();
        ListView clientListView = (ListView) findViewById(R.id.listOfClients);
        clientListView.setAdapter(adaptPersonToListImageTextView(clients));
        clientListView.setClickable(true);
        clientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View arg1, int i, long arg3) {
                Log.v(L, "client at pos " + i);
                HashMap<String,String> p = (HashMap<String,String>) clientListView.getItemAtPosition(i);
                Log.v(L, "Client  " + p.get("listview_title"));
                openDiary(clients.get(i));
            }
        });
    }

    private void openDiary(Person client)
    {
        Intent intent = new Intent(this,DiaryActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.createclient:
                intent = new Intent(this,EditClient.class);
                startActivity(intent);
                return true;
//            case R.id.help:
////                intent = new Intent(this,HelpActivity.class);
////                intent.putExtra("HELP_TITLE",R.string.help_selectclient_title);
////                intent.putExtra("HELP_TEXT",R.string.help_selectclient_text);
//                startActivity(intent);
//                return true;
//            case R.id.setupapplication:
////                intent = new Intent(this,Setup.class);
////                startActivity(intent);
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_client, menu);
        return true;
    }

}
