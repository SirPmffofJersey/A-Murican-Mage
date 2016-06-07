package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Charactor.Player;
import Graphics.Screen;
import Map.Map;

public class Game implements ActionListener{
	private static boolean inGame;
	private static Screen screen;
	private static Game game;
	private static Timer timer;
	private static Map map;
	private static Player player;
	
	public static void main(String[] args){
		screen = new Screen();
		game = new Game();
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
	
	public void actionPerformed(ActionEvent e) {
		screen.repaint();
	}
	
	public static Map getMap() {
		return map;
	}
	
	public static Player getPlayer() {
		return player;
	}
}
