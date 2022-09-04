
public interface GameConstants // Fixed constants for all classes in the program
{
	// Constants related to the game window
	int GROUND = 780;
	int CEILING = 60;
	int PLAYER_HITBOX = 40;
	double myFactor = 1.0185508475756433;
	
	// Constants related to the player's gamemode
	int CUBE = 1;
	int SHIP = 2;
	int BALL = 4;
	int WAVE = 8;
	
	// Constants related to the player's gravity
	int DOWN = 1;
	int UP = -1;
	
	// Constants related to the placement of objects in the level editor, and when the player has won the game
	int START_LINE = 300;
	int FINISH_LINE = -11300;
	int PIXELS_PER_BLOCK = 40;
	int d = 150;
	
	// Constants related to the player's speed
	double halfTimes = 0.5;
	double oneTimes = 1;
	double twoTimes = 1.25;
	double threeTimes = 1.5;
	double fourTimes = 2;
} 
