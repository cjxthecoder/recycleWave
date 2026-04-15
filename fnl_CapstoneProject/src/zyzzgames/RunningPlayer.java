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

public class RunningPlayer extends Player implements Runnable {
	private String difficulty;
	private LevelEditor lvl;
	private long start;
	private long accumulator;
	private boolean firstAttempt = true;
	private boolean musicPlaying = false;
	private static final int FPS = 200;

	public RunningPlayer(int x, int y, int gamemode, int gravity, float speed, boolean mini, String difficulty,
			LevelEditor lvl) {
		super(x, y, gamemode, gravity, speed, mini);
		this.difficulty = difficulty;
		this.lvl = lvl;
	}

	@Override
	public void run() {
		GameSound gs = new GameSound("48000/574484_F-777---Sonic-Blaster_48000.wav");
		float s = GameConstants.DIFF_VAL.get(difficulty);
		setFullScore(getFullScore() * s);

		try {
			start = System.nanoTime();
			accumulator = 0;
			long nano_per_frame = (long) 1e+9 / FPS;

			while (true) {
				long now = System.nanoTime();
				long delta = now - start;
				start = now;
				accumulator += delta;

				while (accumulator >= nano_per_frame) {
					if (!gameIsWon()) {
						update(gs, s);
					}
					accumulator -= nano_per_frame;
				}

				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void update(GameSound gs, float s) throws InterruptedException {
		if (firstAttempt) {
			Thread.sleep(500);
			start = System.nanoTime();
			accumulator = 0;
		}

		if (!musicPlaying) {
			if (firstAttempt) {
				gs.startMusic(38.4F);
			} else {
				gs.startMusic(38.46F);
			}
			musicPlaying = true;
			firstAttempt = false;
		}

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

		if (Collision.checkDeathCollision(lvl.getSlopes(), lvl.getSawblades(), this)) {
			gs.stopMusic();
			setFullScore(getFullScore() / GameConstants.MAGIC);
			setAttempts(getAttempts() + 1);
			Thread.sleep(1000);
			start = System.nanoTime();
			accumulator = 0;
			resetPlayerFields();
			lvl.goForward(lvl.getDx() - 2 * GameConstants.START_LINE);
			lvl.resetWaveTrail();
			musicPlaying = false;
		}

		if (Collision.checkSpeedCollision(lvl.getPortals("SPP"), this)) {
			setSpeed(s);
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

			// divide by 2 if player is wave, // else divide by 1
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

			// divide by 4 if player is wave, // else divide by 2
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

			// divide by 4 if player is mini, else divide by 2
			if (playerIsMini()) {
				setPlayerSize(0.25F);
			} else {
				setPlayerSize(0.5F);
			}
		}

		if (Collision.checkPortalCollision(lvl.getPortals("CBP"), this)) {
			setGamemode(GameConstants.CUBE);

			// divide by 2 if player is mini, else divide by 1
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

	public void setDifficulty(String newDifficulty) {
		difficulty = newDifficulty;
	}
}
