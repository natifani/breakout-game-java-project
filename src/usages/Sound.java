
package usages;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
    private Clip clip;
    private boolean on;			

    public Sound (String filename) {			
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        	
            e.printStackTrace();
        }
        
        on = true;
    }

    public void stop(){		
        if(clip == null) {
            return;
        }
        clip.stop();
        on = false;
    }

    public void play() {	
        if(clip == null) {
            return;
        }
        clip.setFramePosition(0);
        clip.start();
        on = true;
    }

    public void loop() {	
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        on = true;
    }
    
    public boolean checkSound() {
    	
    	return on;
    }
}
