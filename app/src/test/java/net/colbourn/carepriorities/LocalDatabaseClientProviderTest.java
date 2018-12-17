package net.colbourn.carepriorities;

import net.colbourn.carepriorities.api.ClientProvider;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class LocalDatabaseClientProviderTest {


    ClientProvider clientProvider;

    @Before
    public void setup()
    {
        clientProvider = new ClientProviderAccessor().getClientProvider();
    }

    @Test
    public void test_provider_is_injected(){
        assertNotNull(clientProvider);
    }

    @Test
    public void test_provider_returns_all_clients(){

        assertEquals(clientProvider.getAllClients().size(), 10);


    }


}
