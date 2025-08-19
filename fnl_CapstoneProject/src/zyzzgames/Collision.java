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

import java.awt.Rectangle;
import java.awt.geom.Line2D;

/**
 * The Collision class contains the different methods to check for collision between
 * the player and the game's obstacles. The player is a square, which is equivalent to
 * a rectangle with equal width and height. Since all objects are either a rectangle, 
 * a line, or a circle, 3 collision detection methods are required. The rectangle
 * (intersects()) and line (intersectsLine()) methods are provided by the built-in
 * {@link Rectangle} library, while the intersectsCircle() method is implemented by
 * calculating the nearest distance between the player and the circle, then checking
 * if the difference is less than the circle's radius.
 * 
 * @author Ali Haryanawalla
 *
 * @since 1.0
 */

public class Collision
{	
	private static final int PPB = GameConstants.PIXELS_PER_BLOCK;
	
	public static boolean checkDeathCollision(int[][] slopes, int[][] sawblades, Player p)
	{
		for (int i = 0; i < slopes.length; i++)
		{
			if (new Rectangle(p.getX(), p.getY(), p.getHitbox(), p.getHitbox()).intersectsLine
					(new Line2D.Double(slopes[i][0], slopes[i][1],slopes[i][2], slopes[i][3])))
			{
				return true;
			}
		}
		
		for (int i = 0; i < sawblades.length; i++)
		{
			if (intersectsCircle(new Rectangle(p.getX(), p.getY(), p.getHitbox(), p.getHitbox()),
									sawblades[i][1], sawblades[i][2], sawblades[i][0])) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean checkSpeedCollision(int[][] speedPortals, Player p)
	{
		for (int i = 0; i < speedPortals.length; i++)
		{
			if (new Rectangle(p.getX(), p.getY(), p.getHitbox(), p.getHitbox()).intersects
					(new Rectangle(speedPortals[i][0], speedPortals[i][1], (19 * PPB) / 8, (83 * PPB) / 40)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean checkPortalCollision(int[][] portals, Player p)
	{
		for (int i = 0; i < portals.length; i++)
		{
			if (new Rectangle(p.getX(), p.getY(), p.getHitbox(), p.getHitbox()).intersects
					(new Rectangle(portals[i][0], portals[i][1], (3 * PPB) / 2, (15 * PPB) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean checkPlatformCollision(int[][] platforms, Player p)
	{
		for (int i = 0; i < platforms.length; i++)
		{
			if (new Rectangle(p.getX(), p.getY(), p.getHitbox(), p.getHitbox()).intersectsLine
					(new Line2D.Double(platforms[i][0], platforms[i][1], platforms[i][2], platforms[i][1])))
			{
				p.setPlatformY(platforms[i][1]);
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean intersectsCircle(Rectangle r, int circleX, int circleY, int diameter)
	{
		double testX = circleX;
		double testY = circleY;

		if (circleX < r.x) {  
			testX = r.x;
		}
		else if (circleX > r.x + r.width) {
			testX = r.x + r.width;
		}
		if (circleY < r.y) {
			testY = r.y;
		}
		else if (circleY > r.y + r.height) {
			testY = r.y + r.height;
		}

		double distX = circleX - testX;
		double distY = circleY - testY;
		double distance = Math.sqrt((distX * distX) + (distY * distY));

		return distance <= (double)diameter / 2.0;
	}
}
