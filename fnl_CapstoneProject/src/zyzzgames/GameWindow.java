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
 * Current Version: 1.7
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

/**
 * The GameWindow class is the class responsible for drawing graphics on the
 * screen. It also has a starting menu that uses JComboBox so that the user can
 * choose the game's difficulty. To play the game, there is a button that can be
 * clicked by the user to start the game. An InputListner is instantiated so
 * that the game can take keyboard input from the user. An Image and Graphics
 * class, as well as the paint() function, is used to draw graphics on the
 * screen.
 * 
 * @author Daniel Cheng
 *
 * @since 1.0
 */

public class GameWindow extends JFrame implements ActionListener {
	/**
	 * 2026-04-09T16:03:19
	 */
	private static final long serialVersionUID = 1L;
	private Image gdImage;
	private Graphics gdGraphics;

	private JButton play;
	private JComboBox<String> comboBox;
	private String[] difficulty = { "Easy", "Medium", "Hard", "Insane", "Impossible" };
	private LevelEditor lvl = new LevelEditor(false, true);
	private RunningPlayer runP = new RunningPlayer(-GameConstants.PLAYER_HITBOX,
			GameConstants.GROUND - GameConstants.PLAYER_HITBOX, GameConstants.CUBE, GameConstants.UP,
			GameConstants.THREE_TIMES, false, "Insane", lvl);
	private boolean gameStarted = false;

	public GameWindow(int x, int y, int width, int height) {
		super("Recycle Wave");

		this.setBounds(x, y, width, height);
		this.setLayout(new FlowLayout());
		this.addKeyListener(new InputListener(runP));

		play = new JButton("Play");
		play.setPreferredSize(new Dimension(120, 40));
		play.addActionListener(this);
		play.setFocusable(false);

		comboBox = new JComboBox<>(difficulty);
		comboBox.setFocusable(false);

		Container c = getContentPane();
		c.add(play);
		c.add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!gameStarted) {
			gameStarted = true;
			runP.setDifficulty(String.valueOf(comboBox.getSelectedItem()));
			repaint();
			Thread p1 = new Thread(runP);
			p1.start();
		}
	}

	@Override
	public void paint(Graphics g) {
		if (gameStarted) {
			gdImage = createImage(getWidth(), getHeight());
			gdGraphics = gdImage.getGraphics();
			draw(gdGraphics);
			g.drawImage(gdImage, 0, 0, this);
		}

		else {
			drawGameTitle(g);
		}
	}

	private void drawGameTitle(Graphics g) {
		drawCenteredText(g, "Recycle Wave", 96, 1.5);

		if (System.getProperty("os.name").contains("Mac")) {
			g.drawRect(643, 33, 120, 40);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
			g.drawString("Drag your cursor here", 650, 56);
			g.drawRect(761, 37, 120, 99);
			g.drawRect(767, 39, 126, 27);
			g.drawLine(860, 39, 860, 66);
			g.drawString("Over there>", 772, 56);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
			g.drawString("As well!", 778, 90);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
			g.drawString("Don't forget to", 778, 110);
			g.drawString("try clicking :)", 778, 130);
		}

		else {
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
		g.setColor(Color.CYAN);
		g.drawLine(0, GameConstants.GROUND, 1550, GameConstants.GROUND);
		g.drawLine(0, GameConstants.CEILING, 1550, GameConstants.CEILING);

		lvl.drawBlocks(g, Color.BLUE, GameConstants.BLK);
		lvl.drawNormalGravityPortals(g, Color.GREEN, GameConstants.NGP);
		lvl.drawFlippedGravityPortals(g, Color.GREEN, GameConstants.FGP);
		lvl.drawNormalSizePortals(g, Color.GREEN, GameConstants.NSP);
		lvl.drawMiniSizePortals(g, Color.GREEN, GameConstants.MSP);
		lvl.drawWavePortals(g, Color.GREEN, GameConstants.WVP);
		lvl.drawCubePortals(g, Color.GREEN, GameConstants.CBP);
		lvl.drawSlopes(g, new Color(240, 16, 160), Color.RED, GameConstants.GS, GameConstants.CS);
		lvl.drawSawblades(g, Color.RED, GameConstants.SB);
		lvl.drawSpeedPortals(g, Color.GREEN, String.valueOf(comboBox.getSelectedItem()));
		lvl.drawWaveTrail(g, new Color(35, 182, 228));
		lvl.drawProgressBar(g, GameConstants.FINISH_LINE, Color.BLACK, Color.CYAN);
	}

	private void draw(Graphics g) {
		drawGameGraphics((Graphics2D) g);
		runP.drawPlayer(g);
		repaint();
	}

	public static void drawCenteredText(Graphics g, String s, int pt, double yFactor) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, pt);
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (1536 - metrics.stringWidth(s)) / 2;
		int y = (int) Math.round((840 - metrics.getHeight()) / yFactor + metrics.getAscent());
		g.setFont(font);
		g.drawString(s, x, y / 2);
	}
}
