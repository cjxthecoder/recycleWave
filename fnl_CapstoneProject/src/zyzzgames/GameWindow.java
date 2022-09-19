/*
 * Copyright (c) 2022, Team Zyzz LLC, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Team Zyzz designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Team Zyzz in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * Please contact Sunnyvale, Rockefeller Dr, Apt 3B, CA 94097 USA if you
 * need additional information or have any questions.
 */

package zyzzgames;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

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

/**
 * The GameWindow class is the class responsible for drawing graphics on the screen.
 * It also has a starting menu that uses JComboBox so that the user can choose the
 * game's difficulty. To play the game, there is a button that can be clicked by the
 * user to start the game. An InputListner is instantiated so that the game and take
 * keyboard input from the user. An Image and Graphics class, as well as the .paint()
 * function, is used to draw graphics on the screen.
 * 
 * @author Daniel Cheng
 *
 * @since 1.0
 */

public class GameWindow extends JFrame
	implements ActionListener, GameConstants
{
	public boolean gameStarted = false;
	
	private JButton play;
	private String[] difficulty = {"Easy", "Medium", "Hard", "Insane", "Impossible"};
	static JComboBox<String> comboBox;
	
	Image gdImage;
	Graphics gdGraphics;
	LevelEditor lvl;
	
	static RunningPlayer p = new RunningPlayer(-PLAYER_HITBOX, GROUND-PLAYER_HITBOX, CUBE, UP, threeTimes, false);
	
	public GameWindow()
	{
		super("Recycle Wave");
		this.addKeyListener(new InputListener());
		
		play = new JButton("Play");
		play.setPreferredSize(new Dimension(120, 40));
		play.addActionListener(this);
		play.setFocusable(false);
		
		comboBox = new JComboBox<>(difficulty);
		comboBox.setFocusable(false);
		
		this.add(play);
		this.add(comboBox);
		
		Container c = getContentPane();
	    c.setLayout(new FlowLayout());
	}
	
	public void actionPerformed(ActionEvent e)
	{
		gameStarted = true;
		repaint();
		Thread p1 = new Thread(p);
		p1.start();
	}
	
	@Override
	public void paint(Graphics g)
	{
		if (gameStarted) {
			lvl = new LevelEditor();
			gdImage = createImage(getWidth(), getHeight());
			gdGraphics = gdImage.getGraphics();
			draw(gdGraphics);
			g.drawImage(gdImage, 0, 0, this);
		}
		
		else {
			p.drawCenteredText(g, "Recycle Wave", 96, 1.5);
			g.drawRect(660, 35, 121, 40);
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
	
	public void drawGameGraphics(Graphics2D g)
	{
//		for (int i=-20; i<=4300; i+=40) {
//			g.drawLine(i, Player.CEILING, i, Player.GROUND);
//		}
//		
//		for (int i=Player.CEILING; i<=Player.GROUND; i+=40) {
//			g.drawLine(-20, i, 1540, i);
//		}
		
		g.setColor(Color.CYAN);
		g.drawLine(0, GROUND, 1550, GROUND);
	    g.drawLine(0, CEILING, 1550, CEILING);
	    
		lvl.createPlatforms(g, Color.BLACK, new ImageIcon("block.png").getImage());
		lvl.createWalls(g, Color.RED, new ImageIcon("block.png").getImage());
		lvl.createNormalGravityPortals(g, Color.GREEN, new ImageIcon("normalGravityPortal.png").getImage());
		lvl.createNormalSizePortals(g, Color.GREEN, new ImageIcon("NormalSizePortal.png").getImage());
		lvl.createMiniSizePortals(g, Color.GREEN, new ImageIcon("miniSizePortal.png").getImage());
		lvl.createWavePortals(g, Color.GREEN, new ImageIcon("wavePortal.png").getImage());
		lvl.createCubePortals(g, Color.GREEN, new ImageIcon("cubePortal.png").getImage());
		lvl.createSlopes(g, new Color(240, 20, 160), new ImageIcon("groundSpike.png").getImage(), new ImageIcon("ceilingSpike.png").getImage());
		lvl.createSawblades(g, Color.RED, new ImageIcon("fish.png").getImage());
		lvl.createSpeedPortal(g, Color.GREEN);
		lvl.drawProgressBar(g, -FINISH_LINE, Color.BLACK, Color.CYAN);
	}
	
	public void draw(Graphics g)
	{
	    drawGameGraphics((Graphics2D) g);
		p.drawPlayer(g);
		repaint();
	}
}
