
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The InputListener class is a {@link KeyAdapter} that invokes the built-in
 * keyPressed and keyReleased functions to take keyboard input from the user.
 * When one of those functions is called, the keyPressed and keyRelease functions
 * (not related to KeyAdapter) from the Player class will be called, resulting
 * in movement of the Player.
 * 
 * @author Daniel
 *
 * @since 1.0
 */

public class InputListener extends KeyAdapter
{
	@Override
	public void keyPressed(KeyEvent e) {
		GameWindow.p.keyPressed(e);
	}	
	@Override
	public void keyReleased(KeyEvent e) {
		GameWindow.p.keyReleased(e);
	}
}
