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
 * Current Version: 1.9
 */

package zyzzgames;

/**
 * The RunningPlayer class is an instance of the Player when the game is
 * running. A crucial point is that this class must be runnable for the game to
 * run, because our game uses a threading system to allow input from the player
 * while the program runs behind the scenes. When running, sleep() is used to
 * delay the time (in milliseconds) for the program to execute code, and this
 * allows use to set the program running at a certain refresh rate. In our game,
 * this value is 5 (=200 updates per second). This can also be used to set up a
 * large delay to give the player time to take in at what has just happened. For
 * example, when the game is starting or when the player has just died, etc.
 * 
 * @author Daniel Cheng
 *
 * @since 1.0
 */

public class RunningPlayer extends Player {
	private LevelEditor lvl;
	private boolean firstAttempt;
	private boolean musicStart;

	public RunningPlayer(int x, int y, int gamemode, int gravity, float speed, boolean mini, LevelEditor lvl) {
		super(x, y, gamemode, gravity, speed, mini);
		this.lvl = lvl;
		this.firstAttempt = true;
		this.musicStart = true;
	}

	public void update(float speed) {
		boolean before_start = getX() < GameConstants.START_LINE;
		boolean past_finish = lvl.getDx() <= GameConstants.START_LINE - GameConstants.FINISH_LINE;

		// If player is before the start line or player is past the finish line
		if (before_start || past_finish) {
			if (past_finish) {
				lvl.resetWaveTrail();
			}
			setXDirection(4.0F * getSpeed());
			movePlayerX();
		}

		if (Collision.checkSpeedCollision(lvl.getPortals("SPP"), this)) {
			setSpeed(speed);
		}

		if (Collision.checkPortalCollision(lvl.getPortals("NGP"), this)) {
			if (getGravity() == GameConstants.UP) {
				resetTime();
				setGravity(GameConstants.DOWN);
			}
		}

		if (Collision.checkPortalCollision(lvl.getPortals("FGP"), this)) {
			if (getGravity() == GameConstants.DOWN) {
				resetTime();
				setGravity(GameConstants.UP);
			}
		}

		if (Collision.checkPortalCollision(lvl.getPortals("NSP"), this)) {
			setMini(false);

			// Divide by 2 if player is wave, else divide by 1
			if (getGamemode() == GameConstants.WAVE) {
				setPlayerSize(0.5F);
			} else {
				setPlayerSize(1.0F);
			}

			if (keyIsPressed() && getGamemode() == GameConstants.WAVE) {
				setYDirection(-4.0F * getSpeed() * this.getGravity());
			}
		}

		if (Collision.checkPortalCollision(lvl.getPortals("MSP"), this)) {
			setMini(true);

			// Divide by 4 if player is wave, else divide by 2
			if (getGamemode() == GameConstants.WAVE) {
				setPlayerSize(0.25F);
			} else {
				setPlayerSize(0.5F);
			}

			if (keyIsPressed() && getGamemode() == GameConstants.WAVE) {
				setYDirection(-8.0F * getSpeed() * this.getGravity());
			}
		}

		if (Collision.checkPortalCollision(lvl.getPortals("WVP"), this)) {
			setGamemode(GameConstants.WAVE);

			// Divide by 4 if player is mini, else divide by 2
			if (playerIsMini()) {
				setPlayerSize(0.25F);
			} else {
				setPlayerSize(0.5F);
			}
		}

		if (Collision.checkPortalCollision(lvl.getPortals("CBP"), this)) {
			setGamemode(GameConstants.CUBE);

			// Divide by 2 if player is mini, else divide by 1
			if (playerIsMini()) {
				setPlayerSize(0.5F);
			} else {
				setPlayerSize(1);
			}
		}

		if (Collision.checkPlatformCollision(lvl.getBlocks(), this)) {
			if (getGravity() == GameConstants.DOWN) {
				setY(getPlatformY() - getHitbox());
				setYDirection(0);
			} else {
				setY(getPlatformY() + 1);
				setYDirection(0);
			}

			resetTime();
		}

		if (getGamemode() == GameConstants.WAVE) {
			lvl.addWaveTrail(false, this);
		}

		if (getX() < 1460) {
			fall();
			movePlayerY();
		} else {
			setX(1460);
			setGameWon(true);
		}

		// If player is at or after the start line and before the finish line
		if (!before_start && !past_finish) {
			lvl.goForward((int) (4.0 * getSpeed()));
		}
	}

	public boolean isFirstAttempt() {
		return firstAttempt;
	}

	public void setFirstAttempt(boolean first) {
		firstAttempt = first;
	}

	public boolean isMusicStart() {
		return musicStart;
	}

	public void setMusicStart(boolean start) {
		musicStart = start;
	}

	public boolean gameIsOver() {
		return Collision.checkDeathCollision(lvl.getSlopes(), lvl.getSawblades(), this);
	}

	public void stopTrack(GameSound gs) {
		gs.stopMusic();
		setFullScore(getFullScore() / GameConstants.MAGIC);
		setAttempts(getAttempts() + 1);
	}

	public void resetFields() {
		resetPlayerFields();
		lvl.goForward(lvl.getDx() - 2 * GameConstants.START_LINE);
		lvl.resetWaveTrail();
	}
}
