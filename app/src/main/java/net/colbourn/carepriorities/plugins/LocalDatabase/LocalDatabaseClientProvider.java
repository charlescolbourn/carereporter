package net.colbourn.carepriorities.plugins.LocalDatabase;


import net.colbourn.carepriorities.model.Client;
import net.colbourn.carepriorities.api.ClientProvider;
import net.colbourn.carepriorities.api.Person;
import net.colbourn.carepriorities.plugins.LocalDatabase.model.ClientDSO;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;


public class LocalDatabaseClientProvider implements ClientProvider
{

    LocalDatabaseClientProvider() //TODO IoC this from the test
    {


        BoxStore boxStore = new LocalDatabaseProvider().getBoxStore();


        Box<ClientDSO> box = boxStore.boxFor(ClientDSO.class);
    }




    public Person getClient(String id){
        return null;
    }

    public List<Person> getAllClients(){
        List<Person> retlist = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            retlist.add(new net.colbourn.carepriorities.model.Client("dumpling the cat " + i));
        }
        return retlist;
    }

    private Person convertDSOToPerson(ClientDSO clientDSO)
    {
        Client client = new Client(clientDSO.getName());
        client.setPhoto(client.getPhoto());
        return client;

    }




}
