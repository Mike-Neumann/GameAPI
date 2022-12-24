package me.xra1ny.gameapi;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.application.Window;
import me.xra1ny.gameapi.engines.LogicEngine;
import me.xra1ny.gameapi.engines.RenderingEngine;
import me.xra1ny.gameapi.engines.SoundEngine;
import me.xra1ny.gameapi.screens.GameScreen;
import me.xra1ny.gameapi.utils.FileUtils;
import me.xra1ny.gameapi.utils.PropertyUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Properties;


@Slf4j
public abstract class Game extends Window {
    @Getter(onMethod = @__(@NotNull))
    private final RenderingEngine renderEngine;
    @Getter(onMethod = @__(@NotNull))
    private final LogicEngine logicEngine;
    @Getter(onMethod = @__(@NotNull))
    private final SoundEngine soundEngine;
    @Getter
    private int targetFps = 60;
    @Getter
    @Setter
    private int currentFps = 0;
    @Getter
    private int targetTps = 100;
    @Getter
    @Setter
    private int currentTps = 0;

    @Getter(onMethod = @__(@NotNull))
    private Properties gameProperties;

    public Game() {
        final File propertyFile = new File("game.properties");
        final Properties properties = new Properties();

        if(!propertyFile.exists()) {
            onPropertiesCreation();
            FileUtils.create(propertyFile);

            properties.setProperty("fps", "60");
            properties.setProperty("tps", "100");
            properties.setProperty("sound-directory", "sounds");

            PropertyUtils.save(properties, FileUtils.getOutputStream(propertyFile));
        }

        PropertyUtils.load(properties, FileUtils.getInputStream(propertyFile));

        gameProperties = properties;

        final int fps = PropertyUtils.getInt(gameProperties, "fps");
        if(fps > 0) {
            this.targetFps = fps;
        }

        final int tps = PropertyUtils.getInt(gameProperties, "tps");
        if(tps > 0) {
            this.targetTps = tps;
        }

        String soundDirectory = gameProperties.getProperty("sound-directory");
        if(soundDirectory == null) {
            soundDirectory = "sounds";
        }

        soundEngine = new SoundEngine(this, soundDirectory);

        onEnable();

        renderEngine = new RenderingEngine(this);
        logicEngine = new LogicEngine(this);
    }

    /** Called when a new Properties gets created in Result of a non-existent one */
    public abstract void onPropertiesCreation();
    public abstract void onEnable();

    @NotNull
    @Override
    public GameScreen getCurrentScreen() {
        return (GameScreen) super.getCurrentScreen();
    }
}
