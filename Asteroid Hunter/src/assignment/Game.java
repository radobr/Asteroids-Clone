package assignment;

import utilities.JEasyFrameFull;
import utilities.SoundManager;

import java.util.ArrayList;
import java.util.List;

import static assignment.Constants.DELAY;

/**
 * Created by radobr on 20/01/2017.
 */
public class Game {

    public static final int N_INITIAL_ASTEROIDS = 5;
    
    public boolean paused, over;
    
    public List<GameObject> gameObjects;
    public PlayerShip playerShip;

    private Keys ctrl;
    private int score;
    private int level;
    private int bonusLifeScore;

    public Game() {
        gameObjects = new ArrayList<GameObject>();
        for (int i = 0; i < N_INITIAL_ASTEROIDS; i++) {
            gameObjects.add(Asteroid.makeRandomAsteroid());
        }

        score = 0;
        level = 1;
        bonusLifeScore = 5000;

        ctrl = new Keys();
        playerShip = new PlayerShip(ctrl);
        gameObjects.add(playerShip);
        
        over = false;
        paused = false;
    }

    public static void main(String[] args) {
        Game game = new Game();
        View view = new View(game);
        SoundManager.themeSong();
        //MainMenu menu = new MainMenu(); TODO: Might use it later
        JEasyFrameFull frame = new JEasyFrameFull(view);
        frame.addKeyListener(game.ctrl);
        while (true) {
        	if(!game.over){
        		game.update();
	            view.repaint();	
        	} else {
        		break;
        	}
        	
            try{
            Thread.sleep(DELAY);
            }
            catch (InterruptedException e){
                System.out.println(e);
            }
        }
        
        System.exit(0);
    }

    private void addScore(int points){
        score += points;
    }

    public int getScore(){
        return score;
    }
    
    public int getLevel(){
    	return level;
    }
    
    private void advanceLevel(){
    	int levelUpFactor = level*2;
        for (int i = 0; i < N_INITIAL_ASTEROIDS+levelUpFactor; i++) {
            gameObjects.add(Asteroid.makeRandomAsteroid());
        }
        level++;
    }

    private void update() {
        List<GameObject> alive = new ArrayList<>();
        boolean noMoreAsteroids = true;
        
        for(int i=0; i<gameObjects.size()-1; i++){
        	for(int j=i+1;j<gameObjects.size();j++){
        		if(gameObjects.get(j) instanceof PlayerShip){
    				//Skip collision detection for playerShip if it's immune
        			if(((PlayerShip) gameObjects.get(j)).immune){
        				continue;
        			}
        		}
        		
        		if(gameObjects.get(j) instanceof PowerUp){
        			continue;
        		}
        		
        		gameObjects.get(i).collisionHandling(gameObjects.get(j));
        	}
        }
        
        for (GameObject object: gameObjects) {
        	
        	//Add bullet
            if(object instanceof PlayerShip){
                if(( (PlayerShip)object).dead){
                    this.over = true;
                    break;
                }
                if( ( (PlayerShip)object ).bullet != null ){
                    alive.add(((PlayerShip) object).bullet);
                    ((PlayerShip) object).bullet = null;
                }
            }

            //Add lives if score condition is met
            if(score != 0 && score>=bonusLifeScore){
                playerShip.livesLeft++;
                SoundManager.extraShip();             
                bonusLifeScore+=5000;
            }
            
            //Add medium and small asteroids + increment score
            if(object instanceof Asteroid){
            	noMoreAsteroids = false;
            	if( !((Asteroid)object).spawnedAsteroids.isEmpty() ){
            	    addScore(((Asteroid)object).radius == Asteroid.LARGE_RADIUS? 100:
                             ((Asteroid)object).radius == Asteroid.MEDIUM_RADIUS? 250: 500);
            		alive.addAll(((Asteroid)object).spawnedAsteroids);
            	}
            	
            	if( ((Asteroid)object).powerUp != null){
            		alive.add(((Asteroid)object).powerUp);
            		((Asteroid)object).powerUp = null;
            	}
            }
            
            if(!object.dead){
                alive.add(object);
            }
        }
        synchronized (Game.class) {
            gameObjects.clear();
            if(noMoreAsteroids){
            	advanceLevel();
            }
            gameObjects.addAll(alive);
        }
        for(GameObject object : gameObjects){
            object.update();
        }
    }
}
