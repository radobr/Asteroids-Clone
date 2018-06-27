package assignment;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PauseScreen extends JPanel{
	
	public PauseScreen(Game game){
		setFont(View.FONT);
		
		JButton returnToGame = new JButton("Return to game");
		returnToGame.setBorderPainted(false);
		returnToGame.addActionListener(ev->{
			setVisible(false);
			game.paused = false;});
		add(returnToGame);
		
//		Add quit to menu button
//		JButton quitToMenu = new JButton("Quit to Menu");
//		quitGame.setBorderPainted(false);
//		quitToMenu.addActionListener(ev->{
//			setVisible(false);
//			game.paused = false;});
//		add(quitToMenu);
		
		//Add quit game button
		JButton quitGame = new JButton("Quit Game");
		quitGame.setBorderPainted(false);
		quitGame.addActionListener(ev->{
			System.exit(0);});
		add(quitGame);
		
		setVisible(true);
	}
}
