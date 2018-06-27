package assignment;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	
	
	//TODO: Use
	@SuppressWarnings("unused")
	public MainMenu(){
		JButton play = new JButton("PLAY");
		play.setBorderPainted(false);
		play.addActionListener(ev->{}); //TODO: Implement

		JButton quit = new JButton("QUIT");
		play.setBorderPainted(false);
		play.addActionListener(ev->{
			System.exit(0);
		});

		
		setVisible(true);
	}
}
    