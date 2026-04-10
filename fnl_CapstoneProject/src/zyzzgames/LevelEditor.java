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
 * Current Version: 1.8
 */

package zyzzgames;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The LevelEditor class serves as the text editor and database for all of the
 * objects in our game. Each type of objects is a 2D array containing an array
 * of arrays, each array representing one object in the game. To make editing
 * easier, each parameter of the arrays within the 2D arrays uses a special
 * function that fixes the objects' position to a grid exactly. Because the
 * grid-snapping function for circles is slightly different compared to the
 * other objects, a total of 4 special functions is used. Although not an object
 * in the game, this class also draws the progress bar for the game.
 * 
 * @author Brandon Tsao
 *
 * @since 1.0
 */

public class LevelEditor {
	// Starting point of the level relative to the left-side of the window
	private int dx = GameConstants.START_LINE;
	private int d = GameConstants.SAWBLADE_DIAMETER;
	private int ppb = GameConstants.PIXELS_PER_BLOCK;
	private Stroke default_stroke = new BasicStroke(0);
	private Stroke small_stroke = new BasicStroke(3);
	private Stroke large_stroke = new BasicStroke(10);
	private boolean drawHitboxes;
	private boolean drawTrail;

	public LevelEditor(boolean drawHitboxes, boolean drawTrail) {
		this.drawHitboxes = drawHitboxes;
		this.drawTrail = drawTrail;
	}

	// A 2D array of blocks with each array being the position of one block
	private int[][] blocks = { { gX(1), gY(9) }, { gX(2), gY(9) }, { gX(3), gY(9) }, { gX(4), gY(9) }, { gX(5), gY(9) },
			{ gX(6), gY(9) }, { gX(7), gY(9) }, { gX(8), gY(9) }, { gX(9), gY(9) }, { gX(10), gY(9) },
			{ gX(11), gY(9) }, { gX(12), gY(9) }, { gX(13), gY(9) }, { gX(14), gY(9) }, { gX(15), gY(9) },
			{ gX(16), gY(9) }, { gX(17), gY(9) }, { gX(18), gY(9) }, { gX(19), gY(9) }, { gX(20), gY(9) },
			{ gX(21), gY(9) }, { gX(22), gY(9) }, { gX(1), gY(8) }, { gX(1), gY(7) }, { gX(1), gY(6) },
			{ gX(1), gY(5) }, { gX(1), gY(4) }, { gX(1), gY(3) }, { gX(1), gY(2) }, { gX(1), gY(1) }, { gX(22), gY(8) },
			{ gX(22), gY(7) }, { gX(22), gY(6) }, { gX(22), gY(5) }, { gX(22), gY(4) }, { gX(22), gY(3) },
			{ gX(22), gY(2) }, { gX(22), gY(1) } };

	// A 2D array of sawblades with each array being the position of one sawblade
	private int[][] sawblades = { { gcX(50), gcY(14), d }, { gcX(56), gcY(6), d }, { gcX(61), gcY(8), d },
			{ gcX(69), gcY(15), d }, { gcX(76), gcY(3), d }, { gcX(94), gcY(12), d }, { gcX(97), gcY(5), d },
			{ gcX(109), gcY(14), d }, { gcX(114), gcY(8), d }, { gcX(140), gcY(7), d }, { gcX(146), gcY(15), d },
			{ gcX(152), gcY(7), d }, { gcX(161), gcY(4), d }, { gcX(166), gcY(14), d }, { gcX(171), gcY(4), d },
			{ gcX(176), gcY(14), d }, { gcX(181), gcY(4), d }, { gcX(186), gcY(14), d }, { gcX(191), gcY(4), d },
			{ gcX(196), gcY(14), d }, { gcX(211), gcY(4), d }, { gcX(214), gcY(11), d }, { gcX(218), gcY(15), d },
			{ gcX(225), gcY(15), d }, { gcX(235), gcY(2), d }, { gcX(240), gcY(15), d }, { gcX(245), gcY(13), d },
			{ gcX(261), gcY(2), d }, { gcX(265), gcY(8), d }, { gcX(270), gcY(4), d }, { gcX(271), gcY(12), d } };

