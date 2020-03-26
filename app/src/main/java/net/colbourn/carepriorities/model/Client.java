/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.Person;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;



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