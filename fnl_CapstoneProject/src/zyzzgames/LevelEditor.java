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
 * Current Version: 1.5
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
import java.util.Arrays;
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

public class LevelEditor
{
	// Starting point of the level relative to the left-side of the window
	private int dx = GameConstants.START_LINE;
	private int d = GameConstants.SAWBLADE_DIAMETER;
	private int ppb = GameConstants.PIXELS_PER_BLOCK;
	private boolean drawHitboxes = false;
	
	// A 2D array of blocks with each array being the position of one block
	private int[][] blocks = {{ gX(1), gY(9) }, { gX(2), gY(9) },
			{ gX(3), gY(9) }, { gX(4), gY(9) }, { gX(5), gY(9) }, { gX(6), gY(9) },
			{ gX(7), gY(9) }, { gX(8), gY(9) }, { gX(9), gY(9) }, { gX(10), gY(9) },
			{ gX(11), gY(9) }, { gX(12), gY(9) }, { gX(13), gY(9) }, { gX(14), gY(9) },
			{ gX(15), gY(9) }, { gX(16), gY(9) }, { gX(17), gY(9) }, { gX(18), gY(9) },
			{ gX(19), gY(9) }, { gX(20), gY(9) }, { gX(21), gY(9) }, { gX(22), gY(9) },
			{ gX(1), gY(8) }, { gX(1), gY(7) }, { gX(1), gY(6) }, { gX(1), gY(5) },
			{ gX(1), gY(4) }, { gX(1), gY(3) }, { gX(1), gY(2) }, { gX(1), gY(1) },
			{ gX(22), gY(8) }, { gX(22), gY(7) }, { gX(22), gY(6) }, { gX(22), gY(5) },
			{ gX(22), gY(4) }, { gX(22), gY(3) }, { gX(22), gY(2) }, { gX(22), gY(1) }};

	// A 2D array of sawblades with each array being the position of one sawblade
	private int[][] sawblades = {{ d, gcX(50), gcY(14) }, { d, gcX(56), gcY(6) }, { d, gcX(61), gcY(8) },
						 { d, gcX(69), gcY(15)}, { d, gcX(76), gcY(3) }, { d, gcX(94), gcY(12) },
						 { d, gcX(97), gcY(5) }, { d, gcX(109), gcY(14) }, { d, gcX(114), gcY(8) },
						 { d, gcX(140), gcY(7) }, { d, gcX(146), gcY(15) }, { d, gcX(152), gcY(7) },
						 { d, gcX(161), gcY(4)}, { d, gcX(166), gcY(14) }, { d, gcX(171), gcY(4) }, { d, gcX(176), gcY(14)},
						 { d, gcX(181), gcY(4)},  { d, gcX(186), gcY(14) }, { d, gcX(191), gcY(4)}, { d, gcX(196), gcY(14)},
						 { d, gcX(211), gcY(4) }, { d, gcX(214), gcY(11) }, { d, gcX(218), gcY(15) }, { d, gcX(225), gcY(15) },
						 { d, gcX(235), gcY(2) }, { d, gcX(240), gcY(15) }, { d, gcX(245), gcY(13) },
						 { d, gcX(261), gcY(2) }, { d, gcX(265), gcY(8) }, { d, gcX(270), gcY(4) }, { d, gcX(271), gcY(12) }};

