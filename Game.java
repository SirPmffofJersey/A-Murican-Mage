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
    private static ArrayList<Enemies> enemies;
 
    
    
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
        	/*
        	for(Enemies e: enemies) {
        		if(e.isDead())
        			enemies.remove(e);
        		else
        			e.moveTowardPlayer(player);
        	}
        	*/
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
}
