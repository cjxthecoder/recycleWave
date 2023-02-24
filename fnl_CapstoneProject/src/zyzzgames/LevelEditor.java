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

import javax.swing.ImageIcon;

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

public class LevelEditor implements GameConstants
{
	// Starting point of the level relative to the left-side of the window
	public static int dx = START_LINE;
	
	// A 2D array of platforms with each array being the position of one platform 
	int[][] platforms = {{ X(1), Y(9), X(23) }};

	// A 2D array of walls with each array being the position of one wall
	int[][] walls = {{ Y(8), Y(0), X(1) }, { Y(8), Y(0), X(22) }};

	// A 2D array of sawblades with each array being the position of one sawblade
	int[][] sawblades = {{ D, cX(50), cY(14) }, { D, cX(56), cY(6) }, { D, cX(61), cY(8) },
						 { D, cX(69), cY(15)}, { D, cX(76), cY(3) }, { D, cX(94), cY(12) },
						 { D, cX(97), cY(5) }, { D, cX(109), cY(14) }, { D, cX(114), cY(8) },
						 { D, cX(140), cY(7) }, { D, cX(146), cY(15) }, { D, cX(152), cY(7) },
						 { D, cX(161), cY(4)}, { D, cX(166), cY(14) }, { D, cX(171), cY(4) }, { D, cX(176), cY(14)},
						 { D, cX(181), cY(4)},  { D, cX(186), cY(14) }, { D, cX(191), cY(4)}, { D, cX(196), cY(14)},
						 { D, cX(211), cY(4) }, { D, cX(214), cY(11) }, { D, cX(218), cY(15) }, { D, cX(225), cY(15) },
						 { D, cX(235), cY(2) }, { D, cX(240), cY(15) }, { D, cX(245), cY(13) },
						 { D, cX(261), cY(2) }, { D, cX(265), cY(8) }, { D, cX(270), cY(4) }, { D, cX(271), cY(12) }};

	// A 2D array of slopes with each array being the position of one slope
	int[][] slopes = {{ X(25), Y(0), X(35), Y(10) }, { X(35), Y(10), X(45), Y(0) },
					  { X(32), Y(18), X(41), Y(9) }, { X(41), Y(9), X(50), Y(18) },
					  { X(43), Y(7), X(50), Y(0) }, { X(43), Y(7), X(47), Y(11) }, { X(47), Y(11), X(58), Y(0) },
					  { X(50), Y(18), X(75), Y(18) }, { X(58), Y(0), X(67), Y(9) }, { X(67), Y(9), X(76), Y(0) },
					  { X(70), Y(12), X(76), Y(18) }, { X(70), Y(12), X(73), Y(9) }, { X(76), Y(18), X(82), Y(12) }, { X(79), Y(9), X(82), Y(12) },
					  { X(75), Y(7), X(76), Y(6) }, { X(76), Y(6), X(77), Y(7) },
					  { X(77), Y(18), X(86), Y(9) }, { X(86), Y(9), X(95), Y(18) },
					  { X(81), Y(0), X(90), Y(9) }, { X(90), Y(9), X(95), Y(4)}, { X(91), Y(0), X(95), Y(4) },
					  { X(95), Y(18), X(102), Y(11) }, { X(102), Y(11), X(109), Y(18) },
					  { X(95), Y(0), X(106), Y(11) }, { X(106), Y(11), X(117), Y(0) }, { X(109), Y(18), X(115), Y(18) },
					  { X(115), Y(18), X(121), Y(6) }, { X(121), Y(6), X(127), Y(18) },
					  { X(121), Y(0), X(127), Y(12) }, { X(127), Y(12), X(133), Y(0) },
					  { X(127), Y(18), X(133), Y(6) }, { X(133), Y(6), X(139), Y(18) },
					  { X(136), Y(0), X(146), Y(5) }, { X(146), Y(5), X(156), Y(0) }, { X(153), Y(18), X(158), Y(8) },
					  { X(138), Y(18), X(193), Y(18) }, { X(156), Y(0), X(199), Y(0) },
					  { X(192), Y(18), X(197), Y(8) }, { X(198), Y(3), X(201), Y(0) }, { X(198), Y(3), X(203), Y(8) }, { X(203), Y(8), X(211), Y(0) },
					  { X(199), Y(18), X(208), Y(9) }, { X(208), Y(9), X(217), Y(18) },
					  { X(211), Y(0), X(222), Y(11) }, { X(222), Y(11), X(233), Y(0) }, { X(217), Y(18), X(232), Y(18) },
					  { X(228), Y(13), X(233), Y(18) }, { X(233), Y(18), X(238), Y(13) }, { X(230), Y(13), X(233), Y(16) }, { X(233), Y(16), X(236), Y(13) },
					  { X(232), Y(13), X(233), Y(14) }, { X(233), Y(14), X(234), Y(13) }, { X(232), Y(9), X(233), Y(8) }, { X(233), Y(8), X(234), Y(9) },
					  { X(228), Y(9), X(233), Y(4) }, { X(233), Y(4), X(238), Y(9) }, { X(230), Y(9), X(233), Y(6) }, { X(233), Y(6), X(236), Y(9) },
					  { X(240), Y(0), X(250), Y(10) }, { X(250), Y(10), X(260), Y(0) }, { X(247), Y(18), X(259), Y(6) }, { X(259), Y(6), X(271), Y(18) },
					  { X(271), Y(18), X(277), Y(12) }, { X(277), Y(12), X(280), Y(18) }, { X(271), Y(0), X(277), Y(6) }, { X(277), Y(6), X(280), Y(0) },
					  { X(260), Y(0), X(271), Y(0) }};
			
