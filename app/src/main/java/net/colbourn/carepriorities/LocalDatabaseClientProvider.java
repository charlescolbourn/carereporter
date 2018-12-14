package net.colbourn.carepriorities;

import net.colbourn.carepriorities.api.Client;
import net.colbourn.carepriorities.api.ClientProvider;
import net.colbourn.carepriorities.api.Person;

import java.util.ArrayList;
import java.util.List;

import dagger.Component;

@Component
public class LocalDatabaseClientProvider implements ClientProvider {

    public Client getClient(String id){
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
}
