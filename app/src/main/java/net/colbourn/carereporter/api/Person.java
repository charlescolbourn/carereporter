package net.colbourn.carereporter.api;

import java.io.Serializable;

public interface Person extends Serializable {

    long getId();

    String getName();

    String getPhoto();

    void setPhoto(String photo);
}
