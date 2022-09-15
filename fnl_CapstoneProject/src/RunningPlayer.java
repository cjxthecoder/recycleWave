import java.awt.Image;

import javax.swing.ImageIcon;

public class RunningPlayer extends Player // RunningPlayer IS-A Player!!!
	implements Runnable
{
	public RunningPlayer(int x, int y, int gamemode, int gravity, double speed, boolean mini) {
		super(x, y, gamemode, gravity, speed, mini);
	}
	
	GameSound gs = new GameSound();
	
	@Override
	public void run()
	{	
		gs.loadMusic();
		try {
			Thread.sleep(500);
			while(true)
			{
				if ((player.x < START_LINE || LevelEditor.dx < START_LINE - FINISH_LINE) && !gameWon)
				{
					setXDirection(4.0 * speed);
					makePlayerReach300();
					Thread.sleep(5);
				}
				
				if (c.checkDeathCollisions()) // c.checkDeathCollisions()
				{
					gs.stopMusic();
					fullScore /= myFactor;
					attempts++;
					Thread.sleep(1000);
					resetPlayerFields();
					LevelEditor.setDx(2 * START_LINE);
				}
				
				else {
					gs.startMusic();
					
					if (c.checkSpeedCollision())
					{
						switch (String.valueOf(GameWindow.comboBox.getSelectedItem())) {
							case "Easy":
								speed = halfTimes;
								break;
							case "Medium":
								speed = oneTimes;
								break;
							case "Hard":
								speed = twoTimes;
								break;
							case "Insane":
								speed = threeTimes;
								break;
							case "Impossible":
								speed = fourTimes;
								break;
							default:
								speed = threeTimes;
								break;
							}
					}
					
					if (c.checkNormalGravityCollision())
					{
						if (gravity == UP)
						{
							t1 = 0;
							t2 = 0;
							gravity = DOWN;
						}
					}
					
					if (c.checkMiniSizeCollision()) // divide by 4 if player is wave, else divide by 2
					{
						mini = true;
						if (gamemode == WAVE) {
							setPlayerSize(0.25);
						}
						else {
							setPlayerSize(0.5);
						}
					}
					
					if (c.checkNormalSizeCollision()) // divide by 2 if player is wave, else divide by 1
					{
						mini = false;
						if (gamemode == WAVE) {
							setPlayerSize(0.5);
						}
						else {
							setPlayerSize(1.0);
						}
					}
					
					if (c.checkWaveCollision()) // divide by 2
					{
						gamemode = WAVE;
						setPlayerSize(0.5);
					}
					
					if (c.checkCubeCollision()) // divide by 1
					{
						if (gamemode == WAVE) {
							gamemode = CUBE;
							setPlayerSize(1.0);
						}
						else {
							gamemode = CUBE;
						}
					}
					
					if (player.x < 1452) {
						move();
						fall();
					}
					
					if (player.x >= 1452) {
						player.x = 1452;
						gameWon = true;
					}
					
					if (player.x >= START_LINE && LevelEditor.dx >= START_LINE - FINISH_LINE) {
						LevelEditor.goForward((int)(4.0 * speed));
					}
					
					else {
						LevelEditor.goForward(0);
					}
					Thread.sleep(5);
				}
			}
		} catch(Exception e) {
	        e.printStackTrace();
		}
	}
}
