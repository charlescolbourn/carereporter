package net.colbourn.carepriorities.plugins.LocalDatabaseClientProvider;

import net.colbourn.carepriorities.api.ClientProvider;
import net.colbourn.carepriorities.plugins.LocalDatabaseClientProvider.LocalDatabaseClientProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalDatabaseClientProviderFactory {

    @Provides
    @Singleton
    static ClientProvider provide()
    {
        return new LocalDatabaseClientProvider();
    }
}
