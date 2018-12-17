package net.colbourn.carepriorities;

import net.colbourn.carepriorities.api.ClientProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ClientProviderFactory {

    @Provides
    @Singleton
    static ClientProvider provide()
    {
        return new LocalDatabaseClientProvider();
    }
}
