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
 * Current Version: 1.1
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
	double MY_FACTOR = 1.0185508475756433;
	
	// Constants related to the player's gamemode
	byte CUBE = 2;
	byte SHIP = 4;
	byte BALL = 8;
	byte UFO = 16;
	byte WAVE = 32;
	byte SPIDER = 64;
	
	// Constants related to the player's gravity
	int DOWN = 1;
	int UP = -1;
	
	// Constants related to the placement of objects in the level editor, and when the player has won the game
	int START_LINE = 300;
	int FINISH_LINE = 11300;
	int PIXELS_PER_BLOCK = 40;
	int D = 150;
	
	// Constants related to the player's speed
	double HALF_TIMES = 0.5;
	double ONE_TIMES = 1.0;
	double TWO_TIMES = 1.25;
	double THREE_TIMES = 1.5;
	double FOUR_TIMES = 2.0;
}
