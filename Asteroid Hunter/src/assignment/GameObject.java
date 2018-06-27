package assignment;

import utilities.Vector2D;

import java.awt.*;

import static assignment.Constants.*;

/**
 * Created by radobr on 17/02/2017.
 */
public abstract class GameObject {
    public Vector2D position;
    public Vector2D velocity;

    public boolean dead;
    public double radius;
    
    public Sprite sprite;

    public GameObject(Vector2D pos, Vector2D vel, double r){
        position = pos;
        velocity = vel;
        radius = r;
        dead = false;
    }

    public void update() {
        position.addScaled(velocity, DT);
        position.wrap(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    public boolean overlap(GameObject other){
    	if(this.position.dist(other.position) <= (this.radius+other.radius)){
    		return true;
    	}
    	return false;
    }
    public void collisionHandling(GameObject other) {
    	if (this.getClass() != other.getClass() && this.overlap(other)) {
    		this.hit();
    		other.hit();
    	}
    }

    public void hit(){
    	this.dead = true;
    }

    public abstract void draw(Graphics2D g);
}
