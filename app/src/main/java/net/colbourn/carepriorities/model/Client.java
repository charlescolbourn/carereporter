/***************************************************************************************************
 * Copyright C W Colbourn 2018
 *
 * Licence: GPL
 *
 **************************************************************************************************/
package net.colbourn.carepriorities.model;

import java.io.Serializable;

import net.colbourn.carepriorities.R;
import net.colbourn.carepriorities.api.Person;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;


//@Entity
public class Client implements Person, Serializable {
    //    @Id
    public long id;

    private String preferredName;

    //    @Transient
    private Photo photo;

    /**
     * use for testing only
     */
    public Client(String name) {
        preferredName = name;
    }

    @Override
    public String getName() {
        return preferredName;
    }

    @Override
    public String getPhoto() {
        return Integer.toString(R.drawable.dumpling);
    }

}