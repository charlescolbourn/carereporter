package net.colbourn.carepriorities.api;

import net.colbourn.carepriorities.ClientProviderFactory;
import net.colbourn.carepriorities.LocalDatabaseClientProvider;

import java.util.List;

import dagger.Component;

public interface ClientProvider {

    List<Person> getAllClients();

    Person getClient(String id);
}
