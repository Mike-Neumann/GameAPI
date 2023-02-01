package me.xra1ny.gameapi.engines;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public class Sound {
  @Getter(onMethod = @__(@NotNull))
  private final File file;

  @Getter(onMethod = @__(@NotNull))
  private final AudioInputStream audioInputStream;

  Sound(@NotNull File file) throws UnsupportedAudioFileException, IOException {
    this.file = file;
    this.audioInputStream = AudioSystem.getAudioInputStream(file);
  }

  /**
   * Plays this Sound to the Standard AudioSystem
   */
  public void play() {
    new Thread(() -> {
      try {
        final Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
      } catch (LineUnavailableException | IOException e) {
        throw new RuntimeException(e);
      }
    }).start();
  }
}
