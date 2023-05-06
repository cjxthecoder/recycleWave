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

/**
 * The RunningPlayer class is an instance of the Player when the game is
 * running. A crucial point is that this class must be runnable for the
 * game to run, because our game uses a threading system to allow input
 * from the player while the program runs behind the scenes. When running,
 * sleep() is used to delay the time (in milliseconds) for the program to
 * execute code, and this allows use to set the program running at a certain
 * refresh rate. In our game, this value is 5 (=200 updates per second). This
 * can also be used to set up a large delay to give the player time to take in
 * at what has just happened. For example, when the game is starting or when
 * the player has just died, etc.
 * 
 * @author Daniel Cheng
 *
 * @since 1.0
 */

public class RunningPlayer extends Player
	implements Runnable
{
	public RunningPlayer(int x, int y, byte gamemode, int gravity, double speed, boolean mini) {
		super(x, y, gamemode, gravity, speed, mini);
	}
	
	GameSound gs = new GameSound();
	
	@Override
	public void run()
	{	
		gs.loadMusic();
		double s = speed;
		
		switch (String.valueOf(GameWindow.getComboBox().getSelectedItem()))
		{
			case "Easy":
				s = GameConstants.HALF_TIMES;
				fullScore *= GameConstants.HALF_TIMES;
				break;
			case "Medium":
				s = GameConstants.ONE_TIMES;
				fullScore *= GameConstants.ONE_TIMES;
				break;
			case "Hard":
				s = GameConstants.TWO_TIMES;
				fullScore *= GameConstants.TWO_TIMES;
				break;
			case "Insane":
				s = GameConstants.THREE_TIMES;
				fullScore *= GameConstants.THREE_TIMES;
				break;
			case "Impossible":
				s = GameConstants.FOUR_TIMES;
				fullScore *= GameConstants.FOUR_TIMES;
				break;
		}
		
		try {
			Thread.sleep(500);
			while (true)
			{
				LevelEditor lvl = new LevelEditor();
				
				if ((player.x < GameConstants.START_LINE || LevelEditor.dx < GameConstants.START_LINE - GameConstants.FINISH_LINE) && !gameWon)
				{
					setXDirection(4.0 * speed);
					makePlayerReach300();
					Thread.sleep(5);
				}
				
				if (Collision.checkDeathCollision(lvl.slopes, lvl.sawblades))
				{
					gs.stopMusic();
					fullScore /= GameConstants.MAGIC;
					attempts++;
					Thread.sleep(1000);
					resetPlayerFields();
					LevelEditor.setDx(2 * GameConstants.START_LINE);
				}
				
				else {
					gs.startMusic();
					
					if (Collision.checkPortalCollision(lvl.speedPortals))
					{
						speed = s;
					}
					
					if (Collision.checkPortalCollision(lvl.normalGravityPortals))
					{
						if (gravity == GameConstants.UP)
						{
							t1 = 0;
							t2 = 0;
							gravity = GameConstants.DOWN;
						}
					}
					
					if (Collision.checkPortalCollision(lvl.flippedGravityPortals))
					{
						if (gravity == GameConstants.DOWN)
						{
							t1 = 0;
							t2 = 0;
							gravity = GameConstants.UP;
						}
					}
					
					if (Collision.checkPortalCollision(lvl.miniSizePortals)) // divide by 4 if player is wave, else divide by 2
					{
						mini = true;
						
						if (gamemode == GameConstants.WAVE) {
							setPlayerSize(0.25);
						}
						else {
							setPlayerSize(0.5);
						}
					}
					
					if (Collision.checkPortalCollision(lvl.normalSizePortals)) // divide by 2 if player is wave, else divide by 1
					{
						mini = false;
						
						if (gamemode == GameConstants.WAVE) {
							setPlayerSize(0.5);
						}
						else {
							setPlayerSize(1.0);
						}
					}
					
					if (Collision.checkPortalCollision(lvl.wavePortals)) // divide by 4 if player is mini, else divide by 2
					{
						gamemode = GameConstants.WAVE;
						
						if (mini) {
							setPlayerSize(0.25);
						} else {
							setPlayerSize(0.5);
						}
					}
					
					if (Collision.checkPortalCollision(lvl.cubePortals)) // divide by 2 if player is mini, else divide by 1
					{
						gamemode = GameConstants.CUBE;
						
						if (mini) {
							setPlayerSize(0.5);
						} else {
							setPlayerSize(1);
						}
					}
					
					if (player.x < 1452) {
						move();
						fall();
					}
					
					if (player.x >= 1452) {
						player.x = 1452;
						gameWon = true;
					}
					
					if (player.x >= GameConstants.START_LINE && LevelEditor.dx >= GameConstants.START_LINE - GameConstants.FINISH_LINE) {
						LevelEditor.goForward((int)(4.0 * speed));
					}
					
					else {
						LevelEditor.goForward(0);
					}
					Thread.sleep(5);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
