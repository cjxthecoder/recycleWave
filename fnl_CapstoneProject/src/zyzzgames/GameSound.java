/*
 * Copyright (c) 2022, Team Zyzz LLC, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Team Zyzz designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Team Zyzz in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * Please contact Sunnyvale, Rockefeller Dr, Apt 3B, CA 94097 USA if you
 * need additional information or have any questions.
 */

package zyzzgames;

import java.io.File;

/**
 * The GameSound class has an Audio that allows the clip to be played. To load
 * the clip, an audio file is instantiated in the other classes. This class uses
 * .play() and .stop() from the Audio class to start and stop the clip.
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
