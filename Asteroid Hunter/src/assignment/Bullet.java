package assignment;

import utilities.Vector2D;

import javax.swing.*;
import java.awt.*;

/**
 * Created by radobr on 17/02/2017.
 */
public class Bullet extends GameObject {

    public static final double SPEED = 800;
    public static final int RADIUS = 8;
    public static final Color COLOUR = Color.ORANGE;
    public static final int LIFESPAN = 1800;

    public Timer timer;

    private Vector2D direction;
    
    //TODO: Might add sound in the constructor
    public Bullet(Vector2D pos, Vector2D vel){
        super(pos,vel, RADIUS);
        direction = new Vector2D(vel).normalise();
        timer = new Timer(LIFESPAN, ev->{ dead=true;});
        timer.start();
    }

    public void update(){
        super.update();
    }
    public void draw(Graphics2D g){
        g.setColor(COLOUR);
        g.drawLine((int)position.x, (int)position.y,
                   (int)(position.x + RADIUS*direction.x),
                   (int)(position.y + RADIUS*direction.y));
    }
}
