import java.io.File;

/**
 * The GameSound class has an Audio that allows the clip to be played. To load
 * the clip, an audio file is instantiated in the other classes. This class uses
 * .play() and .stop() from the Audio class to start and stop the clip.
 * 
 * @author Ali
 *
 * @since 1.0
 */

public class GameSound
{
	Audio audio;

	public void loadMusic()
	{ 
	    try {
	    	if (audio == null) {
	    		audio = new Audio(new File("976863_F-777---Sonic-Blaster-HJfo.wav"));
	    	}
	    	audio.setOffset(38.0);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void startMusic() {
        audio.play();
	}
	
	public void stopMusic() {
		audio.stop();
		audio.setOffset(37.8);
	}
}