	// A 2D array of slopes with each array being the position of one slope
	private int[][] slopes = {{ gX(25), gY(0), gX(35), gY(10) }, { gX(35), gY(10), gX(45), gY(0) },
					  { gX(32), gY(18), gX(41), gY(9) }, { gX(41), gY(9), gX(50), gY(18) },
					  { gX(43), gY(7), gX(50), gY(0) }, { gX(43), gY(7), gX(47), gY(11) }, { gX(47), gY(11), gX(58), gY(0) },
					  { gX(50), gY(18), gX(75), gY(18) }, { gX(58), gY(0), gX(67), gY(9) }, { gX(67), gY(9), gX(76), gY(0) },
					  { gX(70), gY(12), gX(76), gY(18) }, { gX(70), gY(12), gX(73), gY(9) }, { gX(76), gY(18), gX(82), gY(12) }, { gX(79), gY(9), gX(82), gY(12) },
					  { gX(75), gY(7), gX(76), gY(6) }, { gX(76), gY(6), gX(77), gY(7) },
					  { gX(77), gY(18), gX(86), gY(9) }, { gX(86), gY(9), gX(95), gY(18) },
					  { gX(81), gY(0), gX(90), gY(9) }, { gX(90), gY(9), gX(95), gY(4)}, { gX(91), gY(0), gX(95), gY(4) },
					  { gX(95), gY(18), gX(102), gY(11) }, { gX(102), gY(11), gX(109), gY(18) },
					  { gX(95), gY(0), gX(106), gY(11) }, { gX(106), gY(11), gX(117), gY(0) }, { gX(109), gY(18), gX(115), gY(18) },
					  { gX(115), gY(18), gX(121), gY(6) }, { gX(121), gY(6), gX(127), gY(18) },
					  { gX(121), gY(0), gX(127), gY(12) }, { gX(127), gY(12), gX(133), gY(0) },
					  { gX(127), gY(18), gX(133), gY(6) }, { gX(133), gY(6), gX(139), gY(18) },
					  { gX(136), gY(0), gX(146), gY(5) }, { gX(146), gY(5), gX(156), gY(0) }, { gX(153), gY(18), gX(158), gY(8) },
					  { gX(138), gY(18), gX(193), gY(18) }, { gX(156), gY(0), gX(199), gY(0) },
					  { gX(192), gY(18), gX(197), gY(8) }, { gX(198), gY(3), gX(201), gY(0) }, { gX(198), gY(3), gX(203), gY(8) }, { gX(203), gY(8), gX(211), gY(0) },
					  { gX(199), gY(18), gX(208), gY(9) }, { gX(208), gY(9), gX(217), gY(18) },
					  { gX(211), gY(0), gX(222), gY(11) }, { gX(222), gY(11), gX(233), gY(0) }, { gX(217), gY(18), gX(232), gY(18) },
					  { gX(228), gY(13), gX(233), gY(18) }, { gX(233), gY(18), gX(238), gY(13) }, { gX(230), gY(13), gX(233), gY(16) }, { gX(233), gY(16), gX(236), gY(13) },
					  { gX(232), gY(13), gX(233), gY(14) }, { gX(233), gY(14), gX(234), gY(13) }, { gX(232), gY(9), gX(233), gY(8) }, { gX(233), gY(8), gX(234), gY(9) },
					  { gX(228), gY(9), gX(233), gY(4) }, { gX(233), gY(4), gX(238), gY(9) }, { gX(230), gY(9), gX(233), gY(6) }, { gX(233), gY(6), gX(236), gY(9) },
					  { gX(240), gY(0), gX(250), gY(10) }, { gX(250), gY(10), gX(260), gY(0) }, { gX(247), gY(18), gX(259), gY(6) }, { gX(259), gY(6), gX(271), gY(18) },
					  { gX(271), gY(18), gX(277), gY(12) }, { gX(277), gY(12), gX(280), gY(18) }, { gX(271), gY(0), gX(277), gY(6) }, { gX(277), gY(6), gX(280), gY(0) },
					  { gX(260), gY(0), gX(271), gY(0) }};
			
	// Using the definitions above, below are 7 different types of portals and their respective positions
	private int[][] speedPortals = {{ gX(26), gY(12) }, { gX(26), gY(10) }};
	private int[][] normalGravityPortals = {{ gX(1), gY(16) }, { gX(63), gY(15) }};
	private int[][] flippedGravityPortals = {{ gX(57), gY(15) }};
	private int[][] normalSizePortals = {{ gX(197), gY(8) }};
	private int[][] miniSizePortals = {{ gX(110), gY(12) }};
	private int[][] wavePortals = {{ gX(25), gY(12) }};
	private int[][] cubePortals = {{ gX(277), gY(13) }, { gX(277), gY(9) }};
	
	// A map consisting of the names of the portals to their respective arrays
	private Map<String, int[][]> allPortals = Map.of("SPP", speedPortals,
			"NGP", normalGravityPortals, "FGP", flippedGravityPortals, "NSP", normalSizePortals,
			"MSP", miniSizePortals, "WVP", wavePortals, "CBP", cubePortals);

