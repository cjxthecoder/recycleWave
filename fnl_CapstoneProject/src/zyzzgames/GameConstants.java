/*
 * Copyright (c) 2022, 2026, Team Zyzz and/or its affiliates, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Please contact danielcheng2200@gmail.com if you need additional information
 * or have any questions.
 * 
 * Current Version: 1.9
 */

package zyzzgames;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * The GameConstants class contains the list of constant values used in our
 * game. This includes the ground pixel level, the ceiling pixel level, the size
 * of the player's hitbox, integer value representations for the player's
 * gamemode and gravity, the pixel level for the start line and finish line,
 * amount of pixels per block, diameter of the saws, and float values for the
 * player's speed.
 * 
 * @author Brandon Tsao
 *
 * @since 1.0
 */

public class GameConstants {
	// Constants related to the game window
	public static final int GROUND = 780;
	public static final int CEILING = 60;
	public static final int PLAYER_HITBOX = 40;
	public static final double MAGIC = 1.0185508475756433;

	// Constants related to the player's gamemode
	public static final int CUBE = 12;
	public static final int SHIP = 13;
	public static final int BALL = 15;
	public static final int UFO = 14;
	public static final int WAVE = 10;
	public static final int ROBOT = 11;
	public static final int SPIDER = 9;
	public static final int SWING = 8;

	// Constants related to the player's gravity
	public static final int DOWN = 1;
	public static final int UP = -1;

	// Constants related to the placement of objects in the level editor, and when
	// the player has won the game
	public static final int START_LINE = 320;
	public static final int FINISH_LINE = 11360;
	public static final int PIXELS_PER_BLOCK = 40;
	public static final int SAWBLADE_DIAMETER = 150;

	// Constants related to the player's speed
	public static final float HALF_TIMES = 0.5F;
	public static final float ONE_TIMES = 0.75F;
	public static final float TWO_TIMES = 1F;
	public static final float THREE_TIMES = 1.25F;
	public static final float FOUR_TIMES = 1.5F;
	public static final float IMPOSSIBLE = 2.0F;
	public static final float SILENT = 2.5F;

	// Constants related to the game's colors
	public static final Color CORAL_PINK = new Color(240, 20, 162);
	public static final Color WATER_BLUE = new Color(52, 106, 150);
	public static final Color PLASTIC_BLUE = new Color(35, 182, 228);

	// Constants related to the images of the level's structures
	private static final URL BLK_URL = ClassLoader.getSystemResource("block.png");
	private static final URL NGP_URL = ClassLoader.getSystemResource("normalGravityPortal.png");
	private static final URL FGP_URL = ClassLoader.getSystemResource("flippedGravityPortal.png");
	private static final URL NSP_URL = ClassLoader.getSystemResource("normalSizePortal.png");
	private static final URL MSP_URL = ClassLoader.getSystemResource("miniSizePortal.png");
	private static final URL WVP_URL = ClassLoader.getSystemResource("wavePortal.png");
	private static final URL CBP_URL = ClassLoader.getSystemResource("cubePortal.png");
	private static final URL GS_URL = ClassLoader.getSystemResource("groundSpike.png");
	private static final URL CS_URL = ClassLoader.getSystemResource("ceilingSpike.png");
	private static final URL SB_URL = ClassLoader.getSystemResource("fish.png");

	// Constants related to the images of the level's difficulty
	private static final URL Y_URL = ClassLoader.getSystemResource("half.png");
	private static final URL B_URL = ClassLoader.getSystemResource("one.png");
	private static final URL G_URL = ClassLoader.getSystemResource("two.png");
	private static final URL P_URL = ClassLoader.getSystemResource("three.png");
	private static final URL R_URL = ClassLoader.getSystemResource("four.png");
	private static final URL V_URL = ClassLoader.getSystemResource("five.png");

	// Constants related to the images of the player
	private static final URL PCU_URL = ClassLoader.getSystemResource("playerCubeUp.png");
	private static final URL PCD_URL = ClassLoader.getSystemResource("playerCubeDown.png");
	private static final URL PWU_URL = ClassLoader.getSystemResource("playerWaveUp.png");
	private static final URL PWD_URL = ClassLoader.getSystemResource("playerWaveDown.png");
	private static final URL RCB_URL = ClassLoader.getSystemResource("recycleBin.png");

	// A map consisting of the difficulty names to their corresponding speed portal images
	public static final Map<String, Image> DIFF_IMG = new HashMap<>();

	// A map consisting of the difficulty names to their corresponding speeds
	public static final Map<String, Float> DIFF_VAL = new HashMap<>();

	// Image assets (initialized at runtime via loadResources)
	public static Image BLK = null;
	public static Image NGP = null;
	public static Image FGP = null;
	public static Image NSP = null;
	public static Image MSP = null;
	public static Image WVP = null;
	public static Image CBP = null;
	public static Image GS = null;
	public static Image CS = null;
	public static Image SB = null;
	public static Image Y = null;
	public static Image B = null;
	public static Image G = null;
	public static Image P = null;
	public static Image R = null;
	public static Image V = null;
	public static Image PCU = null;
	public static Image PCD = null;
	public static Image PWU = null;
	public static Image PWD = null;
	public static Image RCB = null;

	// Load all image resources from the classpath and initializes related mappings
	public static void loadResources() {
		try {
			BLK = ImageIO.read(BLK_URL);
			NGP = ImageIO.read(NGP_URL);
			FGP = ImageIO.read(FGP_URL);
			NSP = ImageIO.read(NSP_URL);
			MSP = ImageIO.read(MSP_URL);
			WVP = ImageIO.read(WVP_URL);
			CBP = ImageIO.read(CBP_URL);
			GS = ImageIO.read(GS_URL);
			CS = ImageIO.read(CS_URL);
			SB = ImageIO.read(SB_URL);
			Y = ImageIO.read(Y_URL);
			B = ImageIO.read(B_URL);
			G = ImageIO.read(G_URL);
			P = ImageIO.read(P_URL);
			R = ImageIO.read(R_URL);
			V = ImageIO.read(V_URL);
			PCU = ImageIO.read(PCU_URL);
			PCD = ImageIO.read(PCD_URL);
			PWU = ImageIO.read(PWU_URL);
			PWD = ImageIO.read(PWD_URL);
			RCB = ImageIO.read(RCB_URL);
			DIFF_IMG.put("Easy", Y);
			DIFF_IMG.put("Medium", B);
			DIFF_IMG.put("Hard", G);
			DIFF_IMG.put("Insane", P);
			DIFF_IMG.put("Extreme", R);
			DIFF_IMG.put("Impossible", V);
			DIFF_VAL.put("Easy", HALF_TIMES);
			DIFF_VAL.put("Medium", ONE_TIMES);
			DIFF_VAL.put("Hard", TWO_TIMES);
			DIFF_VAL.put("Insane", THREE_TIMES);
			DIFF_VAL.put("Extreme", FOUR_TIMES);
			DIFF_VAL.put("Impossible", IMPOSSIBLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
