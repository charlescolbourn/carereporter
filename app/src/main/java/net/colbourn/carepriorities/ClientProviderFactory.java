package net.colbourn.carepriorities;

import net.colbourn.carepriorities.api.ClientProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class ClientProviderFactory implements ClientProviderFactoryApi {

    @Provides
    ClientProvider getClientProvider() {
        return new LocalDatabaseClientProvider();
    }
}
