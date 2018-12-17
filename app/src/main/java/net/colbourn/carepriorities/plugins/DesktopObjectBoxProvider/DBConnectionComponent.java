package net.colbourn.carepriorities.plugins.DesktopObjectBoxProvider;

import net.colbourn.carepriorities.plugins.LocalDatabase.LocalDatabaseProvider;

import dagger.Component;

@Component(modules = { DBConnectionProvider.class })
public interface DBConnectionComponent {
    void inject(LocalDatabaseProvider accessor);
}
