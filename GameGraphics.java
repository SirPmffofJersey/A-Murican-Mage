package Graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Charactor.Charactor;
import Charactor.Enemy;
import Charactor.Player;
import Main.Game;
import Main.Media;

public class GameGraphics extends JPanel{
	private final int GAMEPLAY_SCREEN_WIDTH = 1024, GAMEPLAY_SCREEN_HEIGHT = 768;
	private Image gameInterface;
	private Image background;
	private Rectangle backgroundDim;
	private Rectangle gameDim;
	private Player player;
	
	public GameGraphics() {
		gameInterface = new ImageIcon(getClass().getResource("/PlayerInterfaceV1.jpg")).getImage();
		//gameInterface = Media.changeWhiteToTransparent(Media.toBufferedImage(gameInterface));
		background = new Media("city1", "image").getImage();
		player = Game.getPlayer();
		Rectangle r = Screen.getFrameDim();
		int w = GAMEPLAY_SCREEN_WIDTH;
		int h = GAMEPLAY_SCREEN_HEIGHT;
		double scale1 = r.getWidth()/w, scale2 = r.getHeight()/h;
		if(scale1 > scale2) {
			gameDim = new Rectangle((int)Math.abs((w * scale2) - r.getWidth()) / 2, 0, (int)(w * scale2), (int)r.getHeight());
		} else {
			gameDim = new Rectangle(0, (int)Math.abs((h * scale1) - r.getHeight()) / 2, (int)r.getWidth(), (int)(h * scale1));
		}
		backgroundDim = Media.getBackgroundDim(background, this);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(background, (int)backgroundDim.getX(), (int)backgroundDim.getY(), (int)backgroundDim.getWidth(), (int)backgroundDim.getHeight(), this);
		drawGame(g2);
		//g2.drawImage(gameInterface, (int)origin.getX(), (int)origin.getY(), (int)(gameInterface.getWidth(this) * scale), (int)(gameInterface.getHeight(this) * scale), this);
		g2.drawImage(gameInterface, (int)gameDim.getX(), (int)gameDim.getY(), (int)gameDim.getWidth(), (int)gameDim.getHeight(), this);
	}
	
	private void drawGame(Graphics2D g2) {
		//drawRoom(g2);
		//drawCharactors(g2);
	}
	
	private void drawRoom(Graphics2D g2) {
		
	}
	
	private void drawCharactors(Graphics2D g2) {
		drawCharactor(player, g2);
		Enemy[] e = Game.getEnemies();
		for(int i = 0; i < e.length; i++)
			drawCharactor(e[i], g2);
	}
	
	private void drawCharactor(Charactor c, Graphics2D g2) {
		
	}
}
