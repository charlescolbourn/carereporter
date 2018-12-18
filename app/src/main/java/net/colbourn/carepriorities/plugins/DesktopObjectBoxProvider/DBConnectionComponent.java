package net.colbourn.carepriorities.plugins.DesktopObjectBoxProvider;

import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { DBConnectionProvider.class })
public interface DBConnectionComponent {
    void inject(LocalDatabaseProvider accessor);
}
