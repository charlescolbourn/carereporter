package net.colbourn.carepriorities.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static String getProperty(String filename, String key,Context context) throws IOException {
        Properties properties = getAllProperties(filename, context);
        return properties.getProperty(key);
    }
    
    public static Properties getAllProperties(String filename, Context context) throws IOException {
        Properties properties = new Properties();;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(filename);
        properties.load(inputStream);
        return properties;
    }

}
