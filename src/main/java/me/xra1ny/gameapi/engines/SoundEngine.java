package me.xra1ny.gameapi.engines;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.Game;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class SoundEngine extends Engine {
    @Getter(onMethod = @__(@NotNull))
    private final File audioDirectory;
    @Getter(onMethod = @__(@NotNull))
    private final List<Sound> sounds = new ArrayList<>();

    public SoundEngine(@NotNull Game game, @NotNull String audioDirectory) {
        super(game);

        this.audioDirectory = new File(audioDirectory);

        if(!this.audioDirectory.exists()) {
            final boolean success = this.audioDirectory.mkdirs();

            if(!success) {
                throw new RuntimeException("Audio File Directory could not be created!");
            }
        }
    }

    private void loadSounds() {
        System.out.println("Loading Sounds into SoundEngine...");
        sounds.clear();
        final File[] files = audioDirectory.listFiles();

        if(files == null) {
            throw new RuntimeException("Could not load Sounds, no Sounds found in Directory " + audioDirectory.getName());
        }

        for(File file : files) {
            if(!file.getName().endsWith(".wav")) {
                continue;
            }

            try {
                System.out.println("Loading Sound " + file.getName() + "...");
                sounds.add(new Sound(file));
                System.out.println("Sound " + file.getName() + " successfully loaded...");
            } catch (UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return The Sound specified by this Name
     */
    public Sound getSound(@NotNull String name) {
        return sounds.stream()
            .filter(sound -> sound.getFile().getName().equals(name))
            .findFirst().orElse(null);
    }

    @Override
    public void onEnable() {
        loadSounds();
    }
}
