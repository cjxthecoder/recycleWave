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
				if (player.x < START_LINE || LevelEditor.dx < FINISH_LINE + START_LINE)
				{
					setXDirection(4.0 * speed);
					makePlayerReach300();
					Thread.sleep(5);
				}
				
				if (c.checkDeathCollisions()) // c.checkDeathCollisions()
				{
					gs.stopMusic();
					fullScore /= myFactor;
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
					
					if (c.checkMiniSizeCollision())
					{
						mini = true;
						if (gamemode == WAVE) {
							hitbox = PLAYER_HITBOX / 4;
							player.height = PLAYER_HITBOX / 4;
							player.width = PLAYER_HITBOX / 4;
						}
						else {
							hitbox = PLAYER_HITBOX / 2;
							player.height = PLAYER_HITBOX / 2;
							player.width = PLAYER_HITBOX / 2;
						}
					}
					
					if (c.checkNormalSizeCollision())
					{
						mini = false;
						if (gamemode == WAVE) {
							hitbox = PLAYER_HITBOX / 2;
							player.height = PLAYER_HITBOX / 2;
							player.width = PLAYER_HITBOX / 2;
						}
						else {
							hitbox = PLAYER_HITBOX;
							player.height = PLAYER_HITBOX;
							player.width = PLAYER_HITBOX;
						}
					}
					
					if (c.checkWaveCollision())
					{
						gamemode = WAVE;
						hitbox = PLAYER_HITBOX / 2;
						player.height = PLAYER_HITBOX / 2;
						player.width = PLAYER_HITBOX / 2;
					}
					
					if (c.checkCubeCollision())
					{
						if (gamemode == WAVE) {
							gamemode = CUBE;
							hitbox = PLAYER_HITBOX;
							player.height = PLAYER_HITBOX;
							player.width = PLAYER_HITBOX;
						}
						else {
							gamemode = CUBE;
						}
					}
					
					if (player.x < 1550) {
						move();
						fall();
					}
					
					if (player.x >= 1550) {
						player.x = 1550;
					}
					
					if (player.x >= START_LINE && LevelEditor.dx >= FINISH_LINE + START_LINE) {
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
