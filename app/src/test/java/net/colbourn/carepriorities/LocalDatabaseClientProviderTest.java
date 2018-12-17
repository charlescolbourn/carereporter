package net.colbourn.carepriorities;

import net.colbourn.carepriorities.api.ClientProvider;

import org.junit.Ignore;
import org.junit.Test;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;

public class LocalDatabaseClientProviderTest {

    @Inject
    ClientProvider clientProvider;


    @Test
    @Ignore
    public void test_provider_is_injected(){
        assertNotNull(clientProvider);
    }

    @Test
    public void test_provider_returns_all_clients(){

    }


}
