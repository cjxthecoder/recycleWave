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

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * The GameWindow class is the class responsible for drawing graphics on the
 * screen. It also has a starting menu that uses JComboBox so that the user can
 * choose the game's difficulty. To play the game, there is a button that can be
 * clicked by the user to start the game. An InputListner is instantiated so
 * that the game can take keyboard input from the user. An Image and Graphics
 * class, as well as the paint() function, is used to draw graphics on the
 * screen.
 * 
 * @author Brandon Tsao
 *
 * @since 1.0
 */

public class GameWindow extends JFrame implements ActionListener {
	/**
	 * 2026-04-09T16:03:19
	 */
	private static final long serialVersionUID = 1L;
	private static final long START_PAUSE_TIME = (long) 1e+9 / 2;
	private static final long FAIL_PAUSE_TIME = (long) 1e+9;
	private static final int FPS = 200;

	private Image gdImage;
	private Graphics gdGraphics;
	private JButton play;
	private JComboBox<String> comboBox;
	private String[] difficulties = { "Easy", "Medium", "Hard", "Insane", "Extreme", "Impossible" };
	private LevelEditor lvl = new LevelEditor(false, true);
	private RunningPlayer runP = new RunningPlayer(-GameConstants.PLAYER_HITBOX,
			GameConstants.GROUND - GameConstants.PLAYER_HITBOX, GameConstants.CUBE, GameConstants.UP,
			GameConstants.THREE_TIMES, false, lvl);

	private long start;
	private long accumulator;
	private boolean gameStarted = false;
	private boolean runRest = false;
	private boolean trackStopped = false;

	public GameWindow(int x, int y, int width, int height) {
		super("Recycle Wave");

		this.setBounds(x, y, width, height);
		this.setLayout(new FlowLayout());
		this.addKeyListener(new InputListener(runP));

		play = new JButton("Play");
		play.setPreferredSize(new Dimension(120, 40));
		play.addActionListener(this);
		play.setFocusable(false);

		comboBox = new JComboBox<>(difficulties);
		comboBox.setFocusable(false);

		Container c = getContentPane();
		c.add(play);
		c.add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!gameStarted) {
			gameStarted = true;
			play.setEnabled(false);
			comboBox.setEnabled(false);
			repaint();

			GameSound gs = new GameSound("48000/574484_F-777---Sonic-Blaster_48000.wav");
			float speed = GameConstants.DIFF_VAL.get(String.valueOf(comboBox.getSelectedItem()));
			runP.setFullScore(runP.getFullScore() * speed);
			start = System.nanoTime();
			accumulator = 0;

			Timer timer = new Timer(1, e2 -> {
				runGameLoop(gs, speed, (long) 1e+9 / FPS);
			});
			
			timer.start();
		}
	}

	@Override
	public void paint(Graphics g) {
		if (gameStarted) {
			gdImage = createImage(getWidth(), getHeight());
			gdGraphics = gdImage.getGraphics();
			draw(gdGraphics);
			g.drawImage(gdImage, 0, 0, this);
		} else {
			drawGameTitle(g);
		}
	}

	private void runGameLoop(GameSound gs, float speed, long nano_per_frame) {
		long now = System.nanoTime();
		long delta = now - start;
		start = now;
		accumulator += delta;

		if (runP.isFirstAttempt()) {
			if (accumulator >= START_PAUSE_TIME) {
				runRest = true;
				start = System.nanoTime();
				accumulator = 0;
			}
		}

		if (runRest) {
			if (runP.isMusicStart()) {
				if (runP.isFirstAttempt()) {
					gs.startMusic(38.4F);
				} else {
					gs.startMusic(38.46F);
				}
				runP.setMusicStart(false);
				runP.setFirstAttempt(false);
			}

			while (accumulator >= nano_per_frame && !runP.gameIsOver()) {
				if (!runP.gameIsWon()) {
					runP.update(speed);
				}
				accumulator -= nano_per_frame;
			}

			if (runP.gameIsOver()) {
				if (!trackStopped) {
					runP.stopTrack(gs);
					trackStopped = true;
				}
				
				if (accumulator >= FAIL_PAUSE_TIME) {
					runP.resetFields();
					runP.setMusicStart(true);
					trackStopped = false;
					start = System.nanoTime();
					accumulator = 0;
				}
			}
		}
	}

	private void drawGameTitle(Graphics g) {
		drawCenteredText((Graphics2D) g, "Recycle Wave", 96, 1.5);

		if (System.getProperty("os.name").contains("Mac")) {
			g.drawRect(643, 33, 120, 40);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
			g.drawString("Drag your cursor here", 650, 56);
			g.drawRect(761, 37, 120, 117);
			g.drawRect(767, 39, 126, 27);
			g.drawLine(860, 39, 860, 66);
			g.drawString("Over there>", 772, 56);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
			g.drawString("As well!", 778, 90);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
			g.drawString("Don't forget to", 778, 110);
			g.drawString("try clicking :)", 778, 130);
		} else {
			g.drawRect(661, 35, 120, 40);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
			g.drawString("Drag your cursor here", 664, 58);
			g.drawRect(784, 41, 92, 119);
			g.drawLine(784, 67, 876, 67);
			g.drawLine(853, 41, 853, 67);
			g.drawString("Over there>", 788, 58);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
			g.drawString("As well!", 790, 90);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
			g.drawString("Don't forget to", 790, 110);
			g.drawString("try clicking :)", 790, 130);
		}
	}

	private void drawGameGraphics(Graphics2D g) {
		lvl.drawGround(g, Color.CYAN, this.getWidth());
		lvl.drawCeiling(g, Color.CYAN, this.getWidth());
		lvl.drawBlocks(g, Color.BLUE, GameConstants.BLK);
		lvl.drawNormalGravityPortals(g, Color.GREEN, GameConstants.NGP);
		lvl.drawFlippedGravityPortals(g, Color.GREEN, GameConstants.FGP);
		lvl.drawNormalSizePortals(g, Color.GREEN, GameConstants.NSP);
		lvl.drawMiniSizePortals(g, Color.GREEN, GameConstants.MSP);
		lvl.drawWavePortals(g, Color.GREEN, GameConstants.WVP);
		lvl.drawCubePortals(g, Color.GREEN, GameConstants.CBP);
		lvl.drawSlopes(g, GameConstants.CORAL_PINK, Color.RED, GameConstants.GS, GameConstants.CS);
		lvl.drawSawblades(g, Color.RED, GameConstants.SB);
		lvl.drawSpeedPortals(g, Color.GREEN, String.valueOf(comboBox.getSelectedItem()));
		lvl.drawWaveTrail(g, GameConstants.PLASTIC_BLUE);
		lvl.drawPlayer(g, Color.RED, runP);
		lvl.drawProgressBar(g, GameConstants.FINISH_LINE, Color.BLACK, GameConstants.PLASTIC_BLUE);
	}

	private void draw(Graphics g) {
		drawGameGraphics((Graphics2D) g);
		repaint();
	}

	public static void drawCenteredText(Graphics2D g2d, String s, int pt, double yFactor) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, pt);
		FontMetrics metrics = g2d.getFontMetrics(font);
		int x = (1536 - metrics.stringWidth(s)) / 2;
		int y = (int) Math.round((840 - metrics.getHeight()) / yFactor + metrics.getAscent());
		g2d.setFont(font);
		g2d.drawString(s, x, y / 2);
	}
}
