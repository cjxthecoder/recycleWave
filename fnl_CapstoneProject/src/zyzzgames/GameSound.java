/*
 * Copyright (c) 2022, Team Zyzz and/or its affiliates, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Please contact Sunnyvale, Rockefeller Dr, Apt 3B, CA 94087 USA if you
 * need additional information or have any questions.
 * 
 * Current Version: 1.0
 */

package zyzzgames;

import java.io.File;

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
