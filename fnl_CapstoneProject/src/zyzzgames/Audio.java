/*
 * Copyright (c) 2022, Team Zyzz and/or its affiliates, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Please contact danielcheng2200@gmail.com if you need additional information
 * or have any questions.
 * 
 * Current Version: 1.3
 */

package zyzzgames;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The Audio class is for implementing sound in-game. An audio has a {@link Clip}
 * that can be reset, set to a certain frame, be started, or be stopped.
 * 
 * @author Ali Haryanawalla
 *
 * @since 1.0
 */

public class Audio
{
	private Clip clip;
	private String src;
	
	public Audio(String source) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		src = source;
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(src));
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		
		// DO NOT CHANGE THE FOLLOWING FOR YOUR OWN SAFETY
		FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gain.setValue(-20.0f);
	}
	
	public boolean isPlaying() {
		return clip.isRunning();
	}
	
	public void reset() {
		clip.stop();
		clip.setFramePosition(0);
	}
	
	public void setOffset(float seconds) {
		int hz = Integer.parseInt(src.substring(0, 5));
		clip.setFramePosition((int)(hz * seconds));
	}

	public void play() {
		clip.start();
	}

	public void stop() {
		clip.stop();
	}
}
