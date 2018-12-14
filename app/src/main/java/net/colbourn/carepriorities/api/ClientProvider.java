package net.colbourn.carepriorities.api;

import java.util.List;

public interface ClientProvider {

    List<Person> getAllClients();
    Person getClient(String id);
}
