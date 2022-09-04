import java.io.File;

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