	/* 
	 * To make editing easier, the following functions takes in the grid distance and height
	 * and converts them to x and y values that appear in the window. The Y converter allows
	 * you to enter y coordinates from 0 that results in the graphics being painted starting
	 * from the bottom of the window to the top.
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
	
	// Create speed portals based on the difficulty selected by the user.
	public void drawSpeedPortals(Graphics2D g2d, Color c)
	{
		g2d.setColor(c);
		
		for (int i = 0; i < speedPortals.length; i++)
		{
			switch (String.valueOf(GameWindow.getComboBox().getSelectedItem()))
			{
				case "Easy":
					g2d.drawImage(GameConstants.Y, speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0), null);
					if (drawHitboxes) g2d.drawRect(speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0));
					break;
				case "Medium":
					g2d.drawImage(GameConstants.B, speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0), null);
					if (drawHitboxes) g2d.drawRect(speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0));
					break;
				case "Hard":
					g2d.drawImage(GameConstants.G, speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0), null);
					if (drawHitboxes) g2d.drawRect(speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0));
					break;
				case "Insane":
					g2d.drawImage(GameConstants.P, speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0), null);
					if (drawHitboxes) g2d.drawRect(speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0));
					break;
				case "Impossible":
					g2d.drawImage(GameConstants.R, speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0), null);
					if (drawHitboxes) g2d.drawRect(speedPortals[i][0], speedPortals[i][1],
							(int) Math.round((19 * ppb) / 8.0), (int) Math.round((83 * ppb) / 40.0));
					break;					
			}
		}
	}
	
	// Create normal gravity portals.
	public void drawNormalGravityPortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i = 0; i < normalGravityPortals.length; i++)
		{
			if (!(normalGravityPortals[i][0] < -150 || normalGravityPortals[i][0] > 1560))
			{
				g2d.drawImage(pic, normalGravityPortals[i][0], normalGravityPortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes)
				{
					g2d.drawRect(normalGravityPortals[i][0], normalGravityPortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}
	
	// Create flipped gravity portals.
	public void drawFlippedGravityPortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i = 0; i < flippedGravityPortals.length; i++)
		{
			if (!(flippedGravityPortals[i][0] < -150 || flippedGravityPortals[i][0] > 1560))
			{
				g2d.drawImage(pic, flippedGravityPortals[i][0], flippedGravityPortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes)
				{
					g2d.drawRect(flippedGravityPortals[i][0], flippedGravityPortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}
	
	// Create normal size portals.
	public void drawNormalSizePortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i = 0; i < normalSizePortals.length; i++)
		{
			if (!(normalSizePortals[i][0] < -150 || normalSizePortals[i][0] > 1560))
			{
				g2d.drawImage(pic, normalSizePortals[i][0], normalSizePortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes)
				{
					g2d.drawRect(normalSizePortals[i][0], normalSizePortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}
	
	// Create mini size portals.
	public void drawMiniSizePortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i = 0; i < miniSizePortals.length; i++)
		{
			if (!(miniSizePortals[i][0] < -150 || miniSizePortals[i][0] > 1560))
			{
				g2d.drawImage(pic, miniSizePortals[i][0], miniSizePortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes)
				{
					g2d.drawRect(miniSizePortals[i][0], miniSizePortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}
	
	// Create wave portals.
	public void drawWavePortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i = 0; i < wavePortals.length; i++)
		{
			if (!(wavePortals[i][0] < -150 || wavePortals[i][0] > 1560))
			{
				g2d.drawImage(pic, wavePortals[i][0], wavePortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes)
				{
					g2d.drawRect(wavePortals[i][0], wavePortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}
	
	// Create cube portals.
	public void drawCubePortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i = 0; i < cubePortals.length; i++)
		{
			if (!(cubePortals[i][0] < -150 || cubePortals[i][0] > 1560))
			{
				g2d.drawImage(pic, cubePortals[i][0], cubePortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0), null);
				if (drawHitboxes)
				{
					g2d.drawRect(cubePortals[i][0], cubePortals[i][1], (int) Math.round((3 * ppb) / 2.0), (int) Math.round((15 * ppb) / 4.0));
				}
			}
		}
	}
	
	// Create blocks.
	public void drawBlocks(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i = 0; i < blocks.length; i++)
		{
			if (!(blocks[i][1] < -24 || blocks[i][0] > 1560))
			{
				g2d.drawImage(pic, blocks[i][0], blocks[i][1], ppb, ppb, null);
				if (drawHitboxes)
				{
					g2d.drawRect(blocks[i][0], blocks[i][1], ppb, ppb);
				}
			}
		}
	}

	/**
	 * Sawblades: diameter, x-center, y-level;
	 */
	public void drawSawblades(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i = 0; i < sawblades.length; i++)
		{
			if (!((sawblades[i][1] + sawblades[i][0] / 2) < -24 || (sawblades[i][1] - sawblades[i][0] / 2) > 1560))
			{
				g2d.drawImage(pic, sawblades[i][1] - sawblades[i][0] / 2, sawblades[i][2] - sawblades[i][0] / 2, null);
				if (drawHitboxes)
				{
					g2d.fillOval(sawblades[i][1] - sawblades[i][0] / 2, sawblades[i][2] - sawblades[i][0] / 2, sawblades[i][0], sawblades[i][0]);
				}
			}  
		}
	}
	
	/**
	 * Slopes: x-start, y-start, x-end, y-end
	 * Since we are lazy we decided to make ground spikes slope as well.
	 */
	public void drawSlopes(Graphics2D g2d, Color c, Image groundSpike, Image ceilingSpike)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(10);
		g2d.setStroke(stroke);
		
		for (int i = 0; i < slopes.length; i++)
		{
			if (!(slopes[i][1] < -24 || slopes[i][0] > 1560))
			{
				if (slopes[i][1] == slopes[i][3] && slopes[i][1] >= 420)
				{
					for (int j = slopes[i][0]; j < slopes[i][2]; j += ppb)
					{
						g2d.drawImage(groundSpike, j, slopes[i][1] - 23, null);
					}
				}
				
				else if (slopes[i][1] == slopes[i][3] && slopes[i][1] < 420)
				{
					for (int j = slopes[i][0]; j < slopes[i][2]; j += ppb)
					{
						g2d.drawImage(ceilingSpike, j, slopes[i][1], null);
					}
				}
				
				else if (slopes[i][1] != slopes[i][3])
				{
					g2d.draw(new Line2D.Double(slopes[i][0], slopes[i][1], slopes[i][2], slopes[i][3]));
				}
			}
		}
	}

	public void drawProgressBar(Graphics2D g2d, int levelEndPoint, Color barColor, Color progressColor) {
		Stroke stroke = new BasicStroke(3);
		g2d.setStroke(stroke);
		g2d.setColor(progressColor);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillRect(558, 38, Math.round((420 * (GameConstants.START_LINE - dx)) / levelEndPoint), 15);
		g2d.setColor(barColor);
		g2d.drawRect(558, 38, 420, 15);
		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		g2d.drawString((Math.max(0, Math.round((100 * (GameConstants.START_LINE - dx)) / levelEndPoint))) + "%", 986, 54);
	}
	
	public int getDx() {
		return dx;
	}
	
	public void setDx(int newDx) {
		dx = newDx;
	}
	
	public void goForward(int t) {
		for (int j = 0; j < blocks.length; j++) {
			blocks[j][0] -= t;
		}
		for (int j = 0; j < sawblades.length; j++) {
			sawblades[j][1] -= t;
		}
		for (int j = 0; j < slopes.length; j++) {
			slopes[j][0] -= t;
			slopes[j][2] -= t;
		}
		for (int j = 0; j < speedPortals.length; j++) {
			speedPortals[j][0] -= t;
		}
		for (int j = 0; j < normalGravityPortals.length; j++) {
			normalGravityPortals[j][0] -= t;
		}
		for (int j = 0; j < flippedGravityPortals.length; j++) {
			flippedGravityPortals[j][0] -= t;
		}
		for (int j = 0; j < normalSizePortals.length; j++) {
			normalSizePortals[j][0] -= t;
		}
		for (int j = 0; j < miniSizePortals.length; j++) {
			miniSizePortals[j][0] -= t;
		}
		for (int j = 0; j < wavePortals.length; j++) {
			wavePortals[j][0] -= t;
		}
		for (int j = 0; j < cubePortals.length; j++) {
			cubePortals[j][0] -= t;
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
