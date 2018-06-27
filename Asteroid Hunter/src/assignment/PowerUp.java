package assignment;

import javax.swing.Timer;

import utilities.Vector2D;

/**
 * Created by radobr on 23/03/2017.
 */
public abstract class PowerUp extends GameObject{
	
	public Timer timer;
	
	public PowerUp(Vector2D pos, Vector2D vel, double r){
		super(pos,vel,r);
		dead = false;
	}
	
	public abstract void pickUp(PlayerShip ship);
}
