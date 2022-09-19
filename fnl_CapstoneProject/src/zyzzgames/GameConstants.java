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

/**
 * The GameConstants interface contains the list of constant values used
 * in our game. This includes the ground pixel level, the ceiling pixel level,
 * the size of the player's hitbox, pass-by values for the player's gamemode,
 * gravity, the pixel level for the start line and finish line, amount of pixels
 * per block, diameter of the saws, and double values for the player's speed.
 * 
 * @author Brandon Tsao
 *
 * @since 1.0
 */

public interface GameConstants
{
	// Constants related to the game window
	int GROUND = 780;
	int CEILING = 60;
	int PLAYER_HITBOX = 40;
	double myFactor = 1.0185508475756433;
	
	// Constants related to the player's gamemode
	int CUBE = 1;
	int SHIP = 2;
	int BALL = 4;
	int WAVE = 8;
	
	// Constants related to the player's gravity
	int DOWN = 1;
	int UP = -1;
	
	// Constants related to the placement of objects in the level editor, and when the player has won the game
	int START_LINE = 300;
	int FINISH_LINE = 11300;
	int PIXELS_PER_BLOCK = 40;
	int d = 150;
	
	// Constants related to the player's speed
	double halfTimes = 0.5;
	double oneTimes = 1;
	double twoTimes = 1.25;
	double threeTimes = 1.5;
	double fourTimes = 2;
}