	// Using the definitions above, below are 7 different types of portals and there respective positions
	int[][] speedPortals = {{ X(26), Y(12) }, { X(26), Y(10) }};
	int[][] normalGravityPortals = {{ X(1), Y(16) }, { X(63), Y(15) }};
	int[][] flippedGravityPortals = {{ X(57), Y(15) }};
	int[][] normalSizePortals = {{ X(197), Y(8) }};
	int[][] miniSizePortals = {{ X(110), Y(12) }};
	int[][] wavePortals = {{ X(25), Y(12) }};
	int[][] cubePortals = {{ X(277), Y(13) }, { X(277), Y(9) }};

	/* 
	 * To make editing easier, the following functions takes in the grid distance and height
	 * and converts them to x and y values that appear in the window. The Y converter allows
	 * you to enter y coordinates from 0 that results in the graphics being painted starting
	 * from the bottom of the window to the top.
	 */
	
	private int X(int x) {
		return x * PIXELS_PER_BLOCK + dx;
	}
	
	private int Y(int y) {
		return -y * PIXELS_PER_BLOCK + GROUND;
	}
	
	// cX and cY for coordinates of circles (sawblades)
	private int cX(int x) {
		return x * PIXELS_PER_BLOCK + PIXELS_PER_BLOCK / 2 + dx;
	}
	
	private int cY(int y) {
		return -y * PIXELS_PER_BLOCK + GROUND - PIXELS_PER_BLOCK / 2;
	}
	
	public void createSpeedPortals(Graphics2D g2d, Color c)
	{
		Image pic1 = (new ImageIcon("half.png")).getImage();
		Image pic2 = (new ImageIcon("one.png")).getImage();
		Image pic3 = (new ImageIcon("two.png")).getImage();
		Image pic4 = (new ImageIcon("three.png")).getImage();
		Image pic5 = (new ImageIcon("four.png")).getImage();
		
		g2d.setColor(c);
		
		for (int i=0; i<speedPortals.length; i++)
		{
			switch (String.valueOf(GameWindow.comboBox.getSelectedItem()))
			{
				case "Easy":
					g2d.drawImage(pic1, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
//					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
					break;
				case "Medium":
					g2d.drawImage(pic2, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
//					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
					break;
				case "Hard":
					g2d.drawImage(pic3, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
//					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
				case "Insane":
					g2d.drawImage(pic4, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
//					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
					break;
				case "Impossible":
					g2d.drawImage(pic5, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
//					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
					break;					
			}
		}
	}
	
	// Create normal gravity portals.
	public void createNormalGravityPortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i=0; i<normalGravityPortals.length; i++)
		{
			if (!(normalGravityPortals[i][0] < -150 || normalGravityPortals[i][0] > 1550))
			{
//				g2d.drawRect(normalGravityPortals[i][0], flippedGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
				g2d.drawImage(pic, normalGravityPortals[i][0], normalGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
			}
		}
	}
	
	// Create flipped gravity portals. (unused)
	public void createFlippedGravityPortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i=0; i<flippedGravityPortals.length; i++)
		{
			if (!(flippedGravityPortals[i][0] < -150 || flippedGravityPortals[i][0] > 1550))
			{
//				g2d.drawRect(flippedGravityPortals[i][0], flippedGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
				g2d.drawImage(pic, flippedGravityPortals[i][0], flippedGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
			}
		}
	}
	
	// Create normal size portals.
	public void createNormalSizePortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i=0; i<normalSizePortals.length; i++)
		{
			if (!(normalSizePortals[i][0] < -150 || normalSizePortals[i][0] > 1550))
			{
//				g2d.drawRect(normalSizePortals[i][0], normalSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
				g2d.drawImage(pic, normalSizePortals[i][0], normalSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
			}
		}
	}
	
	// Create mini size portals.
	public void createMiniSizePortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i=0; i<miniSizePortals.length; i++)
		{
			if (!(miniSizePortals[i][0] < -150 || miniSizePortals[i][0] > 1550))
			{
//				g2d.drawRect(miniSizePortals[i][0], miniSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
				g2d.drawImage(pic, miniSizePortals[i][0], miniSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
			}
		}
	}
	
	// Create wave portals.
	public void createWavePortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i=0; i<wavePortals.length; i++)
		{
			if (!(wavePortals[i][0] < -150 || wavePortals[i][0] > 1550))
			{
//				g2d.drawRect(wavePortals[i][0], wavePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
				g2d.drawImage(pic, wavePortals[i][0], wavePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
			}
		}
	}
	
