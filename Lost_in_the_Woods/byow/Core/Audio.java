package byow.Core;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

    String status;
    String filePath;
    AudioInputStream audioInputStream;
    Clip clip;

    public Audio(String filePath)
            throws UnsupportedAudioFileException,
        IOException, LineUnavailableException  {
        this.filePath = filePath;
        clip = AudioSystem.getClip();
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public void play() {
        clip.start();
    }

    public void pause() {
        clip.stop();
    }
}
