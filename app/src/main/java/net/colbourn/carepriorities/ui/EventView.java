package net.colbourn.carepriorities.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.EventProvider;
import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseEventProvider;

public class EventView extends AppCompatActivity {


    private EventProvider eventProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_entry_edit);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.diaryentry_toolbar);
//        setSupportActionBar(myToolbar);

        eventProvider = new LocalDatabaseEventProvider();

//        showDiary(DiaryView.ViewType.DAILY);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}