	// Create cube portals.
	public void createCubePortals(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i=0; i<cubePortals.length; i++)
		{
			if (!(cubePortals[i][0] < -150 || cubePortals[i][0] > 1550))
			{
//				g2d.drawRect(cubePortals[i][0], cubePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
				g2d.drawImage(pic, cubePortals[i][0], cubePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
			}
		}
	}
	
	/**
	 * Platforms: x1, y-level, x2; platform length is x2 - x1 + 1.
	 */
	public void createPlatforms(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i=0; i<platforms.length; i++)
		{
			if (!(platforms[i][1] < -10 || platforms[i][0] > 1550))
			{
//				g2d.drawLine(platforms[i][0], platforms[i][1], platforms[i][2], platforms[i][1]);
				for (int j=platforms[i][0]; j<platforms[i][2]; j+=PIXELS_PER_BLOCK)
				{
					g2d.drawImage(pic, j, platforms[i][1], PIXELS_PER_BLOCK, PIXELS_PER_BLOCK, null);
				}
			}
		}
	}

	/**
	 * Walls: y1, y2, x-position; wall height is y2 - y1 + 1.
	 */
	public void createWalls(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i=0; i<walls.length; i++)
		{
			if (!(walls[i][2] < -10 || walls[i][2] > 1550))
			{
//				g2d.drawLine(walls[i][2], walls[i][0], walls[i][2], walls[i][1]);
				for (int j=walls[i][0]; j<walls[i][1]; j+=PIXELS_PER_BLOCK)
				{
					g2d.drawImage(pic, walls[i][2], j, PIXELS_PER_BLOCK, PIXELS_PER_BLOCK, null);
				}
			}
		}
	}

	/**
	 * Sawblades: diameter, x-center, y-level;
	 */
	public void createSawblades(Graphics2D g2d, Color c, Image pic)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(0);
		g2d.setStroke(stroke);
		
		for (int i=0; i<sawblades.length; i++)
		{
			if (!((sawblades[i][1] + sawblades[i][0] / 2) < -10 || (sawblades[i][1] - sawblades[i][0] / 2) > 1550))
			{
//				g2d.fillOval(sawblades[i][1] - sawblades[i][0] / 3, sawblades[i][2] -  sawblades[i][0] / 3, (2 * sawblades[i][0]) / 3, (2 * sawblades[i][0]) / 3);
				g2d.drawImage(pic, sawblades[i][1] - sawblades[i][0] / 2, sawblades[i][2] - sawblades[i][0] / 2, null);
			}  
		}
	}
	
	/**
	 * Slopes: x-start, y-start, x-end, y-end
	 * Since we are lazy we decided to make ground spikes slope as well.
	 */
	public void createSlopes(Graphics2D g2d, Color c, Image groundSpike, Image ceilingSpike)
	{
		g2d.setColor(c);
		Stroke stroke = new BasicStroke(10);
		g2d.setStroke(stroke);
		
		for (int i=0; i<slopes.length; i++)
		{
			if (!(slopes[i][1] < -10 || slopes[i][0] > 1550))
			{
				if (slopes[i][1] == slopes[i][3] && slopes[i][1] >= 420)
				{
					for (int j=slopes[i][0]; j<slopes[i][2]; j+=PIXELS_PER_BLOCK)
					{
						g2d.drawImage(groundSpike, j, slopes[i][1] - 23, null);
					}
				}
				
				else if (slopes[i][1] == slopes[i][3] && slopes[i][1] < 420)
				{
					for (int j=slopes[i][0]; j<slopes[i][2]; j+=PIXELS_PER_BLOCK)
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

	public void drawProgressBar(Graphics2D g2d, int levelEndPoint, Color barColor, Color progressColor)
	{
		Stroke stroke = new BasicStroke(3);
		g2d.setStroke(stroke);
		g2d.setColor(progressColor);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillRect(558, 38, (420 * (dx - START_LINE)) / levelEndPoint, 15);
		g2d.setColor(barColor);
		g2d.drawRect(558, 38, 420, 15);
		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		g2d.drawString((Math.max(0, (100 * (dx - START_LINE)) / levelEndPoint)) + "%", 986, 54);
	}
	
	public static void setDx(int x) {
		dx = x;
	}
	
	public static void goForward(int t) {
		setDx(dx - t);
	}
}
