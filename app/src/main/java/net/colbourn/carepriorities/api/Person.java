package net.colbourn.carepriorities.api;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public interface Person extends Serializable {

    long getId();

    String getName();

    String getPhoto();

    void setPhoto(String photo);
}
