package net.colbourn.carepriorities;


import net.colbourn.carepriorities.api.ClientProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ClientProviderFactory.class })
public interface ClientProviderComponent {
    void inject(ClientProviderAccessor accessor);
}
