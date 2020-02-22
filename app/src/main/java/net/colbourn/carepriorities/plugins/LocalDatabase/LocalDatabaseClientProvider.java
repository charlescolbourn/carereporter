package net.colbourn.carepriorities.plugins.LocalDatabase;


import android.content.Context;

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
    Box<ClientDSO> box;


    public LocalDatabaseClientProvider()
    {
        BoxStore boxStore = new LocalDatabaseProvider().getBoxStore();
        box = boxStore.boxFor(ClientDSO.class);
    }

    public boolean writeClient(Person client)
    {
        ClientDSO dso = convertPersonToDSO(client);
        long ret =  box.put(dso);
        return ret == 1 ? true : false;
    }

    public Person getClient(String id){
        ClientDSO client = box.get(Long.getLong(id));
        return convertDSOToPerson(client);
    }

    public List<Person> getAllClients()
    {
//        List<Person> retlist = new ArrayList<>();
//        for (int i = 0; i < 10; i++)
//        {
//            retlist.add(new net.colbourn.carepriorities.model.Client("dumpling the cat " + i));
//        }
//        return retlist;
        return convertDSOsToPersons(box.getAll());
    }

    private List<Person> convertDSOsToPersons(List<ClientDSO> personDSOs) {
        List<Person> people = new ArrayList<>();
        for (ClientDSO dso : personDSOs)
        {
            people.add(convertDSOToPerson(dso));
        }
        return people;
    }

    private Person convertDSOToPerson(ClientDSO clientDSO)
    {
        Client client = new Client(clientDSO.getName());
        client.setPhoto(client.getPhoto());
        return client;
    }
    private ClientDSO convertPersonToDSO(Person client)
    {
        ClientDSO dso = new ClientDSO();
        dso.setName(client.getName());
        dso.setPhoto(client.getPhoto());
        return dso;
    }



}
