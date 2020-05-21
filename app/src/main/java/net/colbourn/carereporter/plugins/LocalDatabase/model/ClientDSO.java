package net.colbourn.carereporter.plugins.LocalDatabase.model;

//import net.colbourn.carereporter.model.Photo;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

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

    public void setName(String name)
    {
        setPreferredName(name);
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public void setPhoto(String photo) { this.photo = photo; }
}
