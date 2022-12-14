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

/**
 * The GameConstants interface contains the list of constant values used
 * in our game. This includes the ground pixel level, the ceiling pixel
 * level, the size of the player's hitbox, integer value representations
 * for the player's gamemode and gravity, the pixel level for the start
 * line and finish line, amount of pixels per block, diameter of the saws,
 * and double values for the player's speed.
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
	int CUBE = 2;
	int SHIP = 4;
	int BALL = 8;
	int UFO = 16;
	int WAVE = 32;
	int SPIDER = 64;
	
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
	double oneTimes = 1.0;
	double twoTimes = 1.25;
	double threeTimes = 1.5;
	double fourTimes = 2.0;
}
