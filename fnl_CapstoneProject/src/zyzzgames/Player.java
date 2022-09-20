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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 * The player class contains all the information for the player. This includes
 * the player's position in x- and y-coordinates, hitbox size, gamemode, gravity,
 * speed, the x- and y- velocities on screen, if it's mini or falling, the number
 * of attempts the player has spent on a level, the player's score out of 100,
 * whether or not the player has won the game, and if the player is pressing the
 * keyboard to control the player.
 * 
 * @author Daniel Cheng
 *
 * @since 1.0
 */

public class Player implements GameConstants
{
	public int t1, t2;
	public static int platformY = 0;
	protected static int hitbox;
	protected int gamemode;
	protected int gravity;
	protected double speed;
	protected boolean mini, falling, gameWon;
	protected double fullScore = 100.0;
	protected int attempts = 1;
	private int xDirection;
	private int yDirection;
	private boolean keyPressed = false;
	
	static Rectangle player;
	
	Collision c = new Collision();
	
	public Player(int x, int y, int gamemode, int gravity, double speed, boolean mini)
	{
		this.gamemode = gamemode;
		this.gravity = gravity;
		this.speed = speed;
		this.mini = mini;
		
		hitbox = PLAYER_HITBOX;
		
		if (gamemode == WAVE) {
			hitbox /= 2;
		}
		
		if (mini) {
			hitbox /= 2;
		}
		
		player = new Rectangle(x, y, hitbox, hitbox);
	}
	
	protected void makePlayerReach300()
	{
		player.x += xDirection;
	}

	protected void move()
	{
	 	player.y += yDirection;
	 	
	 	if (player.y <= CEILING) {
	 		player.y = CEILING;
	 	}
	 	
	 	if (player.y >= GROUND - hitbox) {
	 		player.y = GROUND - hitbox;
	 	}
	 	
	 	if (c.checkPlatformCollision())
	 	{
	 		switch(gravity)
	 		{
	 			case UP:
	 				player.y = platformY + 1;
	 				setFallingSpeed(0);
	 				break;
	 				
	 			case DOWN:
	 				player.y = platformY - hitbox;
	 				setFallingSpeed(0);
	 				break;
	 		}
	 		
	 		t1 = 0;
	 		t2 = 0;
	 	}
	}
	
	protected void fall()
	{
		if ((gravity == UP && player.y <= GROUND - hitbox) || (gravity == DOWN && player.y >= CEILING))
		{
			switch(gamemode)
			{
				case CUBE:
					t1++;
					setFallingSpeed(Math.min(t1 / 4.0, 8.0) * speed * gravity );
					break;
			
				case SHIP:
					break;
				
				case BALL:
					break;
				
				case WAVE:
					if (!keyPressed)
					{
						if (mini == true) {
							setFallingSpeed(8.0 * speed * gravity);
						}
						
						else {
							setFallingSpeed(4.0 * speed * gravity);
						}
					}
					break;
			}
		}
	}

	public void keyPressed(KeyEvent e)
	{
		switch(gamemode)
		{
			case CUBE:
				break;
				
			case SHIP:
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
					t1++;
					setYDirection(-Math.min(t1, 4.0) * speed * gravity);
					keyPressed = true;
				}
				break;
				
			case BALL:
				break;
				
			case WAVE:
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
					falling = false;
					if (getSize() == true) {
						setYDirection(-8.0 * speed * gravity);
					}
					
					else {
						setYDirection(-4.0 * speed * gravity);
					}
					keyPressed = true;
				}
				break;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		switch(gamemode)
		{
			case CUBE:
				break;
				
			case SHIP:
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
					t1 = 0;
					t2 = 0;
					setYDirection(0);
					keyPressed = false;
				}
				break;
			
			case BALL:
				break;
				
			case WAVE:
				falling = true;
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
					setYDirection(0);
					keyPressed = false;
				}
				break;
		}
	}
	
	public void drawPlayer(Graphics g)
	{
		g.setColor(Color.BLACK);
//		g.drawRect(player.x, player.y, player.width, player.height);
		
		switch(gamemode)
		{
			case CUBE:
				Image playerIcon = (new ImageIcon("playerCube.png")).getImage();
				g.drawImage(playerIcon, player.x, player.y, player.width, player.height, null);
				break;
				
			case WAVE:
				if ((gravity == DOWN && falling == true) || (gravity == UP && falling == false)) {
					Image wave = (new ImageIcon("playerWaveDown.png")).getImage();
					g.drawImage(wave, player.x - player.width / 2, player.y - player.height / 2, player.width * 2, player.height * 2, null);
				}
				else {
					Image wave = (new ImageIcon("playerWaveUp.png")).getImage();
					g.drawImage(wave, player.x - player.width / 2, player.y - player.height / 2, player.width * 2, player.height * 2, null);
				}
				break;
		}
		
		if (player.x >= 768) {
			Image recycle = (new ImageIcon("recycleBin.png")).getImage();
			g.drawImage(recycle, 1428, 660, null);
			drawCenteredText(g, "Level Complete!", 96, 1.8);
			drawCenteredText(g, "Attempts: " + attempts, 72, 1.2);
			drawCenteredText(g, "Your score: " + Math.round(100.0 * fullScore) / 100.0, 72, 0.9);

		}
	}
	
	public void drawCenteredText(Graphics g, String s, int pt, double yFactor) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, pt);
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (1536 - metrics.stringWidth(s)) / 2;
		int y = (int)((840 - metrics.getHeight()) / yFactor + metrics.getAscent());
		g.setFont(font);
		g.drawString(s, x, y/2);
	}
	
	public static int getX() {
		return player.x;
	}
	
	public static int getY() {
		return player.y;
	}
	
	public static int getHitbox() {
		return hitbox;
	}
	
	public int getGamemode() {
		return gamemode;
	}
	
	public int getGravity()
	{
		return gravity;
	}

	public boolean getSize()
	{
		return mini;
	}
	
	public void setGamemode(int gamemode)
	{
		this.gamemode = gamemode;
	}
	
	public void setGravity(int gravity)
	{
		t1 = 0;
		t2 = 0;
		this.gravity = gravity;
	}
	
	public void setMini(boolean mini)
	{
		this.mini = mini;
	}
	
	public void setXDirection(double xDir) {
		xDirection = (int)xDir;
	}
	
	public void setYDirection(double yDir) {
		yDirection = (int)yDir;
	}
	
	public void setFallingSpeed(double fallingSpeed) {
		yDirection = (int)fallingSpeed;
	}
	
	public void setPlayerSize(double factor) {
		hitbox = (int)(PLAYER_HITBOX * factor);
		player.height = (int)(PLAYER_HITBOX * factor);
		player.width = (int)(PLAYER_HITBOX * factor);
	}
	
	public void resetPlayerFields() {
		player.x = START_LINE;
		player.y = GROUND - hitbox;
		player.height = PLAYER_HITBOX;
		player.width = PLAYER_HITBOX;
		hitbox = PLAYER_HITBOX;
		gamemode = CUBE;
		gravity = UP;
		speed = threeTimes;
		mini = false;
		keyPressed = false;
	}
}
