package net.colbourn.carereporter.plugins.LocalDatabase;

import android.content.Context;

import net.colbourn.carereporter.plugins.LocalDatabase.model.MyObjectBox;

import io.objectbox.BoxStore;

public class LocalDatabaseProvider {

    //@Inject
    static BoxStore dbConnection;


    public static void init(Context context) {
        dbConnection = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
    }

    public BoxStore getBoxStore()
    {
        return dbConnection;
    }

}
