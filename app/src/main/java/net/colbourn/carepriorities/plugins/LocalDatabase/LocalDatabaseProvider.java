package net.colbourn.carepriorities.plugins.LocalDatabase;

import android.content.Context;

import net.colbourn.carepriorities.plugins.DesktopObjectBoxProvider.DBConnectionComponent;
import net.colbourn.carepriorities.plugins.DesktopObjectBoxProvider.DaggerDBConnectionComponent;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.MyObjectBox;

import javax.inject.Inject;

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
