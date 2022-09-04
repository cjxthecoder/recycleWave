import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.BorderLayout;
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

// GameWindow IS-A JFrame and HAS-A Game Constants
public class GameWindow extends JFrame
	implements ActionListener, GameConstants
{
	public boolean gameStarted = false;
	
	private JButton play;
	private String[] difficulty = {"Easy", "Medium", "Hard", "Insane", "Impossible"};
	static JComboBox<String> comboBox;
	
	Image gdImage;
	Graphics gdGraphics;
	
	Image block = (new ImageIcon("block.png")).getImage();
	Image groundSpike = (new ImageIcon("groundSpike.png")).getImage();
	Image ceilingSpike = (new ImageIcon("ceilingSpike.png")).getImage();
	Image normalGravityPortal = (new ImageIcon("normalGravityPortal.png")).getImage();
	Image normalSizePortal = (new ImageIcon("normalSizePortal.png")).getImage();
	Image wavePortal = (new ImageIcon("wavePortal.png")).getImage();
	Image cubePortal = (new ImageIcon("cubePortal.png")).getImage();
	Image sawblade = (new ImageIcon("fish.png")).getImage();
	Image miniPortal = (new ImageIcon("miniSizePortal.png")).getImage();
	
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
			gdImage = createImage(getWidth(), getHeight());
			gdGraphics = gdImage.getGraphics();
			draw(gdGraphics);
			g.drawImage(gdImage, 0, 0, this);
		}
		
		else {
			p.drawCenteredText(g, "Recycle Wave", 96, 1.8);
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
		
		g.setColor(new Color(40, 220, 230));
	    g.drawLine(0, GROUND, 2000, GROUND);
	    g.setColor(new Color(40, 220, 230));
	    g.drawLine(0, CEILING, 2000, CEILING);
	    
	    LevelEditor lvl = new LevelEditor();
		lvl.createPlatforms(g, Color.BLACK, block);
		lvl.createWalls(g, Color.RED, block);
		lvl.createNormalGravityPortals(g, Color.GREEN, normalGravityPortal);
		lvl.createNormalSizePortals(g, Color.GREEN, normalSizePortal);
		lvl.createMiniSizePortals(g, Color.GREEN, miniPortal);
		lvl.createWavePortals(g, Color.GREEN, wavePortal);
		lvl.createCubePortals(g, Color.GREEN, cubePortal);
		lvl.createSlopes(g, new Color(240, 20, 160), groundSpike, ceilingSpike);
		lvl.createSawblades(g, Color.RED, sawblade);
		lvl.createSpeedPortal(g, Color.GREEN);
		lvl.drawProgressBar(g, FINISH_LINE, Color.BLACK, Color.CYAN);
	}
	
	public void draw(Graphics g)
	{
	    drawGameGraphics((Graphics2D) g);
		p.drawPlayer(g);
		repaint();
	}
}
