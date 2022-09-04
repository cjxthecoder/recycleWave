import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputListener extends KeyAdapter // A InputListener IS-A KeyAdapter
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
