//package zyzzgames;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.RenderingHints;
//import java.awt.Stroke;
//import java.awt.geom.Line2D;
//
//import javax.swing.ImageIcon;
//
//public class LevelLoader {
//	
//	public void createSpeedPortals(Graphics2D g2d, Color c)
//	{
//		Image pic1 = (new ImageIcon("half.png")).getImage();
//		Image pic2 = (new ImageIcon("one.png")).getImage();
//		Image pic3 = (new ImageIcon("two.png")).getImage();
//		Image pic4 = (new ImageIcon("three.png")).getImage();
//		Image pic5 = (new ImageIcon("four.png")).getImage();
//		
//		g2d.setColor(c);
//		
//		for (int i = 0; i < speedPortals.length; i++)
//		{
//			switch (String.valueOf(GameWindow.comboBox.getSelectedItem()))
//			{
//				case "Easy":
//					g2d.drawImage(pic1, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
////					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
//					break;
//				case "Medium":
//					g2d.drawImage(pic2, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
////					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
//					break;
//				case "Hard":
//					g2d.drawImage(pic3, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
////					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
//					break;
//				case "Insane":
//					g2d.drawImage(pic4, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
////					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
//					break;
//				case "Impossible":
//					g2d.drawImage(pic5, speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40, null);
////					g2d.drawRect(speedPortals[i][0], speedPortals[i][1], (19 * PIXELS_PER_BLOCK) / 8, (83 * PIXELS_PER_BLOCK) / 40);
//					break;					
//			}
//		}
//	}
//	
//	// Create normal gravity portals.
//	public void createNormalGravityPortals(Graphics2D g2d, Color c, Image pic)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(0);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < normalGravityPortals.length; i++)
//		{
//			if (!(normalGravityPortals[i][0] < -150 || normalGravityPortals[i][0] > 1550))
//			{
////				g2d.drawRect(normalGravityPortals[i][0], flippedGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
//				g2d.drawImage(pic, normalGravityPortals[i][0], normalGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
//			}
//		}
//	}
//	
//	// Create flipped gravity portals. (unused)
//	public void createFlippedGravityPortals(Graphics2D g2d, Color c, Image pic)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(0);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < flippedGravityPortals.length; i++)
//		{
//			if (!(flippedGravityPortals[i][0] < -150 || flippedGravityPortals[i][0] > 1550))
//			{
////				g2d.drawRect(flippedGravityPortals[i][0], flippedGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
//				g2d.drawImage(pic, flippedGravityPortals[i][0], flippedGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
//			}
//		}
//	}
//	
//	// Create normal size portals.
//	public void createNormalSizePortals(Graphics2D g2d, Color c, Image pic)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(0);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < normalSizePortals.length; i++)
//		{
//			if (!(normalSizePortals[i][0] < -150 || normalSizePortals[i][0] > 1550))
//			{
////				g2d.drawRect(normalSizePortals[i][0], normalSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
//				g2d.drawImage(pic, normalSizePortals[i][0], normalSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
//			}
//		}
//	}
//	
//	// Create mini size portals.
//	public void createMiniSizePortals(Graphics2D g2d, Color c, Image pic)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(0);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < miniSizePortals.length; i++)
//		{
//			if (!(miniSizePortals[i][0] < -150 || miniSizePortals[i][0] > 1550))
//			{
////				g2d.drawRect(miniSizePortals[i][0], miniSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
//				g2d.drawImage(pic, miniSizePortals[i][0], miniSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
//			}
//		}
//	}
//	
//	// Create wave portals.
//	public void createWavePortals(Graphics2D g2d, Color c, Image pic)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(0);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < wavePortals.length; i++)
//		{
//			if (!(wavePortals[i][0] < -150 || wavePortals[i][0] > 1550))
//			{
////				g2d.drawRect(wavePortals[i][0], wavePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
//				g2d.drawImage(pic, wavePortals[i][0], wavePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
//			}
//		}
//	}
//	
//	// Create cube portals.
//	public void createCubePortals(Graphics2D g2d, Color c, Image pic)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(0);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < cubePortals.length; i++)
//		{
//			if (!(cubePortals[i][0] < -150 || cubePortals[i][0] > 1550))
//			{
////				g2d.drawRect(cubePortals[i][0], cubePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4);
//				g2d.drawImage(pic, cubePortals[i][0], cubePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4, null);
//			}
//		}
//	}
//	
//	/**
//	 * Platforms: x1, y-level, x2; platform length is x2 - x1 + 1.
//	 */
//	public void createPlatforms(Graphics2D g2d, Color c, Image pic)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(0);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < platforms.length; i++)
//		{
//			if (!(platforms[i][1] < -10 || platforms[i][0] > 1550))
//			{
////				g2d.drawLine(platforms[i][0], platforms[i][1], platforms[i][2], platforms[i][1]);
//				for (int j = platforms[i][0]; j < platforms[i][2]; j += PIXELS_PER_BLOCK)
//				{
//					g2d.drawImage(pic, j, platforms[i][1], PIXELS_PER_BLOCK, PIXELS_PER_BLOCK, null);
//				}
//			}
//		}
//	}
//
//	/**
//	 * Walls: y1, y2, x-position; wall height is y2 - y1 + 1.
//	 */
//	public void createWalls(Graphics2D g2d, Color c, Image pic)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(0);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < walls.length; i++)
//		{
//			if (!(walls[i][2] < -10 || walls[i][2] > 1550))
//			{
////				g2d.drawLine(walls[i][2], walls[i][0], walls[i][2], walls[i][1]);
//				for (int j = walls[i][0]; j < walls[i][1]; j += PIXELS_PER_BLOCK)
//				{
//					g2d.drawImage(pic, walls[i][2], j, PIXELS_PER_BLOCK, PIXELS_PER_BLOCK, null);
//				}
//			}
//		}
//	}
//
//	/**
//	 * Sawblades: diameter, x-center, y-level;
//	 */
//	public void createSawblades(Graphics2D g2d, Color c, Image pic)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(0);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < sawblades.length; i++)
//		{
//			if (!((sawblades[i][1] + sawblades[i][0] / 2) < -10 || (sawblades[i][1] - sawblades[i][0] / 2) > 1550))
//			{
////				g2d.fillOval(sawblades[i][1] - sawblades[i][0] / 3, sawblades[i][2] -  sawblades[i][0] / 3, (2 * sawblades[i][0]) / 3, (2 * sawblades[i][0]) / 3);
//				g2d.drawImage(pic, sawblades[i][1] - sawblades[i][0] / 2, sawblades[i][2] - sawblades[i][0] / 2, null);
//			}  
//		}
//	}
//	
//	/**
//	 * Slopes: x-start, y-start, x-end, y-end
//	 * Since we are lazy we decided to make ground spikes slope as well.
//	 */
//	public void createSlopes(Graphics2D g2d, Color c, Image groundSpike, Image ceilingSpike)
//	{
//		g2d.setColor(c);
//		Stroke stroke = new BasicStroke(10);
//		g2d.setStroke(stroke);
//		
//		for (int i = 0; i < slopes.length; i++)
//		{
//			if (!(slopes[i][1] < -10 || slopes[i][0] > 1550))
//			{
//				if (slopes[i][1] == slopes[i][3] && slopes[i][1] >= 420)
//				{
//					for (int j = slopes[i][0]; j < slopes[i][2]; j += PIXELS_PER_BLOCK)
//					{
//						g2d.drawImage(groundSpike, j, slopes[i][1] - 23, null);
//					}
//				}
//				
//				else if (slopes[i][1] == slopes[i][3] && slopes[i][1] < 420)
//				{
//					for (int j = slopes[i][0]; j < slopes[i][2]; j += PIXELS_PER_BLOCK)
//					{
//						g2d.drawImage(ceilingSpike, j, slopes[i][1], null);
//					}
//				}
//				
//				else if (slopes[i][1] != slopes[i][3])
//				{
//					g2d.draw(new Line2D.Double(slopes[i][0], slopes[i][1], slopes[i][2], slopes[i][3]));
//				}
//			}
//		}
//	}
//
//	public void drawProgressBar(Graphics2D g2d, int levelEndPoint, Color barColor, Color progressColor)
//	{
//		Stroke stroke = new BasicStroke(3);
//		g2d.setStroke(stroke);
//		g2d.setColor(progressColor);
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.fillRect(558, 38, (420 * (dx - START_LINE)) / levelEndPoint, 15);
//		g2d.setColor(barColor);
//		g2d.drawRect(558, 38, 420, 15);
//		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
//		g2d.drawString((Math.max(0, (100 * (dx - START_LINE)) / levelEndPoint)) + "%", 986, 54);
//	}
//}
