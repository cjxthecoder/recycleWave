import java.awt.Color;
import javax.swing.JFrame;

public class Main extends JFrame
{	
	public static void main(String[] args)
	{
		GameWindow gw = new GameWindow();
		gw.setBounds(0, 0, 1536, 840);
		gw.setBackground(new Color(20, 90, 230));
		gw.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gw.setResizable(false);
		gw.setVisible(true);
	}
}