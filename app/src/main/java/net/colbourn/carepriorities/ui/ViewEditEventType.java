package net.colbourn.carepriorities.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.colbourn.carepriorities.R;

// TODO   https://stackoverflow.com/questions/50676100/program-type-already-present-android-support-v4-os-resultreceivermyresultrecei

public class ViewEditEventType extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_type_edit);
    }
}
