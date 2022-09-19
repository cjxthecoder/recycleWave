/*
 * Copyright (c) 2022, Team Zyzz LLC, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Please contact Sunnyvale, Rockefeller Dr, Apt 3B, CA 94097 USA if you
 * need additional information or have any questions.
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

public class Collision implements GameConstants
{
	public boolean checkDeathCollisions()
	{
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.slopes.length; i++)
		{
			if (new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox()).intersectsLine
					(new Line2D.Double(lvl.slopes[i][0], lvl.slopes[i][1],lvl.slopes[i][2], lvl.slopes[i][3])))
			{
				return true;
			}
		}
		
		for (int i=0; i<lvl.sawblades.length; i++)
		{
			if (intersectsCircle(new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox()),
									lvl.sawblades[i][1], lvl.sawblades[i][2], lvl.sawblades[i][0])) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkSpeedCollision()
	{
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.speedPortal.length; i++)
		{
			if (new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox()).intersects
					(new Rectangle(lvl.speedPortal[i][0], lvl.speedPortal[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40)));
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkNormalGravityCollision()
	{
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.normalGravityPortals.length; i++)
		{
			if (new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox()).intersects
					(new Rectangle(lvl.normalGravityPortals[i][0], lvl.normalGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkMiniSizeCollision()
	{
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.miniSizePortals.length; i++)
		{
			if (new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox()).intersects
					(new Rectangle(lvl.miniSizePortals[i][0], lvl.miniSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkNormalSizeCollision()
	{
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.normalSizePortals.length; i++)
		{
			if (new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox()).intersects
					(new Rectangle(lvl.normalSizePortals[i][0], lvl.normalSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkWaveCollision()
	{
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.wavePortals.length; i++)
		{
			if (new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox()).intersects
					(new Rectangle(lvl.wavePortals[i][0], lvl.wavePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkCubeCollision()
	{
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.cubePortals.length; i++)
		{
			if (new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox()).intersects
					(new Rectangle(lvl.cubePortals[i][0], lvl.cubePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkPlatformCollision()
	{
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.platforms.length; i++)
		{
			if (new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox()).intersectsLine
					(new Line2D.Double(lvl.platforms[i][0], lvl.platforms[i][1], lvl.platforms[i][2], lvl.platforms[i][1])))
			{
				Player.platformY = lvl.platforms[i][1];
				return true;
			}
		}
		
		return false;
	}
	
	private boolean intersectsCircle(Rectangle r, int circleX, int circleY, int diameter)
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
