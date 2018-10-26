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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.model.api.Person;
import net.colbourn.carepriorities.model.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectClient extends AppCompatActivity {

    private static final String L = SelectClient.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        showListOfClients();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private List<Person> getAllClients() {
        List<Person> retlist = new ArrayList<>();
        for (int i = 0; i < 20; i++)
        {
            retlist.add(new Client("dumpling the cat " + i));
        }
        ///return new ArrayList<Person>(Arrays.asList(new Client("dumpling the cat")));
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

    //TODO - lifted this method from some old code. Clean up
    private void showListOfClients(){
        final ListView view = (ListView) findViewById(R.id.listOfClients);
        final List<Person> clients = getAllClients();

        List<String> clientnames = getClientNames(clients);


        ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientnames);
        view.setAdapter(myarrayAdapter);
        view.setTextFilterEnabled(true);

        view.setClickable(true);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View arg1, int i, long arg3) {
                Log.v(L, "client at pos " + i);
                String p = (String) view.getItemAtPosition(i);
                Log.v(L, "Client  " + p);
                this.openDiary(clients.get(i));
                //finish();
            }

            private void openDiary(Person person) {
//                Intent intent = new Intent(SelectClient.this,ClientDiary.class);
//                Log.v(SelectClient.class.toString(),"Selecting user " + person.displayname);
//                Log.v(SelectClient.class.toString(),"uid " + ( person.id != null ? "present" + person.id.toString() : "absent"));
//                intent.putExtra("CLIENT_ID", person.id);
//                startActivity(intent);
            }
        });

    }
}
