package net.colbourn.carepriorities.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.model.api.Person;
import net.colbourn.carepriorities.model.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SelectClient extends AppCompatActivity {

    private static final String L = SelectClient.class.getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientselect);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.select_client_toolbar);
        setSupportActionBar(myToolbar);
        showListOfClients();
    }

    private List<Person> getAllClients() {
        List<Person> retlist = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            retlist.add(new Client("dumpling the cat " + i));
        }
        return retlist;
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



    private void showListOfClients()
    {
        final List<Person> clients = getAllClients();
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (Person p: clients) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", p.getName());
            hm.put("listview_description", "");
            hm.put("listview_image", Integer.toString(R.drawable.dumpling)); // quick demo hack
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_description"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_textandphoto, from, to);
        ListView clientListView = (ListView) findViewById(R.id.listOfClients);
        clientListView.setAdapter(simpleAdapter);
        clientListView.setClickable(true);
        clientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View arg1, int i, long arg3) {
                Log.v(L, "client at pos " + i);
                HashMap<String,String> p = (HashMap<String,String>) clientListView.getItemAtPosition(i);
                Log.v(L, "Client  " + p.get("listview_title"));
//                openDiary(clients.get(i));
                //finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.createclient:
//                intent = new Intent(this,EditClient.class);
//                startActivity(intent);
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
