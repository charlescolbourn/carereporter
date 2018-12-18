package net.colbourn.carepriorities.plugins.DesktopObjectBoxProvider;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.MyObjectBox;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.objectbox.BoxStore;

@Module
public class DBConnectionProvider {

    @Provides
    @Singleton
    static BoxStore provide()
    {
        return MyObjectBox.builder().name("objectbox-db").build();
    }

}
