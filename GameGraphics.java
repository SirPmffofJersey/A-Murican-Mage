package Graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Charactor.Player;
import Main.Game;
import Main.Media;
import Map.Room;

public class GameGraphics extends JPanel{
	private final int GAMEPLAY_SCREEN_WIDTH = 1024, GAMEPLAY_SCREEN_HEIGHT = 768;
	private Image gameInterface;
	private Image background;
	private Rectangle backgroundDim;
	private double scale;
	private Point origin;
	private Room currentRoom;
	private Player player;
	private Rectangle temp;
	
	public GameGraphics() {
		gameInterface = new ImageIcon(getClass().getResource("/PlayerInterfaceV1.jpg")).getImage();
		/*
		Rectangle r = Screen.getFrameDim();
		int w = GAMEPLAY_SCREEN_WIDTH;
		int h = GAMEPLAY_SCREEN_HEIGHT;
		double scale1 = r.getWidth()/w, scale2 = r.getHeight()/h;
		if(scale1 > scale2) {
			scale = scale2;
			origin = new Point(0, (int)Math.abs((h * scale1) - r.getHeight()) / 2);
		} else {
			scale = scale1;
			origin = new Point((int)Math.abs((w * scale2) - r.getWidth()) / 2, 0);
		}
		*/
		Rectangle r = Screen.getFrameDim();
		temp = null;
		int w = gameInterface.getWidth(this);
		int h = gameInterface.getHeight(this);
		double scale1 = r.getWidth()/w, scale2 = r.getHeight()/h;
		if(scale1 > scale2) {
			temp = new Rectangle(0, (int)Math.abs((h * scale1) - r.getHeight()) / 2 * -1, (int)r.getWidth(), (int)(h * scale1));
		} else {
			temp = new Rectangle((int)Math.abs((w * scale2) - r.getWidth()) / 2 * -1, 0, (int)(w * scale2), (int)r.getHeight());
		}
		background = new Media("city1", "image").getImage();
		backgroundDim = r = Media.getBackgroundDim(background, this);
		player = Game.getPlayer();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		currentRoom = Game.getMap().getRoom(Game.getMap().getPlayerRoom());
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(background, (int)backgroundDim.getX(), (int)backgroundDim.getY(), (int)backgroundDim.getWidth(), (int)backgroundDim.getHeight(), this);
		//g2.drawImage(gameInterface, (int)origin.getX(), (int)origin.getY(), (int)(gameInterface.getWidth(this) * scale), (int)(gameInterface.getHeight(this) * scale), this);
		g2.drawImage(gameInterface, (int)temp.getX(), (int)temp.getY(), (int)temp.getWidth(), (int)temp.getHeight(), this);
		drawRoom(currentRoom, g2);
	}
	
	private void drawRoom(Room room, Graphics2D g2) {
		
	}
}
