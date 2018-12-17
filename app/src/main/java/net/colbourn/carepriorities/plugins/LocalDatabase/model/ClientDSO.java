package net.colbourn.carepriorities.plugins.LocalDatabase.model;

//import net.colbourn.carepriorities.model.Photo;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;

@Entity
public class ClientDSO {

    @Id
    public long id;

    private String preferredName;

    private String photo;

//    @Transient
//    private Photo photo;

    public String getName()
    {
        return getPreferredName();
    }

    public String getPreferredName()
    {
        return preferredName;
    }

    public String getPhoto()
    {
        return photo;
    }


}
