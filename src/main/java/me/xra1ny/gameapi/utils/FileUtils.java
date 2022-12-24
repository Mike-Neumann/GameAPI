package me.xra1ny.gameapi.utils;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

@Slf4j
public class FileUtils {
    /**
     * Attempts to create the File specified
     *
     * @return True if the File was created successfully, false otherwise
     */
    public static boolean create(@NotNull File file) {
        try {
            final File parentFile = file.getParentFile();
            if(parentFile != null) {
                parentFile.mkdirs();
            }
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Retrieves the InputStream of the File specified
     *
     * @return The InputStream on Success, null otherwise
     */
    @Nullable
    public static InputStream getInputStream(@NotNull File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * Retrieves the OutputStream of the File specified
     *
     * @return The OutputStream on Success, null otherwise
     */
    @Nullable
    public static OutputStream getOutputStream(@NotNull File file) {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