	// A 2D array of slopes with each array being the position of one slope
	private int[][] slopes = { { gX(25), gY(0), gX(35), gY(10) }, { gX(35), gY(10), gX(45), gY(0) },
			{ gX(32), gY(18), gX(41), gY(9) }, { gX(41), gY(9), gX(50), gY(18) }, { gX(43), gY(7), gX(50), gY(0) },
			{ gX(43), gY(7), gX(47), gY(11) }, { gX(47), gY(11), gX(58), gY(0) }, { gX(50), gY(18), gX(75), gY(18) },
			{ gX(58), gY(0), gX(67), gY(9) }, { gX(67), gY(9), gX(76), gY(0) }, { gX(70), gY(12), gX(76), gY(18) },
			{ gX(70), gY(12), gX(73), gY(9) }, { gX(76), gY(18), gX(82), gY(12) }, { gX(79), gY(9), gX(82), gY(12) },
			{ gX(75), gY(7), gX(76), gY(6) }, { gX(76), gY(6), gX(77), gY(7) }, { gX(77), gY(18), gX(86), gY(9) },
			{ gX(86), gY(9), gX(95), gY(18) }, { gX(81), gY(0), gX(90), gY(9) }, { gX(90), gY(9), gX(95), gY(4) },
			{ gX(91), gY(0), gX(95), gY(4) }, { gX(95), gY(18), gX(102), gY(11) }, { gX(102), gY(11), gX(109), gY(18) },
			{ gX(95), gY(0), gX(106), gY(11) }, { gX(106), gY(11), gX(117), gY(0) },
			{ gX(109), gY(18), gX(115), gY(18) }, { gX(115), gY(18), gX(121), gY(6) },
			{ gX(121), gY(6), gX(127), gY(18) }, { gX(121), gY(0), gX(127), gY(12) },
			{ gX(127), gY(12), gX(133), gY(0) }, { gX(127), gY(18), gX(133), gY(6) },
			{ gX(133), gY(6), gX(139), gY(18) }, { gX(136), gY(0), gX(146), gY(5) }, { gX(146), gY(5), gX(156), gY(0) },
			{ gX(153), gY(18), gX(158), gY(8) }, { gX(138), gY(18), gX(193), gY(18) },
			{ gX(156), gY(0), gX(199), gY(0) }, { gX(192), gY(18), gX(197), gY(8) }, { gX(198), gY(3), gX(201), gY(0) },
			{ gX(198), gY(3), gX(203), gY(8) }, { gX(203), gY(8), gX(211), gY(0) }, { gX(199), gY(18), gX(208), gY(9) },
			{ gX(208), gY(9), gX(217), gY(18) }, { gX(211), gY(0), gX(222), gY(11) },
			{ gX(222), gY(11), gX(233), gY(0) }, { gX(217), gY(18), gX(232), gY(18) },
			{ gX(228), gY(13), gX(233), gY(18) }, { gX(233), gY(18), gX(238), gY(13) },
			{ gX(230), gY(13), gX(233), gY(16) }, { gX(233), gY(16), gX(236), gY(13) },
			{ gX(232), gY(13), gX(233), gY(14) }, { gX(233), gY(14), gX(234), gY(13) },
			{ gX(232), gY(9), gX(233), gY(8) }, { gX(233), gY(8), gX(234), gY(9) }, { gX(228), gY(9), gX(233), gY(4) },
			{ gX(233), gY(4), gX(238), gY(9) }, { gX(230), gY(9), gX(233), gY(6) }, { gX(233), gY(6), gX(236), gY(9) },
			{ gX(240), gY(0), gX(250), gY(10) }, { gX(250), gY(10), gX(260), gY(0) },
			{ gX(247), gY(18), gX(259), gY(6) }, { gX(259), gY(6), gX(271), gY(18) },
			{ gX(271), gY(18), gX(277), gY(12) }, { gX(277), gY(12), gX(280), gY(18) },
			{ gX(271), gY(0), gX(277), gY(6) }, { gX(277), gY(6), gX(280), gY(0) },
			{ gX(260), gY(0), gX(271), gY(0) } };

	// Using the definitions above, below are 7 different types of portals and their respective positions
	private int[][] speedPortals = { { gX(26), gY(12) }, { gX(26), gY(10) } };
	private int[][] normalGravityPortals = { { gX(1), gY(16) }, { gX(63), gY(15) } };
	private int[][] flippedGravityPortals = { { gX(57), gY(15) } };
	private int[][] normalSizePortals = { { gX(197), gY(8) } };
	private int[][] miniSizePortals = { { gX(110), gY(12) } };
	private int[][] wavePortals = { { gX(25), gY(12) } };
	private int[][] cubePortals = { { gX(277), gY(13) }, { gX(277), gY(9) } };

