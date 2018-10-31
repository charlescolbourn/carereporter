package net.colbourn.carepriorities;

import net.colbourn.carepriorities.api.Client;
import net.colbourn.carepriorities.api.ClientProvider;

import java.util.List;

public class LocalDatabaseClientProvider implements ClientProvider {

    public Client getClient(String id){
        return null;
    }


    public List<Client> getAllClients(){
        return null;
    }
}
