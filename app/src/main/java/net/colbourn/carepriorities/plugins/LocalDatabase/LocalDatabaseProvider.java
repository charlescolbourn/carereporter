package net.colbourn.carepriorities.plugins.LocalDatabase;

import javax.inject.Inject;

import io.objectbox.BoxStore;

public class LocalDatabaseProvider {

    @Inject
    BoxStore dbConnection;

    public BoxStore getBoxStore()
    {
        return dbConnection;
    }

}
