package net.colbourn.carereporter.ui;

import android.graphics.Bitmap;

import java.util.List;

public class ViewElementDiaryHour {
    private String time;
    private List<Bitmap> icons;

    public ViewElementDiaryHour(String time, List<Bitmap> icons) {
        this.time = time;
        this.icons = icons;
    }

    public String getTime() { return time; }
    public List<Bitmap> getIcons() { return icons; }

}
