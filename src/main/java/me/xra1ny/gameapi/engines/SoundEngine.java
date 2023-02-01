package me.xra1ny.gameapi.engines;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.Game;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SoundEngine extends Engine {
    @Getter(onMethod = @__(@NotNull))
    private final File audioDirectory;
    @Getter(onMethod = @__(@NotNull))
    private final List<File> soundFiles = new ArrayList<>();

    public SoundEngine(@NotNull Game game, @NotNull String audioDirectory) {
        super(game);

        this.audioDirectory = new File(audioDirectory);

        if (!this.audioDirectory.exists()) {
            final boolean success = this.audioDirectory.mkdirs();

            if (!success) {
                throw new RuntimeException("Audio File Directory could not be created!");
            }
        }
    }

    private void loadSounds() {
        System.out.println("Loading Sounds into SoundEngine...");
        soundFiles.clear();
        final File[] files = audioDirectory.listFiles();

        if (files == null) {
            throw new RuntimeException("Could not load Sounds, no Sounds found in Directory " + audioDirectory.getName());
        }

        for (File file : files) {
            if (!file.getName().endsWith(".wav")) {
                continue;
            }

            System.out.println("Loading Sound " + file.getName() + "...");
            soundFiles.add(file);
            System.out.println("Sound " + file.getName() + " successfully loaded...");
        }
    }

    @Nullable
    public File getSoundFile(@NotNull String sound) {
        return soundFiles.stream()
                .filter(file -> file.getName().equals(sound))
                .findFirst().orElse(null);
    }

    public void playSound(@NotNull String sound) {
        playSound(sound, getGame().getSoundVolume());
    }

    public void playSound(@NotNull String sound, float volume) {
        final File soundFile = getSoundFile(sound);
        if(soundFile == null) {
            return;
        }
        try {
            final Clip clip = AudioSystem.getClip();
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEnable() {
        loadSounds();
    }
}
