package assignment;

import utilities.SoundManager;
import utilities.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static assignment.Constants.FRAME_HEIGHT;
import static assignment.Constants.FRAME_WIDTH;

/**
 * Created by radobr on 20/01/2017.
 */
public class Asteroid extends GameObject {

    public static final int LARGE_RADIUS = 36;
    public static final int MEDIUM_RADIUS = 28;
    public static final int SMALL_RADIUS = 14;
    public static final double MAX_SPEED = 200;
	public static final double ROTATION_SPEED = 1;

    public List<Asteroid> spawnedAsteroids = new ArrayList<>();
    
    public PowerUp powerUp;

    public Asteroid(Vector2D pos, Vector2D vel, int radius) {
        super(pos,vel,radius);
        sprite = new Sprite(Sprite.ASTEROID1, pos, new Vector2D(vel).normalise(), radius*2, radius*2);
    }

	public static Asteroid makeRandomAsteroid() {
	    double rnd1 = Math.random();
        double rnd2 = Math.random();
        double rnd3 = Math.random();

        rnd1 *= rnd2 > 0.5? 1:-1;
        rnd2 *= rnd3 > 0.5? 1:-1;

        return new Asteroid(new Vector2D(rnd1*FRAME_WIDTH, rnd2*FRAME_HEIGHT),
                            new Vector2D(rnd1*MAX_SPEED, rnd2*MAX_SPEED),
                            LARGE_RADIUS);
    }

	public void hit(){
		SoundManager.asteroid();
		
		double rnd = Math.random();
		
		if(rnd<0.15){
			powerUp = new Shield(position); 
		}
		
		if(radius == LARGE_RADIUS){
			spawnedAsteroids.add(new Asteroid(new Vector2D(position),
								 			  new Vector2D(velocity).mult(-1), 
								 			  MEDIUM_RADIUS));
			
			spawnedAsteroids.add(new Asteroid(new Vector2D(position),
											  new Vector2D(velocity), 
											  MEDIUM_RADIUS));
		} 
		else if (radius == MEDIUM_RADIUS){
			spawnedAsteroids.add(new Asteroid(new Vector2D(position),
											  new Vector2D(velocity).mult(-1), 
											  SMALL_RADIUS));
			
			spawnedAsteroids.add(new Asteroid(new Vector2D(position),
											  new Vector2D(velocity), 
											  SMALL_RADIUS));
			
			spawnedAsteroids.add(new Asteroid(new Vector2D(position),
											  new Vector2D(velocity).rotate(Math.PI/2), 
											  SMALL_RADIUS));
		}

		super.hit();
	}
	
    public void update() {
        super.update();
    }

    public void draw(Graphics2D g) {
		sprite.rotateSprite(Math.toRadians(ROTATION_SPEED));
    	sprite.draw(g);
    }
}
