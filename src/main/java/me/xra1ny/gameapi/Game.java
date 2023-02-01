package me.xra1ny.gameapi;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.application.Window;
import me.xra1ny.gameapi.engines.LogicEngine;
import me.xra1ny.gameapi.engines.RenderingEngine;
import me.xra1ny.gameapi.engines.SoundEngine;
import me.xra1ny.gameapi.screens.DefaultGameScreen;
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
    @Getter(onMethod = @__(@NotNull))
    private final DefaultGameScreen defaultGameScreen = new DefaultGameScreen(this);
    @Getter
    private int targetFps = 144;
    @Getter
    @Setter
    private int currentFps = 0;
    @Getter
    private int targetTps = 100;
    @Getter
    @Setter
    private int currentTps = 0;
    @Getter
    @Setter
    private double collisionTolerance;
    @Getter
    @Setter
    private float soundVolume;

    @Getter(onMethod = @__(@NotNull))
    private final Properties gameProperties;
    @Getter(onMethod = @__(@NotNull))
    private final File gamePropertiesFile;

    public Game() {
        gamePropertiesFile = new File("game.properties");
        final Properties properties = new Properties();

        gameProperties = properties;

        if(!gamePropertiesFile.exists()) {
            FileUtils.create(gamePropertiesFile);

            properties.setProperty("fps", "144");
            properties.setProperty("tps", "100");
            properties.setProperty("collision-tolerance", "2.5");
            properties.setProperty("sound-directory", "sounds");
            properties.setProperty("volume-boost", "0.0");

            PropertyUtils.save(properties, FileUtils.getOutputStream(gamePropertiesFile));

            onPropertiesCreation();
        }

        PropertyUtils.load(properties, FileUtils.getInputStream(gamePropertiesFile));


        final int fps = PropertyUtils.getInt(gameProperties, "fps");
        if(fps > 0) {
            this.targetFps = fps;
        }

        final int tps = PropertyUtils.getInt(gameProperties, "tps");
        if(tps > 0) {
            this.targetTps = tps;
        }

        final double collisionTolerance = PropertyUtils.getDouble(gameProperties, "collision-tolerance");
        if(collisionTolerance > 0) {
            this.collisionTolerance = collisionTolerance;
        }

        String soundDirectory = gameProperties.getProperty("sound-directory");
        if(soundDirectory == null) {
            soundDirectory = "sounds";
        }

        this.soundVolume = PropertyUtils.getFloat(gameProperties, "sound-volume");

        show(defaultGameScreen);

        renderEngine = new RenderingEngine(this);
        renderEngine.enable();
        soundEngine = new SoundEngine(this, soundDirectory);
        soundEngine.enable();
        logicEngine = new LogicEngine(this);
        logicEngine.enable();


        onEnable();
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
