/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carereporter.model;

import net.colbourn.carereporter.api.Person;


public class Client implements Person {

    public long id;
    private String preferredName;
    private String photo;

    /**
     * use for testing only
     */
    public Client(String name) {
        preferredName = name;
    }

    @Override
    public long getId() { return id; }

    @Override
    public String getName() {
        return preferredName;
    }

    @Override
    public String getPhoto() { return photo; }

    @Override
    public void setPhoto(String photo)
    {
        this.photo = photo;
    }
}