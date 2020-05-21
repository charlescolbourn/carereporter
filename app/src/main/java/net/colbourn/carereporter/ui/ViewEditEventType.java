package net.colbourn.carereporter.ui;

import android.app.Activity;
import android.os.Bundle;


import net.colbourn.carereporter.R;

// TODO   https://stackoverflow.com/questions/50676100/program-type-already-present-android-support-v4-os-resultreceivermyresultrecei

public class ViewEditEventType extends Activity {

    private static final String ICON_DIALOG_TAG = "icon-dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_type_edit);

    }




}
