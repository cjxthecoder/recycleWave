/*
 * Copyright (c) 2022, 2025, Team Zyzz and/or its affiliates, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Please contact danielcheng2200@gmail.com if you need additional information
 * or have any questions.
 * 
 * Current Version: 1.4
 */

package zyzzgames;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * The GameWindow class is the class responsible for drawing graphics on the screen.
 * It also has a starting menu that uses JComboBox so that the user can choose the
 * game's difficulty. To play the game, there is a button that can be clicked by the
 * user to start the game. An InputListner is instantiated so that the game can take
 * keyboard input from the user. An Image and Graphics class, as well as the paint()
 * function, is used to draw graphics on the screen.
 * 
 * @author Daniel Cheng
 *
 * @since 1.0
 */

public class GameWindow extends JFrame
	implements ActionListener
{
	private Image gdImage;
	private Graphics gdGraphics;
	private LevelEditor lvl;
	
	private JButton play;
	private String[] difficulty = {"Easy", "Medium", "Hard", "Insane", "Impossible"};
	
	private static JComboBox<String> comboBox;
	private static RunningPlayer p = new RunningPlayer(-GameConstants.PLAYER_HITBOX,
														GameConstants.GROUND-GameConstants.PLAYER_HITBOX,
														GameConstants.CUBE,
														GameConstants.UP,
														GameConstants.THREE_TIMES, false);
	public boolean gameStarted = false;
	
	public GameWindow(int x, int y, int width, int height) {
		super("Recycle Wave");
		
		this.setBounds(x, y, width, height);
		this.setLayout(new FlowLayout());
		this.addKeyListener(new InputListener());
		
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
	
	public void actionPerformed(ActionEvent e) {
		gameStarted = true;
		repaint();
		Thread p1 = new Thread(getP());
		p1.start();
	}
	
	@Override
	public void paint(Graphics g) {
		if (gameStarted) {
			lvl = new LevelEditor();
			gdImage = createImage(getWidth(), getHeight());
			gdGraphics = gdImage.getGraphics();
			draw(gdGraphics);
			g.drawImage(gdImage, 0, 0, this);
		}
		
		else {
			drawGameTitle(g);
		}
	}
	
	public void drawGameTitle(Graphics g) {
		p.drawCenteredText(g, "Recycle Wave", 96, 1.5);
		
		if (System.getProperty("os.name").contains("Mac")) {
			g.drawRect(643, 33, 120, 40);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
			g.drawString("Drag your cursor here", 650, 56);
			g.drawRect(761, 37, 120, 99);
			g.drawRect(767, 39, 126, 27);
			g.drawLine(860, 39, 860, 66);
			g.drawString("Over there>", 772, 56); 
			g.setFont(new Font (Font.SANS_SERIF, Font.PLAIN, 24));
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
	
	public void drawGameGraphics(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.drawLine(0, GameConstants.GROUND, 1550, GameConstants.GROUND);
		g.drawLine(0, GameConstants.CEILING, 1550, GameConstants.CEILING);
	    
		lvl.createPlatforms(g, Color.BLACK, GameConstants.BLK);
		lvl.createWalls(g, Color.RED, GameConstants.BLK);
		lvl.createNormalGravityPortals(g, Color.GREEN, GameConstants.NGP);
		lvl.createFlippedGravityPortals(g, Color.GREEN, GameConstants.FGP);
		lvl.createNormalSizePortals(g, Color.GREEN, GameConstants.NSP);
		lvl.createMiniSizePortals(g, Color.GREEN, GameConstants.MSP);
		lvl.createWavePortals(g, Color.GREEN, GameConstants.WVP);
		lvl.createCubePortals(g, Color.GREEN, GameConstants.CBP);
		lvl.createSlopes(g, new Color(240, 16, 160), GameConstants.GS, GameConstants.CS);
		lvl.createSawblades(g, Color.RED, GameConstants.SB);
		lvl.createSpeedPortals(g, Color.GREEN);
		lvl.drawProgressBar(g, GameConstants.FINISH_LINE, Color.BLACK, Color.CYAN);
	}
	
	public void draw(Graphics g) {
		drawGameGraphics((Graphics2D) g);
		getP().drawPlayer(g);
		repaint();
	}

	public static JComboBox<String> getComboBox() {
		return comboBox;
	}

	public static RunningPlayer getP() {
		return p;
	}
}
