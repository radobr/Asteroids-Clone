package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by radobr on 20/01/2017.
 */


@SuppressWarnings("serial")
public class View extends JComponent {
	
    public static final Font FONT = new Font("Verdana", 15, 15);
    public Image bg = Sprite.MILKYWAY2;
    public AffineTransform bgTransf;

    private Game game;
    //private PauseScreen pause;

    public View(Game game) {
        this.game = game;
        double imWidth = bg.getWidth(null);
        double imHeight = bg.getHeight(null);
        double stretchx = (imWidth > Constants.FRAME_WIDTH? 1 :
                                    Constants.FRAME_WIDTH/imWidth);
        double stretchy = (imHeight > Constants.FRAME_HEIGHT? 1 :
                                    Constants.FRAME_HEIGHT/imHeight);
        bgTransf = new AffineTransform(); 
        bgTransf.scale(stretchx, stretchy);
        
       // pause = new PauseScreen(game);
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        
        //Set background
        g.drawImage(bg, bgTransf, null);

		// Draw game objects
		synchronized (Game.class) {
			for (GameObject object : game.gameObjects) {
				object.draw(g);
			}
		}

		// Display score and number of lives
		g.setColor(Color.WHITE);
		g.setFont(FONT);
		g.drawString("Score: " + game.getScore(), 5, 15);
		g.drawString("Level " + game.getLevel(), getWidth() / 2 - 20, 15);
		g.drawString("Lives: " + game.playerShip.livesLeft, getWidth() - 70, 15);
    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}
