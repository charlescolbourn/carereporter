package net.colbourn.carereporter.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    public static String cacheIcon(Context context, String iconName) {
        if (iconName != null) {
            File cacheFile = new File(context.getCacheDir(), iconName);
            try {
                InputStream inputStream = context.getAssets().open("images/" + iconName);
                try {
                    FileOutputStream outputStream = new FileOutputStream(cacheFile);
                    try {
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buf)) > 0) {
                            outputStream.write(buf, 0, len);
                        }
                    } finally {
                        outputStream.close();
                    }
                } finally {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return cacheFile.getAbsolutePath();
        }
        return null;
    }

}

