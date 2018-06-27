package assignment;

import utilities.SoundManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by radobr on 03/02/2017.
 */
public class Keys extends KeyAdapter implements Controller {
    private Action action;
    public Keys() {
        action = new Action();
    }

    public Action action() {
        // this is defined to comply with the standard interface
        return action;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                action.thrust = 1;
                SoundManager.startThrust();
                break;
            case KeyEvent.VK_A:
                action.turn = -1;
                break;
            case KeyEvent.VK_D:
                action.turn = +1;
                break;
            case KeyEvent.VK_SPACE:
                action.shoot = true;
                break;
            case KeyEvent.VK_ESCAPE:
            	System.exit(0);
            	break;
        }
    }

    public void keyReleased(KeyEvent e) {
        // please add appropriate event handling code
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                action.thrust = 0;
                SoundManager.stopThrust();
                break;
            case KeyEvent.VK_A:
                action.turn = 0;
                break;
            case KeyEvent.VK_D:
                action.turn = 0;
                break;
            case KeyEvent.VK_SPACE:
                action.shoot = false;
                break;
        }
    }
}
