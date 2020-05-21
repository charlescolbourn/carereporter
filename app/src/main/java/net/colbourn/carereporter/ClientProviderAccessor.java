//package net.colbourn.carereporter;
//
//import net.colbourn.carereporter.api.ClientProvider;
//import net.colbourn.carereporter.plugins.LocalDatabase.ClientProviderComponent;
////import net.colbourn.carereporter.plugins.LocalDatabase.DaggerClientProviderComponent;
//import javax.inject.Inject;
//
//public class ClientProviderAccessor {
//
//    @Inject
//    ClientProvider clientProvider;
//
//    ClientProviderAccessor()
//    {
//        ClientProviderComponent component = DaggerClientProviderComponent.builder()
//                .build();
//        component.inject(this);
//    }
//
//    public ClientProvider getClientProvider() {
//        return clientProvider;
//    }
//}
