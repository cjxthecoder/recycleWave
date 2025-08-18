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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * The GameWindowNew class is the class responsible for drawing graphics on the screen.
 * It also has a starting menu that uses JComboBox so that the user can choose the
 * game's difficulty. To play the game, there is a button that can be clicked by the
 * user to start the game. An InputListner is instantiated so that the game can take
 * keyboard input from the user. An Image and Graphics class, as well as the paintComponent()
 * function, is used to draw graphics on the screen.
 * 
 * @author ChatGPT-5
 *
 * @since 1.0
 */

public class GameWindowNew extends JFrame
	implements ActionListener
{
    // ---------- Your existing fields ----------
    private LevelEditor lvl;

    private JButton play;
    private String[] difficulty = {"Easy", "Medium", "Hard", "Insane", "Impossible"};

    private static JComboBox<String> comboBox;
    private static RunningPlayer p = new RunningPlayer(
            -GameConstants.PLAYER_HITBOX,
            GameConstants.GROUND - GameConstants.PLAYER_HITBOX,
            GameConstants.CUBE,
            GameConstants.UP,
            GameConstants.THREE_TIMES,
            false
    );

    public boolean gameStarted = false;

    // ---------- New: fixed internal canvas at 1536x840 ----------
    private static final int SRC_W = 1536;
    private static final int SRC_H = 840;
    private final BufferedImage canvas = new BufferedImage(SRC_W, SRC_H, BufferedImage.TYPE_INT_ARGB);

    // Center panel that draws (scaled) canvas
    private final JPanel gameView = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Render the current frame into the 1536x840 canvas
            renderFrame();

            // Scale to fit while preserving aspect ratio (letterbox if needed)
            int dstW = getWidth(), dstH = getHeight();
            double sx = dstW / (double) SRC_W, sy = dstH / (double) SRC_H;
            double scale = Math.min(sx, sy);
            int w = (int) Math.round(SRC_W * scale);
            int h = (int) Math.round(SRC_H * scale);
            int x = (dstW - w) / 2, y = (dstH - h) / 2;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(canvas, x, y, w, h, null);
            g2.dispose();
        }
    };

    // Optional: a simple Swing timer to drive repaints at ~200 FPS
    private final Timer repaintTimer = new Timer(5, e -> gameView.repaint());

    public GameWindowNew(int x, int y, int width, int height) {
        super("Recycle Wave");

        // Layout: CENTER = game, SOUTH = controls
        setLayout(new BorderLayout());
        add(gameView, BorderLayout.CENTER);

        // Controls row at the bottom
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER));
        play = new JButton("Play");
        play.setPreferredSize(new Dimension(120, 40));
        play.addActionListener(this);
        play.setFocusable(false);

        comboBox = new JComboBox<>(difficulty);
        comboBox.setFocusable(false);

        controls.add(play);
        controls.add(comboBox);
        controls.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        add(controls, BorderLayout.SOUTH);

        // Window prep
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input listener—attach to the view so it gets focus
        gameView.addKeyListener(new InputListener());
        gameView.setFocusable(true);

        setVisible(true);
        gameView.requestFocusInWindow(); // so keys go to your listener
    }

    // ---------- Event handlers ----------
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameStarted) {
            gameStarted = true;

            // If you still want your RunningPlayer thread:
            Thread p1 = new Thread(getP());
            p1.start();

            // Start rendering loop (repaints only; your game state can update in p1 or here)
            repaintTimer.start();
        }
    }

    // ---------- Rendering pipeline ----------
    /** Draws the current frame into the 1536x840 canvas. */
    private void renderFrame() {
        Graphics2D g = canvas.createGraphics();
        try {
            // Clear background (choose your color)
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, SRC_W, SRC_H);

            if (gameStarted) {
                lvl = new LevelEditor(); // lazy init if you need it here
                draw(g); // your existing draw() now targets the canvas graphics
            } else {
                drawGameTitle(g);
            }
        } finally {
            g.dispose();
        }
    }

    // ---------- Your existing methods (kept, just used differently) ----------
    /** Code by Daniel Cheng */
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
    
    /** Code by Daniel Cheng */
    public void drawGameGraphics(Graphics2D g) {
//		// Grid lines used when editing the level
//		for (int i=-20; i<=4300; i+=40) {
//			g.drawLine(i, Player.CEILING, i, Player.GROUND);
//		}
//		
//		for (int i=Player.CEILING; i<=Player.GROUND; i+=40) {
//			g.drawLine(-20, i, 1540, i);
//		}
		
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
		
//		if (p.getGamemode() == GameConstants.WAVE) {
//			lvl.addPixels(g, getP().getX(), getP().getY());
//			lvl.drawPixels(g, getP().getX(), getP().getY());
//		}
    }

    public void draw(Graphics g) {
        // IMPORTANT: do NOT call repaint() from here (no recursive repaint loop).
        drawGameGraphics((Graphics2D) g);
        getP().drawPlayer(g);
    }

    // ---------- Accessors ----------
    public static JComboBox<String> getComboBox() {
    	return comboBox;
    
    }
    public static RunningPlayer getP() {
    	return p;
    }
}