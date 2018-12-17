package net.colbourn.carepriorities.plugins.LocalDatabase;

import net.colbourn.carepriorities.ClientProviderAccessor;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = { LocalDatabaseClientProviderFactory.class })
public interface ClientProviderComponent {
    void inject(ClientProviderAccessor accessor);
}
