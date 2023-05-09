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

public class Player
{
	protected static int platformY = 0;
	protected int t1;
	protected int t2;
	private static int hitbox;
	private int gamemode;
	private int gravity;
	private int attempts = 1;
	private double speed;
	private double fullScore = 100.0;
	private boolean mini;
	private boolean gameWon;
	private int xDirection, yDirection;
	private boolean keyPressed = false;
	
	public static Rectangle player;
		
	public Player(int x, int y, int gamemode, int gravity, double speed, boolean mini)
	{
		this.gamemode = gamemode;
		this.gravity = gravity;
		this.setSpeed(speed);
		this.setMini(mini);
		
		hitbox = GameConstants.PLAYER_HITBOX;
		
		if (gamemode == GameConstants.WAVE) {
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
	 	
	 	if (player.y <= GameConstants.CEILING) {
	 		player.y = GameConstants.CEILING;
	 	}
	 	
	 	if (player.y >= GameConstants.GROUND - hitbox) {
	 		player.y = GameConstants.GROUND - hitbox;
	 	}
	 	
	 	if (Collision.checkPlatformCollision())
	 	{
	 		switch(gravity)
	 		{
	 			case GameConstants.UP:
	 				player.y = platformY + 1;
	 				setFallingSpeed(0);
	 				break;
	 				
	 			case GameConstants.DOWN:
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
		if ((gravity == GameConstants.UP && player.y <= GameConstants.GROUND - hitbox) || (gravity == GameConstants.DOWN && player.y >= GameConstants.CEILING))
		{
			switch(gamemode)
			{
				case GameConstants.CUBE:
					t1++;
					setFallingSpeed(Math.min(t1 / 4.0, 8.0) * getSpeed() * gravity);
					break;
			
				case GameConstants.SHIP:
					break;
				
				case GameConstants.BALL:
					break;
				
				case GameConstants.WAVE:
					if (!keyPressed)
					{
						if (isMini()) {
							setFallingSpeed(8.0 * getSpeed() * gravity);
						}
						
						else {
							setFallingSpeed(4.0 * getSpeed() * gravity);
						}
					}
					break;
			}
		}
	}

	protected void keyPressed(KeyEvent e)
	{
		switch(gamemode)
		{
			case GameConstants.CUBE:
				break;
				
			case GameConstants.SHIP:
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
					t1++;
					setYDirection(-Math.min(t1, 4.0) * getSpeed() * gravity);
					keyPressed = true;
				}
				break;
				
			case GameConstants.BALL:
				break;
				
			case GameConstants.WAVE:
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (isMini()) {
						setYDirection(-8.0 * getSpeed() * gravity);
					}
					
					else {
						setYDirection(-4.0 * getSpeed() * gravity);
					}
					keyPressed = true;
				}
				break;
		}
	}
	
	protected void keyReleased(KeyEvent e)
	{
		switch(gamemode)
		{
			case GameConstants.CUBE:
				break;
				
			case GameConstants.SHIP:
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
					t1 = 0;
					t2 = 0;
					setYDirection(0);
					keyPressed = false;
				}
				break;
			
			case GameConstants.BALL:
				break;
				
			case GameConstants.WAVE:
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
					setYDirection(0);
					keyPressed = false;
				}
				break;
		}
	}
	
	protected void drawPlayer(Graphics g)
	{
		g.setColor(Color.BLACK);
//		g.drawRect(player.x, player.y, player.width, player.height);
		
		switch(gamemode)
		{
			case GameConstants.CUBE:
				if (gravity == GameConstants.DOWN) {
					Image playerCubeUp = (new ImageIcon("playerCubeUp.png")).getImage();
					g.drawImage(playerCubeUp, player.x, player.y, player.width, player.height, null);
				}
				
				else {
					Image playerCubeDown = (new ImageIcon("playerCubeDown.png")).getImage();
					g.drawImage(playerCubeDown, player.x, player.y, player.width, player.height, null);
				}
				break;
				
			case GameConstants.WAVE:
				if ((gravity == GameConstants.DOWN && !keyPressed) || (gravity == GameConstants.UP && keyPressed)) {
					Image playerWaveDown = (new ImageIcon("playerWaveDown.png")).getImage();
					g.drawImage(playerWaveDown, player.x - player.width / 2, player.y - player.height / 2, player.width * 2, player.height * 2, null);
				}
				
				else {
					Image playerWaveUp = (new ImageIcon("playerWaveUp.png")).getImage();
					g.drawImage(playerWaveUp, player.x - player.width / 2, player.y - player.height / 2, player.width * 2, player.height * 2, null);
				}
				break;
		}
		
		if (player.x >= 768) {
			Image recycle = (new ImageIcon("recycleBin.png")).getImage();
			g.drawImage(recycle, 1428, 660, null);
			drawCenteredText(g, "Level Complete!", 96, 1.8);
			drawCenteredText(g, "Attempts: " + getAttempts(), 72, 1.2);
			drawCenteredText(g, "Your score: " + Math.round(100.0 * getFullScore()) / 100.0, 72, 0.9);

		}
	}
	
	protected void drawCenteredText(Graphics g, String s, int pt, double yFactor) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, pt);
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (1536 - metrics.stringWidth(s)) / 2;
		int y = (int) Math.round((840 - metrics.getHeight()) / yFactor + metrics.getAscent());
		g.setFont(font);
		g.drawString(s, x, y/2);
	}
	
	protected void setFallingSpeed(double fallingSpeed) {
		yDirection = (int) Math.round(fallingSpeed);
	}
	
	protected void resetPlayerFields() {
		player.x = GameConstants.START_LINE;
		player.y = GameConstants.GROUND - hitbox;
		player.height = GameConstants.PLAYER_HITBOX;
		player.width = GameConstants.PLAYER_HITBOX;
		hitbox = GameConstants.PLAYER_HITBOX;
		gamemode = GameConstants.CUBE;
		gravity = GameConstants.UP;
		setSpeed(GameConstants.THREE_TIMES);
		setMini(false);
		keyPressed = false;
	}
	
	protected static int getX() {
		return player.x;
	}
	
	protected void setXDirection(double xDir) {
		xDirection = (int) Math.round(xDir);
	}
	
	protected static int getY() {
		return player.y;
	}
	
	protected void setYDirection(double yDir) {
		yDirection = (int) Math.round(yDir);
	}
	
	protected static int getHitbox() {
		return hitbox;
	}
	
	protected void setPlayerSize(double factor) {
		hitbox = (int) Math.round(GameConstants.PLAYER_HITBOX * factor);
		player.height = (int) Math.round(GameConstants.PLAYER_HITBOX * factor);
		player.width = (int) Math.round(GameConstants.PLAYER_HITBOX * factor);
	}
	
	protected int getGamemode() {
		return gamemode;
	}

	protected void setGamemode(int gamemode)
	{
		this.gamemode = gamemode;
	}

	protected int getGravity()
	{
		return gravity;
	}
	
	protected void setGravity(int gravity)
	{
		t1 = 0;
		t2 = 0;
		this.gravity = gravity;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isMini() {
		return mini;
	}
	
	public void setMini(boolean mini)
	{
		this.mini = mini;
	}
	
	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}
	
	public double getFullScore() {
		return fullScore;
	}

	public void setFullScore(double fullScore) {
		this.fullScore = fullScore;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
}
