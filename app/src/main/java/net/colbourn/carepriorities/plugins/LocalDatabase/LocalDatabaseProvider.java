package net.colbourn.carepriorities.plugins.LocalDatabase;

import net.colbourn.carepriorities.plugins.DesktopObjectBoxProvider.DBConnectionComponent;
import net.colbourn.carepriorities.plugins.DesktopObjectBoxProvider.DaggerDBConnectionComponent;

import javax.inject.Inject;

import io.objectbox.BoxStore;

public class LocalDatabaseProvider {

    @Inject
    BoxStore dbConnection;

    LocalDatabaseProvider()
    {
        DBConnectionComponent component = DaggerDBConnectionComponent.builder()
                .build();
        component.inject(this);
    }

    public BoxStore getBoxStore()
    {
        return dbConnection;
    }

}
