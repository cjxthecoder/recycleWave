
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Collision implements GameConstants { // Collision HAS-A Game Constant
	
	public boolean checkDeathCollisions()
	{
		Rectangle r = new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox());
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.slopes.length; i++)
		{
			if (r.intersectsLine(new Line2D.Double(lvl.slopes[i][0], lvl.slopes[i][1],lvl.slopes[i][2], lvl.slopes[i][3])))
			{
				return true;
			}
		}
		
		// Circle collision detection below
		for (int i=0; i<lvl.sawblades.length; i++)
		{
			if (intersectsCircle(lvl.sawblades[i][1], lvl.sawblades[i][2], lvl.sawblades[i][0],
								 Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkSpeedCollision()
	{
		Rectangle r = new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox());
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.speedPortal.length; i++)
		{
			if (r.intersects(new Rectangle(lvl.speedPortal[i][0], lvl.speedPortal[i][1], 95, 83)));
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkNormalGravityCollision()
	{
		Rectangle r = new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox());
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.normalGravityPortals.length; i++)
		{
			if (r.intersects(new Rectangle(lvl.normalGravityPortals[i][0], lvl.normalGravityPortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkMiniSizeCollision()
	{
		Rectangle r = new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox());
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.miniSizePortals.length; i++)
		{
			if (r.intersects(new Rectangle(lvl.miniSizePortals[i][0], lvl.miniSizePortals[i][1], 40, 120)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkNormalSizeCollision()
	{
		Rectangle r = new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox());
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.normalSizePortals.length; i++)
		{
			if (r.intersects(new Rectangle(lvl.normalSizePortals[i][0], lvl.normalSizePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkWaveCollision()
	{
		Rectangle r = new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox());
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.wavePortals.length; i++)
		{
			if (r.intersects(new Rectangle(lvl.wavePortals[i][0], lvl.wavePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkCubeCollision()
	{
		Rectangle r = new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox());
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.cubePortals.length; i++)
		{
			if (r.intersects(new Rectangle(lvl.cubePortals[i][0], lvl.cubePortals[i][1], (3 * PIXELS_PER_BLOCK) / 2, (15 * PIXELS_PER_BLOCK) / 4)))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkPlatformCollision()
	{
		Rectangle r = new Rectangle(Player.getX(), Player.getY(), Player.getHitbox(), Player.getHitbox());
		LevelEditor lvl = new LevelEditor();
		
		for (int i=0; i<lvl.platforms.length; i++)
		{
			if (r.intersectsLine(new Line2D.Double(lvl.platforms[i][0], lvl.platforms[i][1], lvl.platforms[i][2], lvl.platforms[i][1])))
			{
				Player.platformY = lvl.platforms[i][1];
				return true;
			}
		}
		
		return false;
	}
	
	private boolean intersectsCircle(int circleX, int circleY, int diameter, int rectX, int rectY, int width, int height)
	{
		double testX = circleX;
		double testY = circleY;

		if (circleX < rectX) {  
			testX = rectX;
		}
		else if (circleX > rectX + width) {
			testX = rectX + width;
		}
		if (circleY < rectY) {
			testY = rectY;
		}
		else if (circleY > rectY + height) {
			testY = rectY + height;
		}

		double distX = circleX - testX;
		double distY = circleY - testY;
		double distance = Math.sqrt((distX * distX) + (distY * distY));

		return (distance <= (double)diameter / 2.0);
	}
}
