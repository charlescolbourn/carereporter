package net.colbourn.carepriorities.plugins.DesktopObjectBoxProvider;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.MyObjectBox;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBConnectionProvider {

    @Provides
    @Singleton
    static DBConnectionProvider provide()
    {
        return MyObjectBox.builder().name("objectbox-db").build();
    }

}
