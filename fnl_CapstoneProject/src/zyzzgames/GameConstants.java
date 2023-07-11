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
 * Current Version: 1.2
 */

package zyzzgames;

import java.awt.Image;
import javax.swing.ImageIcon;

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

public class GameConstants
{
	// Constants related to the game window
	public static final int GROUND = 780;
	public static final int CEILING = 60;
	public static final int PLAYER_HITBOX = 40;
	public static final double MAGIC = 1.0185508475756433;
	
	// Constants related to the player's gamemode
	public static final int CUBE = 2;
	public static final int SHIP = 3;
	public static final int BALL = 4;
	public static final int UFO = 8;
	public static final int WAVE = 16;
	public static final int ROBOT = 32;
	public static final int SPIDER = 64;
	
	// Constants related to the player's gravity
	public static final int DOWN = 1;
	public static final int UP = -1;
	
	// Constants related to the placement of objects in the level editor, and when the player has won the game
	public static final int START_LINE = 300;
	public static final int FINISH_LINE = 11300;
	public static final int PIXELS_PER_BLOCK = 40;
	public static final int SAWBLADE_DIAMETER = 150;
	
	// Constants related to the player's speed
	public static final double HALF_TIMES = 0.5;
	public static final double ONE_TIMES = 1.0;
	public static final double TWO_TIMES = 1.25;
	public static final double THREE_TIMES = 1.5;
	public static final double FOUR_TIMES = 2.0;
	
	// Constants related to the images of the level's structures
	public static final Image BLK = new ImageIcon("block.png").getImage();
	public static final Image NGP = new ImageIcon("normalGravityPortal.png").getImage();
	public static final Image FGP = new ImageIcon("flippedGravityPortal.png").getImage();
	public static final Image NSP = new ImageIcon("normalSizePortal.png").getImage();
	public static final Image MSP = new ImageIcon("miniSizePortal.png").getImage();
	public static final Image WP = new ImageIcon("wavePortal.png").getImage();
	public static final Image CP = new ImageIcon("cubePortal.png").getImage();
	public static final Image GS = new ImageIcon("groundSpike.png").getImage();
	public static final Image CS = new ImageIcon("ceilingSpike.png").getImage();
	public static final Image SB = new ImageIcon("fish.png").getImage();
	
	// Constants related to the images of the level's difficulty
	public static final Image Y = (new ImageIcon("half.png")).getImage();
	public static final Image B = (new ImageIcon("one.png")).getImage();
	public static final Image G = (new ImageIcon("two.png")).getImage();
	public static final Image P = (new ImageIcon("three.png")).getImage();
	public static final Image R = (new ImageIcon("four.png")).getImage();
	
	// Constants related to the images of the player
	public static final Image PCU = new ImageIcon("playerCubeUp.png").getImage();
	public static final Image PCD = new ImageIcon("playerCubeDown.png").getImage();
	public static final Image PWU = new ImageIcon("playerWaveUp.png").getImage();
	public static final Image PWD = new ImageIcon("playerWaveDown.png").getImage();
	public static final Image RB = new ImageIcon("recycleBin.png").getImage();
}
