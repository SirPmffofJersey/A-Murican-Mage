package Main;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.Timer;

import Charactor.Crunch;
import Charactor.Cultists;
import Charactor.Dump;
import Charactor.Enemies;
import Charactor.Player;
import Charactor.RiotOfficer;
import Graphics.Screen;
import Item.Projectile;
import Item.Sword;
import Map.Map;

/**
 * <h1> Game Class: </h1>
 * The Game class is the main class for the program, 
 * it holds the main method and static references to
 * the other main aspects of the program
 * 
 * @author Will Roberts
 * @version 4
 * @since 6/10/16
 */
public class Game implements ActionListener{
    private static boolean inGame;
    private static boolean generateEnemies;
    private static Screen screen;
    private static Game game;
    private static Timer timer;
    private static Map map;
    private static Player player;
    private static int enemyAmount;
    private static ArrayList<Enemies> enemies;
    private static ArrayList<Projectile> projectiles;
 
    
    /**
     * Game's constructor.
     * Creates a player, screen and map,
     * as wellas two ArrayLists, enemies and projectiles,
     * starts the game's timer, and set generateEnemies to true
     */
    public Game() {
    	player = new Player();
        screen = new Screen();
        timer = new Timer(100, this);
        map = new Map();
        enemies = new ArrayList<Enemies>();
        projectiles = new ArrayList<Projectile>(0);
        generateEnemies = true;
        timer.start();
    }
    
    /**
     * Game's constructor.
     * Creates a player, screen and map,
     * as wellas two ArrayLists, enemies and projectiles,
     * starts the game's timer, and set generateEnemies to true
     */
    public void actionPerformed(ActionEvent a) {
        screen.repaint();
        if(inGame) {
        	if(player.getHealth() <= 0)
        		screen.setMenu("Death");
        	player.regenerate();
        	if(player.getAttackCount() > 0)
        		player.decrementAttackCount();
        	else if(player.getAttackCount() == 0)
        		player.stopAttack();
        	if(player.getEquippedSpell() != null && player.getEquippedSpell().getCoolDown() > 0)
        		player.getEquippedSpell().coolDown();
        	if(generateEnemies)
        		generateEnemies(map.getPlayerRoomLoc());
        	if(enemies != null)
        		for(int i = 0; i < enemies.size(); i++) {
        			if(enemies.get(i).isDead()) {
        				enemies.remove(enemies.get(i));
        				i--;
        			} else {
        				enemies.get(i).moveTowardPlayer(player);
        				if(enemies.get(i).canAttack(player))
        					enemies.get(i).attack(player);
        				//enemies.get(i).move();
        			}
        		}
        	
        	for(int i = 0; i < projectiles.size(); i++){
        	    if(projectiles.get(i).toBeRemoved()) {
        	        projectiles.remove(i);
        	    	i--;
        	    } else
        	        projectiles.get(i).move();
        	}
        	if(enemies.size() <= 0) {
        		map.getCurrentRoom().enableDoors();
        	}
        }
    }
    

    public static void addProjectile(Projectile p) {
    	projectiles.add(p);
    }

    public static void enterGame() {
        inGame = true;
    }
  
    public static void exitGame() {
        inGame = false;
    }
    
    //Accessor Methods
    public static Rectangle getBackgroundDim(Image i, ImageObserver io) {
		Rectangle r = Screen.getFrameDim();
		Rectangle temp = null;
		int w = i.getWidth(io);
		int h = i.getHeight(io);
		double scale1 = r.getWidth()/w, scale2 = r.getHeight()/h;
		if(scale1 > scale2) {
			temp = new Rectangle(0, (int)Math.abs((h * scale1) - r.getHeight()) / 2 * -1, (int)r.getWidth(), (int)(h * scale1));
		} else {
			temp = new Rectangle((int)Math.abs((w * scale2) - r.getWidth()) / 2 * -1, 0, (int)(w * scale2), (int)r.getHeight());
		}
		return temp;
	}
    
    public static ArrayList<Enemies> getEnemies() {
    	return enemies;
    }
    
    public static Game getGame() {return game;}
    
    public static boolean getGameState() {
        return inGame;
    }
    
     public static Map getMap() {
        return map;
    }
   
    public static Player getPlayer() {
        return player;
    }

    public static ArrayList<Projectile> getProjectiles() {
    	return projectiles;
    }
    
    public static Screen getScreen() {
    	return screen;
    }
    
    public static void generateEnemies() {generateEnemies = true;}
    
