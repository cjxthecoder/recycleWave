/*
 * Copyright (c) 2022, 2025, Team Zyzz and/or its affiliates, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Please contact danielcheng2200@gmail.com if you need additional information
 * or have any questions.
 * 
 * Current Version: 1.4
 */

package zyzzgames;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The GameSound class has an Audio that allows the clip to be played. To load
 * the clip, an audio file is instantiated in the other classes. This class uses
 * play() and stop() from the Audio class to start and stop the clip, as well as
 * the setOffset() method to start the music from a certain point.
 * 
 * @author Ali Haryanawalla
 *
 * @since 1.0
 */

public class GameSound
{
	Audio audio;
	
	public GameSound(String file) {
		try {
			audio = new Audio("48000/574484_F-777---Sonic-Blaster_48000.wav");
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	public void startMusic(float seconds) {
		if (!audio.isPlaying()) {
			audio.setOffset(seconds);
			audio.play();
		}
	}
	
	public void stopMusic() {
		audio.stop();
	}
}
