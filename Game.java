package Main;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import Charactor.Enemies;
import Charactor.Player;
import Graphics.Screen;
import Item.Projectile;
import Item.Sword;
import Map.Map;

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
 
    
    
    public static void main(String[] args){
    	game = new Game();
    	player = new Player();
        screen = new Screen();
        timer = new Timer(100, game);
        map = new Map();
        enemies = new ArrayList<Enemies>();
        projectiles = new ArrayList<Projectile>(0);
        generateEnemies = true;
        timer.start();
    }

    public static void enterGame() {
        inGame = true;
        
    }

    public static void exitGame() {
        inGame = false;
    }

    public static boolean getGameState() {
        return inGame;
    }

    public void actionPerformed(ActionEvent a) {
        screen.repaint();
        if(inGame == true) {
        	if(player.getHealth() <= 0)
        		System.exit(0);
        	if(player.getAttackCount() > 0)
        		player.decrementAttackCount();
        	else if(player.getAttackCount() == 0)
        		player.stopAttack();
        	if(generateEnemies)
        		generateEnemies(map.getPlayerRoomLoc());
        	if(enemies != null)
        		for(int i = 0; i < enemies.size(); i++) {
        			if(enemies.get(i).isDead()) {
        				enemies.remove(enemies.get(i));
        				i--;
        			} else
        				enemies.get(i).moveTowardPlayer(player);
        				//enemies.get(i).move();
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

    public static Map getMap() {
        return map;
    }

    public static Player getPlayer() {
        return player;
    }
    
    public static ArrayList<Enemies> getEnemies() {
    	return enemies;
    }
    
    public static void generateEnemies() {generateEnemies = true;}
    
    private static void generateEnemies(int room){
    	generateEnemies = false;
        if(room == 0 || room == 2 || room == 4 || room == 8){
            enemyAmount = 4;
            for(int i =0; i < enemyAmount; i++){
                enemies.add(new Enemies("Police officer", 50, 0, 50, 0, 20, 20, 2, 1, new Rectangle((int)(Math.random()* 340 + 100), (int)(Math.random()* 340 + 100), 100, 100), new Sword("Dagger"), 1));
            }
        }
        
    }
}
