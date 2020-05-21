package net.colbourn.carereporter;


import net.colbourn.carereporter.api.ClientProvider;
import net.colbourn.carereporter.model.Client;
import net.colbourn.carereporter.plugins.LocalDatabase.LocalDatabaseClientProvider;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class LocalDatabaseTest {


    ClientProvider clientProvider;

    @Before
    public void setup()
    {
        clientProvider = new ClientProviderAccessor().getClientProvider();
    }

    @Test
    public void test_provider_is_injected()
    {
        assertNotNull(clientProvider);
    }

    @Test
    public void test_provider_returns_all_clients()
    {
        assertEquals(clientProvider.getAllClients().size(), 10);
    }

    @Ignore
    @Test
    public void test_client_dso() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Client client = new Client("test");
        Method dsoConvertMethod = LocalDatabaseClientProvider.class.getMethod("convertPersonToDSO");
        dsoConvertMethod.invoke(client);
    }

}
