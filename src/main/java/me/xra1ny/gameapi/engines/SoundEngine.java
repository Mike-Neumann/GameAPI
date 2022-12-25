package me.xra1ny.gameapi.engines;

import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.Game;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

@Slf4j
public class SoundEngine extends Engine {
    private final File audioDirectory;

    public SoundEngine(@NotNull Game game, @NotNull String audioDirectory) {
        super(game);

        this.audioDirectory = new File(audioDirectory);

        if(!this.audioDirectory.exists()) {
            final boolean success = this.audioDirectory.mkdirs();

            if(!success) {
                log.error("Cannot initialise SoundEngine!");
                log.error("Audio File Directory cannot be created!");
                System.exit(1);
            }
        }
    }

    public void playSound(@NotNull String soundFile) {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioDirectory, soundFile).getAbsoluteFile());
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            log.error("Cannot Play Sound of File " + soundFile, e);
        }
    }

    @Override
    public void onEnable() {

    }
}
