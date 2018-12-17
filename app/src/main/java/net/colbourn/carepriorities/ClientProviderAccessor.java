package net.colbourn.carepriorities;

import net.colbourn.carepriorities.api.ClientProvider;
import net.colbourn.carepriorities.plugins.LocalDatabaseClientProvider.ClientProviderComponent;
import net.colbourn.carepriorities.plugins.LocalDatabaseClientProvider.DaggerClientProviderComponent;
import javax.inject.Inject;

public class ClientProviderAccessor {

    @Inject
    ClientProvider clientProvider;

    ClientProviderAccessor()
    {
        ClientProviderComponent component = DaggerClientProviderComponent.builder()
                .build();
        component.inject(this);
    }

    public ClientProvider getClientProvider() {
        return clientProvider;
    }
}
