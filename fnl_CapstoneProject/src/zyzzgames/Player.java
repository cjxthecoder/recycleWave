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
 * Current Version: 1.6
 */

package zyzzgames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

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
	private Rectangle player;
	private int t1;
	private int hitbox;
	private int gamemode;
	private int gravity;
	private int platformY = 0;
	private int attempts = 1;
	private double speed;
	private double fullScore = 100.0;
	private boolean mini;
	private boolean gameWon;
	private int xDirection, yDirection;
	private boolean keyPressed = false;
	private List<Integer> hotKeys = List.of(KeyEvent.VK_UP, KeyEvent.VK_SPACE, KeyEvent.VK_ENTER);
		
	public Player(int x, int y, int gamemode, int gravity, double speed, boolean mini)
	{
		this.gamemode = gamemode;
		this.gravity = gravity;
		this.speed = speed;
		this.mini = mini;
		
		hitbox = GameConstants.PLAYER_HITBOX;
		
		if (gamemode == GameConstants.WAVE) {
			hitbox /= 2;
		}
		
		if (mini) {
			hitbox /= 2;
		}
		
		player = new Rectangle(x, y, hitbox, hitbox);
	}
	
	public void makePlayerReach300()
	{
		player.x += xDirection;
	}

	public void move()
	{
	 	player.y += yDirection;
	 	
	 	if (player.y <= GameConstants.CEILING) {
	 		player.y = GameConstants.CEILING;
	 	}
	 	
	 	if (player.y >= GameConstants.GROUND - hitbox) {
	 		player.y = GameConstants.GROUND - hitbox;
	 	}
	}
	
	public void fall()
	{
		if ((gravity == GameConstants.UP && player.y <= GameConstants.GROUND - hitbox) ||
				(gravity == GameConstants.DOWN && player.y >= GameConstants.CEILING))
		{
			switch(gamemode)
			{
				case GameConstants.CUBE:
					t1++;
					setYDirection(Math.min(t1 / 4.0, 8.0) * getSpeed() * gravity);
					break;
			
				case GameConstants.SHIP:
					break;
				
				case GameConstants.BALL:
					break;
				
				case GameConstants.WAVE:
					if (!keyPressed)
					{
						if (playerIsMini()) {
							setYDirection(8.0 * getSpeed() * gravity);
						}
						else {
							setYDirection(4.0 * getSpeed() * gravity);
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
			case GameConstants.CUBE:
				break;
				
			case GameConstants.SHIP:
				break;
				
			case GameConstants.BALL:
				break;
				
			case GameConstants.WAVE:
				if (hotKeys.contains(e.getKeyCode())) {
					if (playerIsMini()) {
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
	
	public void keyReleased(KeyEvent e)
	{
		switch(gamemode)
		{
			case GameConstants.CUBE:
				break;
				
			case GameConstants.SHIP:
				break;
			
			case GameConstants.BALL:
				break;
				
			case GameConstants.WAVE:
				if (hotKeys.contains(e.getKeyCode())) {
					setYDirection(0);
					keyPressed = false;
				}
				break;
		}
	}
	
	public void drawPlayer(Graphics g)
	{
		g.setColor(Color.BLACK);
		
		switch(gamemode)
		{
			case GameConstants.CUBE:
				if (gravity == GameConstants.DOWN) {
					g.drawImage(GameConstants.PCU, player.x, player.y, player.width, player.height, null);
				}
				
				else {
					g.drawImage(GameConstants.PCD, player.x, player.y, player.width, player.height, null);
				}
				break;
				
			case GameConstants.WAVE:
				if ((gravity == GameConstants.DOWN && !keyPressed) || (gravity == GameConstants.UP && keyPressed)) {
					g.drawImage(GameConstants.PWD, player.x - player.width / 2,
							player.y - player.height / 2, player.width * 2, player.height * 2, null);
				}
				
				else {
					g.drawImage(GameConstants.PWU, player.x - player.width / 2,player.y - player.height / 2, player.width * 2, player.height * 2, null);
				}
				break;
		}
		
		if (player.x >= 768) {
			g.drawImage(GameConstants.RCB, 1428, 660, null);
			GameWindow.drawCenteredText(g, "Level Complete!", 96, 1.8);
			GameWindow.drawCenteredText(g, "Attempts: " + getAttempts(), 72, 1.2);
			GameWindow.drawCenteredText(g, "Your score: " + Math.round(100.0 * getFullScore()) / 100.0, 72, 0.9);
		}
	}
	
	public void resetPlayerFields() {
		hitbox = GameConstants.PLAYER_HITBOX;
		player.x = GameConstants.START_LINE;
		player.y = GameConstants.GROUND - hitbox;
		player.height = hitbox;
		player.width = hitbox;
		gamemode = GameConstants.CUBE;
		gravity = GameConstants.UP;
		setSpeed(GameConstants.THREE_TIMES);
		setMini(false);
		keyPressed = false;
	}
	
	public void resetTime() {
		t1 = 0;
	}
	
	public int getX() {
		return player.x;
	}
	
	public void setX(int x) {
		player.x = x;
	}
	
	public void setXDirection(double xDir) {
		xDirection = (int) Math.round(xDir);
	}
	
	public int getY() {
		return player.y;
	}
	
	public void setY(int y) {
		player.y = y;
	}
	
	public void setYDirection(double yDir) {
		yDirection = (int) Math.round(yDir);
	}
	
	public int getHitbox() {
		return hitbox;
	}
	
	public void setPlayerSize(double factor) {
		hitbox = (int) Math.round(GameConstants.PLAYER_HITBOX * factor);
		player.height = hitbox;
		player.width = hitbox;
	}
	
	public int getGamemode() {
		return gamemode;
	}

	public void setGamemode(int gamemode) {
		this.gamemode = gamemode;
	}

	public int getGravity() {
		return gravity;
	}
	
	public void setGravity(int gravity) {
		resetTime();
		this.gravity = gravity;
	}
	
	public int getPlatformY() {
		return platformY;
	}
	
	public void setPlatformY(int platformY) {
		this.platformY = platformY;
	}
	
	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getFullScore() {
		return fullScore;
	}

	public void setFullScore(double fullScore) {
		this.fullScore = fullScore;
	}
	
	public boolean playerIsMini() {
		return mini;
	}
	
	public void setMini(boolean mini) {
		this.mini = mini;
	}
	
	public boolean gameIsWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}
	
	public boolean keyIsPressed() {
		return keyPressed;
	}
}
