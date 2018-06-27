package assignment;

import utilities.Vector2D;

/**
 * Created by radobr on 23/03/2017.
 */
public abstract class Ship extends GameObject{
	
    public static final int RADIUS = 15;

    // rotation velocity in radians per second
    public static final double STEER_RATE = 2* Math.PI;

    // acceleration when thrust is applied
    public static final double MAG_ACC = 400;

    public static final int IMMUNE_TIME = 2500;

    public static final int SHOOTING_COOLDOWN = 600;

    // constant speed loss factor
    public static final double DRAG = 0.01;
    
    //TODO: finish with the variables and implementing it
    
    public boolean thrusting, ableToShoot;
    
    public Sprite shipThrusting;
    
	public Ship(Vector2D pos, Vector2D vel, double r){
		super(pos,vel,r);
	}
}
