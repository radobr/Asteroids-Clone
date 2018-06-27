package assignment;

import utilities.SoundManager;
import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;

import static assignment.Constants.*;

/**
 * Created by radobr on 03/02/2017.
 */
public class PlayerShip extends Ship {
//    public static final int RADIUS = 15;
//
//    // rotation velocity in radians per second
//    public static final double STEER_RATE = 2* Math.PI;
//
//    // acceleration when thrust is applied
//    public static final double MAG_ACC = 400;
//
//    public static final int IMMUNE_TIME = 2500;
//
//    public static final int SHOOTING_COOLDOWN = 600;
//
//    // constant speed loss factor
//    public static final double DRAG = 0.01;

    public static final Color COLOR = Color.cyan;

    public static final double DRAWING_SCALE = 60;
    
    private static final float OPACITY_OFFSET = 0.05f;
    
    // direction in which the nose of the playerShip is pointing
    // this will be the direction in which thrust is applied
    // it is a unit vector representing the angle by which the playerShip has rotated
    public Vector2D direction;

    public int livesLeft;
    public boolean immune;
    public Timer immunityTimer, shootingTimer;
    public Bullet bullet;

    // controller which provides an Action object in each frame
    private Controller ctrl;
    private float opacity;
    private boolean transparent;
    
    public PlayerShip(Controller ctrl) {
        super(new Vector2D(FRAME_WIDTH/2, FRAME_HEIGHT/2),
              new Vector2D(), RADIUS);

        this.ctrl = ctrl;
        direction = new Vector2D(0,-1);

        thrusting = false;
        bullet = null;

        livesLeft = 3;

        immune = true;
        immunityTimer = new Timer(IMMUNE_TIME, ev->{immune=false; opacity = 1;});
        immunityTimer.start();

        ableToShoot = true;
        shootingTimer = new Timer(SHOOTING_COOLDOWN, ev->{ableToShoot=true;});
        shootingTimer.start();

        opacity = 1f;
        transparent = false;
        sprite = new Sprite(Sprite.SHIP,position,direction,DRAWING_SCALE,DRAWING_SCALE);
        shipThrusting = new Sprite(Sprite.SHIP_THRUSTING,position,direction,DRAWING_SCALE,DRAWING_SCALE);

    }

    private void makeBullet(){
        Vector2D pos = new Vector2D(position).addScaled(direction, RADIUS+ Bullet.RADIUS);
        Vector2D vel = new Vector2D(direction).mult(Bullet.SPEED);
        bullet = new Bullet(pos,vel);
        SoundManager.fire();
    }

    public void hit(){
        livesLeft--;
        immune = true;
        immunityTimer.restart();
        
        SoundManager.shipHit();
    	
        if(livesLeft == 0){
        	SoundManager.shipDeath();
            super.hit(); //Kill playerShip
        }
    }

    public void update() {
        super.update();

        //Turn playerShip
        direction.rotate(ctrl.action().turn*STEER_RATE*DT);

        //Update velocity
        velocity.addScaled(direction, MAG_ACC*DT*ctrl.action().thrust);
        velocity = velocity.mult(1-DRAG);

        //Update thrusting
        thrusting = ctrl.action().thrust == 1;
        
        if(immune){
        	if(transparent){
        		opacity += OPACITY_OFFSET;
        		if(opacity > 0.9f){
        			transparent = false;
        		}
        	} else {
        		opacity -= OPACITY_OFFSET;
        		if(opacity < 0.2f){
        			transparent = true;
        		}
        	}
        }

        if (ctrl.action().shoot && ableToShoot) {
            makeBullet();
            ableToShoot = false;
            shootingTimer.restart();
            ctrl.action().shoot = false;
        }
    }
    public void draw(Graphics2D g) {
    	
    	//Flicker image
    	try{
    		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
    	} catch (IllegalArgumentException e){
    		System.out.println("Why does this alpha value keep on getting out of range?!");
    	}
    	
        if (thrusting) {
        	shipThrusting.draw(g);
        } else {
        	sprite.draw(g);
        }
        
        //Set alpha back to original value 
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