    /**
     * Adds different enemies to the enemies ArrayList based on the room 
     */
    private static void generateEnemies(int room){
    	generateEnemies = false;
        if(room == 2 || room == 4){
            enemyAmount = 3;
            switch(room){
            
            case 2:
            	for(int i =0; i < enemyAmount; i++){
                    enemies.add(new Enemies("Police officer", 50, 50, 50, 50, 5, 20, 2, 1, new Rectangle((int)(Math.random()* 400 + 100), (int)(Math.random()* 400 + 100), 100, 100), new Sword(""), 1));
                }
            	break;
            case 4:
            	for(int i =0; i < enemyAmount; i++){
                    enemies.add(new Enemies("Dog", 30, 30, 30, 30, 15, 5, 3, 1, new Rectangle((int)(Math.random() * 400 + 100), (int)(Math.random() * 400 + 100), 100, 100), new Sword(""), 1));
                }
            	break;
            }
        }
        
        if(room == 1 || room == 6){
            enemyAmount = 5;
            switch(room){
            
            case 1:
            	for(int i =0; i < enemyAmount; i++){
                    enemies.add(new Enemies("Police officer", 50, 50, 50, 50, 5, 20, 2, 1, new Rectangle((int)(Math.random()* 1200 + 100), (int)(Math.random()* 200 + 100), 100, 100), new Sword(""), 1));
                }
            	break;
            case 6:
            	for(int i =0; i < enemyAmount; i++){
                    enemies.add(new Enemies("Secret Service", 50, 50, 50, 50, 8, 25, 2, 1, new Rectangle((int)(Math.random()* 1200 + 100), (int)(Math.random()* 200 + 100), 100, 100), new Sword(""), 1));
                }
            	break;
            }
        }
        
        if(room == 3 || room == 7 || room == 8){
            switch(room){
            
            case 3:
            	enemyAmount = 3;
            	for(int i =0; i < enemyAmount; i++){
                    enemies.add(new Enemies("Police officer", 50, 50, 50, 50, 5, 20, 2, 1, new Rectangle((int)(Math.random()* 700 + 100), (int)(Math.random()* 700 + 100), 100, 100), new Sword(""), 1));
                }
            	enemyAmount = 2;
            	for(int i =0; i < enemyAmount; i++){
                    enemies.add(new RiotOfficer("Riot Officer", 60, 60, 60, 60, 5, 25, 1, 1, new Rectangle((int)(Math.random()* 700 + 100), (int)(Math.random()* 700 + 100), 100, 100), new Sword(""), 1));
                }
            	break;
            case 7:
            	enemyAmount = 3;
            	for(int i =0; i < enemyAmount; i++){
            		enemies.add(new Enemies("Secret Service", 50, 50, 50, 50, 8, 25, 2, 1, new Rectangle((int)(Math.random()* 700 + 100), (int)(Math.random()* 700 + 100), 100, 100), new Sword(""), 1));
                }
            	enemyAmount = 2;
            	for(int i =0; i < enemyAmount; i++){
                    enemies.add(new RiotOfficer("Riot Officer", 60, 60, 60, 60, 5, 25, 1, 1, new Rectangle((int)(Math.random()* 700 + 100), (int)(Math.random()* 700 + 100), 100, 100), new Sword(""), 1));
                }
            	break;
            case 8:
            	enemyAmount = 3;
            	for(int i =0; i < enemyAmount; i++){
            		enemies.add(new Enemies("Dog", 30, 30, 30, 30, 15, 5, 3, 1, new Rectangle((int)(Math.random()* 700 + 100), (int)(Math.random()* 700 + 100), 100, 100), new Sword(""), 1));
                }
            	enemyAmount = 3;
            	for(int i =0; i < enemyAmount; i++){
                    enemies.add(new Cultists("Cultist", 10, 10, 10, 10, 0, 25, 2, 1, new Rectangle((int)(Math.random()* 700 + 100), (int)(Math.random()* 700 + 100), 100, 100), new Sword(""), 1));
                }
            	break;
            }
        }
        
        if(room == 5){
        	Crunch c = new Crunch("Captain Crunchstie", 100, 100, 100, 100, 20, 25, 1, 1, new Rectangle(1200, 400, 200, 200), new Sword(""), 1);
        	enemies.add(c);
        	c.spawnEnemies(enemies, c);
        }
        if(room == 9) {
        	Dump d = new Dump("TronaldDump", 150, 150, 150, 150, 30, 30, 1, 1, new Rectangle(600, 200, 300, 300), new Sword(""), 1);
        	enemies.add(d);
        	d.spawnEnemies(enemies, d);
        }
    }
    
    /**
     * Stops timer and creates a new Game.
     */
    public static void restartGame() {
    	timer.stop();
    	game = new Game();
    }
}
