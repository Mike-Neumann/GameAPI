package me.xra1ny.gameapi.utils;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

@Slf4j
public class PropertyUtils {
    /**
     * Loads the Contents of the specified InputStream to the specified Properties
     */
    public static void load(@NotNull Properties properties, @NotNull InputStream inputStream) {
        try {
            properties.load(inputStream);
        } catch (IOException ignored) {}
    }

    /**
     * Saves the specified Properties to the specified OutputStream
     */
    public static void save(@NotNull Properties properties, @NotNull OutputStream outputStream) {
        try {
            properties.store(outputStream, null);
        } catch (IOException ignored) {}
    }

    /**
     * @return The Boolean of the specified Key
     */
    public static boolean getBoolean(@NotNull Properties properties, @NotNull String key) {
        return Boolean.valueOf(getObject(properties, key).toString());
    }

    /**
     * @return The Integer of the specified Key
     */
    public static int getInt(@NotNull Properties properties, @NotNull String key) {
        return Integer.parseInt(getObject(properties, key).toString());
    }

    /**
     * @return The Object of the specified Key
     */
    public static Object getObject(@NotNull Properties properties, @NotNull String key) {
        return properties.getProperty(key);
    }
}
