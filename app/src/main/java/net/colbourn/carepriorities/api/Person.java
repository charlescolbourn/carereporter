package net.colbourn.carepriorities.api;

import android.graphics.drawable.Drawable;

public interface Person {

    long getId();

    String getName();

    String getPhoto();

    void setPhoto(String photo);
}
