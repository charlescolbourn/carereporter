package net.colbourn.carepriorities;

import net.colbourn.carepriorities.api.ClientProvider;
import net.colbourn.carepriorities.api.ClientProviderFactory;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;


public class ClientProviderAccessor implements ClientProviderFactory {

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
