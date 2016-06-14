package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Game implements ActionListener{
    private static boolean inGame;
    private static Screen screen;
    private static Game game;
    private static Timer timer;
    private static Map map;
    private static Player player;
    private static int currentRoom;
    private static int enemyAmount;
    private static ArrayList<Enemies> enemies;
    private static ArrayList<Projectile> projectiles;
    
    
    public static void main(String[] args){
    	game = new Game();
    	player = new Player();
        screen = new Screen();
        timer = new Timer(100, game);
        map = new Map();
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
        	for(Enemies e: enemies) {
        		if(e.isDead())
        			enemies.remove(e);
        		else
        			e.moveTowardPlayer(player);
        	}
        	
        	for(Pojectile p: projectiles){
        	    if(p.toBeRemoved())
        	        projectiles.remove(p);
        	    else
        	        p.move();
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
    
    public static generateEnemies(int room){
        if(room == 0 || room == 2 || room == 4 || room == 8){
            enemyAmount = 4;
            for(int i =0; i < enemyAmount; i++){
                enemies.add( new Enemies("Police officer.gif", 50, 0, 50, 0, 20, 20, 2, 1, new Point((int)(Math.random()* 340 + 100), (int)(Math.random()* 340 + 100), new Rectangle(100, 100), new Sword("Dagger"), 4)
            }
        }
        
    }
    
    public static addProjectiles(Spell type, Point mouseLoc){
        projectiles.add(new Projectile(type, mouseLoc));
    }
    
    
}
