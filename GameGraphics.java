package Graphics;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameGraphics extends JPanel implements KeyListener{
	private final int GAMEPLAY_SCREEN_WIDTH = 1024, GAMEPLAY_SCREEN_HEIGHT = 768;
	private Image gameInterface;
	private Image background;
	private Rectangle backgroundDim;
	private Rectangle gameDim;
	private Rectangle roomDisDim;
	private Rectangle roomDim;
	private Player player;
	private JLabel[] labels;
	
	public GameGraphics() {
		gameInterface = new ImageIcon(getClass().getResource("/PlayerInterfaceV1.png")).getImage();
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
		roomDisDim = new Rectangle((int)(gameDim.getX() + gameDim.getWidth() * 0.00694444), (int)(gameDim.getY() + gameDim.getHeight() * 0.01851851), (int)(gameDim.getWidth() * 0.98611111), (int)(gameDim.getHeight() * 0.76851851));
		//setJComp();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(background, (int)backgroundDim.getX(), (int)backgroundDim.getY(), (int)backgroundDim.getWidth(), (int)backgroundDim.getHeight(), this);
		drawGame(g2);
		//g2.drawImage(gameInterface, (int)origin.getX(), (int)origin.getY(), (int)(gameInterface.getWidth(this) * scale), (int)(gameInterface.getHeight(this) * scale), this);
		g2.setColor(Color.RED);
		//g2.fillRect(400, 40, 293, 43);
		g2.fillRect((int)(gameDim.getX() + gameDim.getWidth() * .11111111), (int)(gameDim.getY() + gameDim.getHeight() * 0.03981481), (int)(gameDim.getWidth() * 0.20347222 * ((double)Game.getPlayer().getHealth()/Game.getPlayer().getMaxHealth())), (int)(gameDim.getHeight() * 0.03981481 * ((double)Game.getPlayer().getMana()/Game.getPlayer().getMaxMana())));
		g2.setColor(Color.BLUE);
		g2.fillRect((int)(gameDim.getX() + gameDim.getWidth() * .11111111), (int)(gameDim.getY() + gameDim.getHeight() * 0.08981482), (int)(gameDim.getWidth() * 0.20347222), (int)(gameDim.getHeight() * 0.03981481));
		g2.drawImage(gameInterface, (int)gameDim.getX(), (int)gameDim.getY(), (int)gameDim.getWidth(), (int)gameDim.getHeight(), this);
	}
	
	public void stop(Container c) {
		removeJComp(c);
	}
	
	public void start(Container c) {
		setJComp();
		addJComp(c);
	}
	
	public Rectangle getGameDim() {
		return gameDim;
	}
	
	private void drawGame(Graphics2D g2) {
		drawRoom(g2);
		drawCharacters(g2);
	}
	
	private void drawRoom(Graphics2D g2) {
		Room room = Game.getMap().getRoom(Game.getMap().getPlayerRoomLoc());
		Image image = new ImageIcon(getClass().getResource("/" + room.getImageName() + ".png")).getImage();
		int w = image.getWidth(this);
		int h = image.getHeight(this);
		double scale1 = roomDisDim.getWidth()/w, scale2 = roomDisDim.getHeight()/h;
		if(scale1 > scale2) {
			g2.drawImage(image, (int)(Math.abs((w * scale2) - roomDisDim.getWidth()) / 2 + roomDisDim.getX()), (int)roomDisDim.getY(), (int)(w * scale2), (int)roomDisDim.getHeight(), this);
			roomDim = new Rectangle((int)((Math.abs((w * scale2) - roomDisDim.getWidth()) / 2 + roomDisDim.getX()) + (w * (1/room.getDim().getWidth()))), (int)(roomDisDim.getY() + (h * (1/room.getDim().getHeight()))), (int)((w * scale2) - (roomDisDim.getWidth() * (2/w))), (int)(roomDisDim.getHeight() - (roomDisDim.getWidth() * (2/h))));
		} else {
			g2.drawImage(image, (int)roomDisDim.getX(), (int)(Math.abs((h * scale1) - roomDisDim.getHeight()) / 2 + roomDisDim.getY()), (int)roomDisDim.getWidth(), (int)(h * scale1), this);
		}
		//g2.drawRect((int)roomDim.getX(), (int)roomDim.getY(), (int)roomDim.getWidth(), (int)roomDim.getHeight());
	}
	
	private void drawCharacters(Graphics2D g2) {
		drawCharacter(player, g2);
		ArrayList<Enemies> e = Game.getEnemies();
		for(int i = 0; i < e.size(); i++)
			drawCharacter(e.get(i), g2);
	}
	
	private void drawCharacter(Charactor.Character c, Graphics2D g2) {
		Image image = new ImageIcon(getClass().getResource("/" + c.getImageName() + ".gif")).getImage();
		g2.drawImage(image, (int)(roomDim.getX() + c.getLocation().getX()), (int)(roomDim.getY() + c.getLocation().getY()), (int)(c.getDimensions().getWidth()), (int)(c.getDimensions().getHeight()), this);
	}
	
	private void setJComp() {
		labels = new JLabel[11];
		labels[0] = new JLabel("Level");
		labels[0].setText("" + player.getLevel());
		labels[0].setBounds((int)(gameDim.getWidth() * 0.03819444 + gameDim.getX()), (int)(gameDim.getHeight() * 0.06018518 + gameDim.getY()), (int)(gameDim.getWidth() * 0.03055555), (int)(gameDim.getHeight() * 0.04814814));
		labels[0].setHorizontalAlignment(SwingConstants.CENTER);
		labels[0].setVerticalAlignment(SwingConstants.CENTER);
		labels[0].setFont(new Font("Jokerman", Font.BOLD, 20));
		labels[0].setForeground(Color.MAGENTA);
		labels[1] = new JLabel("Health Percentage");
		labels[2] = new JLabel("Mana Percentage");
		labels[3] = null;
		labels[4] = null;
		labels[5] = null;
		labels[6] = null;
		labels[7] = null;
		labels[8] = null;
		labels[9] = null;
		labels[10] = null;
	}
	
	private void addJComp(Container c) {
		if(labels != null)
			for(JLabel l: labels)
				if(l != null)
					c.add(l);
	}
	
	private void removeJComp(Container c) {
		if(labels != null)
			for(JLabel l: labels)
				if(l != null)
					c.remove(l);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Hit Button");
		if(e.getKeyCode() == KeyEvent.VK_A) {
			System.out.println("Hit Button");
			Screen.setMenu("Pause");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
