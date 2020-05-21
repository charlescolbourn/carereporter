package net.colbourn.carereporter.plugins.DesktopObjectBoxProvider;

import net.colbourn.carereporter.plugins.LocalDatabase.LocalDatabaseProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { DBConnectionProvider.class })
public interface DBConnectionComponent {
    void inject(LocalDatabaseProvider accessor);
}