	// A map consisting of the names of the portals to their corresponding arrays
	private Map<String, int[][]> allPortals = Map.of("SPP", speedPortals, "NGP", normalGravityPortals, "FGP",
			flippedGravityPortals, "NSP", normalSizePortals, "MSP", miniSizePortals, "WVP", wavePortals, "CBP",
			cubePortals);

	// A map consisting of the difficulty names to their corresponding speed portal images
	private Map<String, Image> allSpeedPortals = Map.of("Easy", GameConstants.Y, "Medium", GameConstants.B, "Hard",
			GameConstants.G, "Insane", GameConstants.P, "Impossible", GameConstants.R);

	// A list consisting of the player's trail when the player is wave.
	private List<int[]> waveTrails = new ArrayList<>();

	/*
	 * To make editing easier, the following functions takes in the grid distance
	 * and height and converts them to x and y values that appear in the window.
	 * Negating the y-value allows you to enter y coordinates from 0 that results in
	 * the graphics being painted starting from the bottom of the window to the top.
	 */

	private int gX(int x) {
		return x * ppb + GameConstants.START_LINE;
	}

	private int gY(int y) {
		return -y * ppb + GameConstants.GROUND;
	}

	// cX and cY for coordinates of circles (sawblades)
	private int gcX(int x) {
		return x * ppb + ppb / 2 + GameConstants.START_LINE;
	}

	private int gcY(int y) {
		return -y * ppb + GameConstants.GROUND - ppb / 2;
	}

