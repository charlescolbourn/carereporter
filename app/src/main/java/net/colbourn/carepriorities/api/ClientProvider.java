package net.colbourn.carepriorities.api;

import java.util.List;

public interface ClientProvider {

    List<Client> getAllClients();
    Client getClient(String id);
}
