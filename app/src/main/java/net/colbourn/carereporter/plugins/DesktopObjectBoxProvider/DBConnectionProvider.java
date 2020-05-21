package net.colbourn.carereporter.plugins.DesktopObjectBoxProvider;
import net.colbourn.carereporter.plugins.LocalDatabase.model.MyObjectBox;

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
        return MyObjectBox.builder().build();
    }

}