	/**
	 * Create speed portals based on the difficulty selected by the user.
	 * 
	 * @param g2d
	 * @param c
	 * @param difficulty
	 */
	public void drawSpeedPortals(Graphics2D g2d, Color c, String difficulty) {
		g2d.setColor(c);

		for (int i = 0; i < speedPortals.length; i++) {
			g2d.drawImage(allSpeedPortals.get(difficulty), speedPortals[i][0], speedPortals[i][1],
					(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0), null);
			if (drawHitboxes) {
				g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (int) Math.round((19 * ppb) / 8.0),
						(int) Math.round((83 * ppb) / 40.0));
			}
		}
	}

	/**
	 * Create normal gravity portals.
	 * 
	 * @param g2d
	 * @param c
	 * @param pic
	 */
	public void drawNormalGravityPortals(Graphics2D g2d, Color c, Image pic) {
		g2d.setColor(c);
		g2d.setStroke(default_stroke);

		for (int i = 0; i < normalGravityPortals.length; i++) {
			if (!(normalGravityPortals[i][0] < -165 || normalGravityPortals[i][0] > 1560)) {
				g2d.drawImage(pic, normalGravityPortals[i][0], normalGravityPortals[i][1],
						(int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes) {
					g2d.drawRect(normalGravityPortals[i][0], normalGravityPortals[i][1],
							(int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}

	/**
	 * Create flipped gravity portals.
	 * 
	 * @param g2d
	 * @param c
	 * @param pic
	 */
	public void drawFlippedGravityPortals(Graphics2D g2d, Color c, Image pic) {
		g2d.setColor(c);
		g2d.setStroke(default_stroke);

		for (int i = 0; i < flippedGravityPortals.length; i++) {
			if (!(flippedGravityPortals[i][0] < -165 || flippedGravityPortals[i][0] > 1560)) {
				g2d.drawImage(pic, flippedGravityPortals[i][0], flippedGravityPortals[i][1],
						(int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes) {
					g2d.drawRect(flippedGravityPortals[i][0], flippedGravityPortals[i][1],
							(int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}

	/**
	 * Create normal size portals.
	 * 
	 * @param g2d
	 * @param c
	 * @param pic
	 */
	public void drawNormalSizePortals(Graphics2D g2d, Color c, Image pic) {
		g2d.setColor(c);
		g2d.setStroke(default_stroke);

		for (int i = 0; i < normalSizePortals.length; i++) {
			if (!(normalSizePortals[i][0] < -165 || normalSizePortals[i][0] > 1560)) {
				g2d.drawImage(pic, normalSizePortals[i][0], normalSizePortals[i][1], (int) Math.round((3 * ppb) / 2.0),
						(int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes) {
					g2d.drawRect(normalSizePortals[i][0], normalSizePortals[i][1], (int) Math.round((3 * ppb) / 2.0),
							(int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}

	/**
	 * Create mini size portals.
	 * 
	 * @param g2d
	 * @param c
	 * @param pic
	 */
	public void drawMiniSizePortals(Graphics2D g2d, Color c, Image pic) {
		g2d.setColor(c);
		g2d.setStroke(default_stroke);

		for (int i = 0; i < miniSizePortals.length; i++) {
			if (!(miniSizePortals[i][0] < -165 || miniSizePortals[i][0] > 1560)) {
				g2d.drawImage(pic, miniSizePortals[i][0], miniSizePortals[i][1], (int) Math.round((3 * ppb) / 2.0),
						(int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes) {
					g2d.drawRect(miniSizePortals[i][0], miniSizePortals[i][1], (int) Math.round((3 * ppb) / 2.0),
							(int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}

	/**
	 * Create wave portals.
	 * 
	 * @param g2d
	 * @param c
	 * @param pic
	 */
	public void drawWavePortals(Graphics2D g2d, Color c, Image pic) {
		g2d.setColor(c);
		g2d.setStroke(default_stroke);

		for (int i = 0; i < wavePortals.length; i++) {
			if (!(wavePortals[i][0] < -165 || wavePortals[i][0] > 1560)) {
				g2d.drawImage(pic, wavePortals[i][0], wavePortals[i][1], (int) Math.round((3 * ppb) / 2.0),
						(int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes) {
					g2d.drawRect(wavePortals[i][0], wavePortals[i][1], (int) Math.round((3 * ppb) / 2.0),
							(int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}

	/**
	 * Create cube portals.
	 * 
	 * @param g2d
	 * @param c
	 * @param pic
	 */
	public void drawCubePortals(Graphics2D g2d, Color c, Image pic) {
		g2d.setColor(c);
		g2d.setStroke(default_stroke);

		for (int i = 0; i < cubePortals.length; i++) {
			if (!(cubePortals[i][0] < -165 || cubePortals[i][0] > 1560)) {
				g2d.drawImage(pic, cubePortals[i][0], cubePortals[i][1], (int) Math.round((3 * ppb) / 2.0),
						(int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes) {
					g2d.drawRect(cubePortals[i][0], cubePortals[i][1], (int) Math.round((3 * ppb) / 2.0),
							(int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}

	/**
	 * Blocks: x-position, y-level.
	 * 
	 * @param g2d
	 * @param c
	 * @param pic
	 */
	public void drawBlocks(Graphics2D g2d, Color c, Image pic) {
		g2d.setColor(c);
		g2d.setStroke(default_stroke);

		for (int i = 0; i < blocks.length; i++) {
			if (!(blocks[i][0] + ppb < -24 || blocks[i][0] > 1560)) {
				g2d.drawImage(pic, blocks[i][0], blocks[i][1], ppb, ppb, null);
				if (drawHitboxes) {
					g2d.drawRect(blocks[i][0], blocks[i][1], ppb, ppb);
				}
			}
		}
	}

	/**
	 * Sawblades: x-center, y-center, diameter.
	 * 
	 * @param g2d
	 * @param c
	 * @param pic
	 */
	public void drawSawblades(Graphics2D g2d, Color c, Image pic) {
		g2d.setColor(c);
		g2d.setStroke(default_stroke);

		for (int i = 0; i < sawblades.length; i++) {
			if (!((sawblades[i][0] + sawblades[i][2] / 2) < -24 || (sawblades[i][0] - sawblades[i][2] / 2) > 1560)) {
				g2d.drawImage(pic, sawblades[i][0] - sawblades[i][2] / 2, sawblades[i][1] - sawblades[i][2] / 2, null);
				if (drawHitboxes) {
					g2d.drawOval(sawblades[i][0] - sawblades[i][2] / 2, sawblades[i][1] - sawblades[i][2] / 2,
							sawblades[i][2], sawblades[i][2]);
				}
			}
		}
	}

	/**
	 * Slopes: x-start, y-start, x-end, y-end. <br>
	 * Since we are lazy we decided to make ground spikes slope as well.
	 * 
	 * @param g2d
	 * @param c
	 * @param h
	 * @param groundSpike
	 * @param ceilingSpike
	 */
	public void drawSlopes(Graphics2D g2d, Color c, Color h, Image groundSpike, Image ceilingSpike) {
		for (int i = 0; i < slopes.length; i++) {
			if (!(slopes[i][1] < -24 || slopes[i][0] > 1560)) {
				if (slopes[i][1] == slopes[i][3] && slopes[i][1] >= 420) {
					for (int j = slopes[i][0]; j < slopes[i][2]; j += ppb) {
						g2d.drawImage(groundSpike, j, slopes[i][1] - 23, null);
					}
				}

				else if (slopes[i][1] == slopes[i][3] && slopes[i][1] < 420) {
					for (int j = slopes[i][0]; j < slopes[i][2]; j += ppb) {
						g2d.drawImage(ceilingSpike, j, slopes[i][1], null);
					}
				}

				else if (slopes[i][1] != slopes[i][3]) {
					if (drawHitboxes) {
						g2d.setColor(h);
						g2d.setStroke(default_stroke);
						g2d.draw(new Line2D.Double(slopes[i][0], slopes[i][1], slopes[i][2], slopes[i][3]));
					}

					else {
						g2d.setColor(c);
						g2d.setStroke(large_stroke);
						g2d.draw(new Line2D.Double(slopes[i][0], slopes[i][1], slopes[i][2], slopes[i][3]));
					}
				}
			}
		}
	}

	/**
	 * Adds the player's current position to the wave trail list.
	 * 
	 * @param p
	 * @param changeSize
	 */
	public void addWaveTrail(boolean changeSize, Player p) {
		if (drawTrail) {
			int[] wt = new int[3];
			wt[0] = p.getX();
			wt[1] = p.getY();
			wt[2] = changeSize ? (p.playerIsMini() ? 2 : 1) : 1;
			waveTrails.add(wt);
			if (waveTrails.size() > 165) {
				waveTrails.remove(0);
			}
		}
	}

	/**
	 * Draws the player's wave trail.
	 * 
	 * @param g2d
	 * @param c
	 */
	public void drawWaveTrail(Graphics2D g2d, Color c) {
		if (drawTrail) {
			for (int i = 0; i < waveTrails.size(); i++) {
				g2d.setColor(c);
				g2d.fillRect(waveTrails.get(i)[0], waveTrails.get(i)[1],
						(int) Math.round(ppb / (2.5 * waveTrails.get(i)[2])),
						(int) Math.round(ppb / (2.5 * waveTrails.get(i)[2])));
			}
		}
	}

	/**
	 * Resets the wave trail of the player.
	 */
	public void resetWaveTrail() {
		if (drawTrail) {
			waveTrails.clear();
		}
	}

	/**
	 * Draws the progress bar based on the player's current posiition.
	 * 
	 * @param g2d
	 * @param levelEndPoint
	 * @param barColor
	 * @param progressColor
	 */
	public void drawProgressBar(Graphics2D g2d, int levelEndPoint, Color barColor, Color progressColor) {
		g2d.setStroke(small_stroke);
		g2d.setColor(progressColor);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillRect(558, 38, Math.round((420 * (GameConstants.START_LINE - dx)) / levelEndPoint), 15);
		g2d.setColor(barColor);
		g2d.drawRect(558, 38, 420, 15);
		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		g2d.drawString((Math.max(0, Math.round((100 * (GameConstants.START_LINE - dx)) / levelEndPoint))) + "%", 986,
				54);
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int newDx) {
		dx = newDx;
	}

	public void goForward(int t) {
		for (int i = 0; i < blocks.length; i++) {
			blocks[i][0] -= t;
		}
		for (int i = 0; i < sawblades.length; i++) {
			sawblades[i][0] -= t;
		}
		for (int i = 0; i < slopes.length; i++) {
			slopes[i][0] -= t;
			slopes[i][2] -= t;
		}
		for (int i = 0; i < speedPortals.length; i++) {
			speedPortals[i][0] -= t;
		}
		for (int i = 0; i < normalGravityPortals.length; i++) {
			normalGravityPortals[i][0] -= t;
		}
		for (int i = 0; i < flippedGravityPortals.length; i++) {
			flippedGravityPortals[i][0] -= t;
		}
		for (int i = 0; i < normalSizePortals.length; i++) {
			normalSizePortals[i][0] -= t;
		}
		for (int i = 0; i < miniSizePortals.length; i++) {
			miniSizePortals[i][0] -= t;
		}
		for (int i = 0; i < wavePortals.length; i++) {
			wavePortals[i][0] -= t;
		}
		for (int i = 0; i < cubePortals.length; i++) {
			cubePortals[i][0] -= t;
		}
		if (drawTrail) {
			for (int i = 0; i < waveTrails.size(); i++) {
				waveTrails.get(i)[0] -= t;
			}
		}
		setDx(dx - t);
	}

	public int[][] getBlocks() {
		return blocks;
	}

	public int[][] getSawblades() {
		return sawblades;
	}

	public int[][] getSlopes() {
		return slopes;
	}

	public int[][] getPortals(String portalType) {
		return allPortals.get(portalType);
	}
}
