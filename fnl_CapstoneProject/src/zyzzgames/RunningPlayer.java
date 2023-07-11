/*
 * Copyright (c) 2022, Team Zyzz and/or its affiliates, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Please contact danielcheng2200@gmail.com if you need additional information
 * or have any questions.
 * 
 * Current Version: 1.3
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
	public RunningPlayer(int x, int y, int gamemode, int gravity, double speed, boolean mini) {
		super(x, y, gamemode, gravity, speed, mini);
	}
	
	GameSound gs = new GameSound();
	
	@Override
	public void run()
	{	
		gs.loadMusic();
		double s = getSpeed();
		
		switch (String.valueOf(GameWindow.getComboBox().getSelectedItem()))
		{
			case "Easy":
				s = GameConstants.HALF_TIMES;
				setFullScore(getFullScore() * GameConstants.HALF_TIMES);
				break;
			case "Medium":
				s = GameConstants.ONE_TIMES;
				setFullScore(getFullScore() * GameConstants.ONE_TIMES);
				break;
			case "Hard":
				s = GameConstants.TWO_TIMES;
				setFullScore(getFullScore() * GameConstants.TWO_TIMES);
				break;
			case "Insane":
				s = GameConstants.THREE_TIMES;
				setFullScore(getFullScore() * GameConstants.THREE_TIMES);
				break;
			case "Impossible":
				s = GameConstants.FOUR_TIMES;
				setFullScore(getFullScore() * GameConstants.FOUR_TIMES);
				break;
		}
		
		try {
			Thread.sleep(500);
			while (true)
			{
				LevelEditor lvl = new LevelEditor();
				
				if ((getX() < GameConstants.START_LINE || LevelEditor.dx < GameConstants.START_LINE - GameConstants.FINISH_LINE) && !gameWon())
				{
					setXDirection(4.0 * getSpeed());
					makePlayerReach300();
					Thread.sleep(5);
				}
				
				if (Collision.checkDeathCollision(lvl.slopes, lvl.sawblades, this))
				{
					gs.stopMusic();
					setFullScore(getFullScore() / GameConstants.MAGIC);
					setAttempts(getAttempts() + 1);
					Thread.sleep(1000);
					resetPlayerFields();
					LevelEditor.setDx(2 * GameConstants.START_LINE);
				}
				
				else {
					gs.startMusic();
					
					if (Collision.checkPortalCollision(lvl.speedPortals, this))
					{
						setSpeed(s);
					}
					
					if (Collision.checkPortalCollision(lvl.normalGravityPortals, this))
					{
						if (getGravity() == GameConstants.UP)
						{
							resetTime();
							setGravity(GameConstants.DOWN);
						}
					}
					
					if (Collision.checkPortalCollision(lvl.flippedGravityPortals, this))
					{
						if (getGravity() == GameConstants.DOWN)
						{
							resetTime();
							setGravity(GameConstants.UP);
						}
					}
					
					if (Collision.checkPortalCollision(lvl.miniSizePortals, this)) // divide by 4 if player is wave, else divide by 2
					{
						setMini(true);
						
						if (getGamemode() == GameConstants.WAVE) {
							setPlayerSize(0.25);
						}
						else {
							setPlayerSize(0.5);
						}
					}
					
					if (Collision.checkPortalCollision(lvl.normalSizePortals, this)) // divide by 2 if player is wave, else divide by 1
					{
						setMini(false);
						
						if (getGamemode() == GameConstants.WAVE) {
							setPlayerSize(0.5);
						}
						else {
							setPlayerSize(1.0);
						}
					}
					
					if (Collision.checkPortalCollision(lvl.wavePortals, this)) // divide by 4 if player is mini, else divide by 2
					{
						setGamemode(GameConstants.WAVE);
						
						if (isMini()) {
							setPlayerSize(0.25);
						} else {
							setPlayerSize(0.5);
						}
					}
					
					if (Collision.checkPortalCollision(lvl.cubePortals, this)) // divide by 2 if player is mini, else divide by 1
					{
						setGamemode(GameConstants.CUBE);
						
						if (isMini()) {
							setPlayerSize(0.5);
						} else {
							setPlayerSize(1);
						}
					}
					
					if (getX() < 1452) {
						move();
						fall();
					}
					
					if (getX() >= 1452) {
						setX(1452);
						setGameWon(true);
					}
					
					if (getX() >= GameConstants.START_LINE && LevelEditor.dx >= GameConstants.START_LINE - GameConstants.FINISH_LINE) {
						LevelEditor.goForward((int)(4.0 * getSpeed()));
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
