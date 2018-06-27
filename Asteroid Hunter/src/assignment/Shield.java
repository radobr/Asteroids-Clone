package assignment;

import java.awt.Graphics2D;

import javax.swing.Timer;

import utilities.SoundManager;
import utilities.Vector2D;

public class Shield extends PowerUp {
	
	
	public static final int RADIUS = 10;
	public static final double SCALE = 40;
	public static final int TIME_ALIVE = 5000;
	
	
	public Shield(Vector2D pos){
		super(pos,new Vector2D(),RADIUS);
		timer = new Timer(TIME_ALIVE, ev->{dead = true;});
		timer.start();
		sprite = new Sprite(Sprite.IMMUNITY,position,velocity,SCALE,SCALE);
	}

	@Override
	public void draw(Graphics2D g) {		
		sprite.draw(g);
	}
	
	public void collisionHandling(GameObject other){
		if(other instanceof PlayerShip && overlap(other)){
			pickUp((PlayerShip) other);
		}
	}
	
	public void pickUp(PlayerShip ship){
		super.hit();
		SoundManager.powerUp();
		ship.immune = true;
		ship.immunityTimer.restart();
	}
}
