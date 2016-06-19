package Graphics;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Charactor.Enemies;
import Charactor.Player;
import Item.Projectile;
import Main.Game;
import Map.Obstacle;
import Map.Room;

public class GameGraphics extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 420L;
	private final int GAMEPLAY_SCREEN_WIDTH = 1024, GAMEPLAY_SCREEN_HEIGHT = 768;
	private Image gameInterface;
	private Image background;
	private Rectangle backgroundDim;
	private Rectangle gameDim;
	private Rectangle roomDisDim;
	private Point innerRoom;
	private double centiunitToPixel;
	private Player player;
	private JLabel[] labels;
	private ArrayList<String> strings;
	private int stringCount;
	
	public GameGraphics() {
		gameInterface = new ImageIcon(getClass().getResource("/PlayerInterfaceV1.png")).getImage();
		//gameInterface = Media.changeWhiteToTransparent(Media.toBufferedImage(gameInterface));
		background = new ImageIcon(getClass().getResource("/city1.png")).getImage();
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
		backgroundDim = Game.getBackgroundDim(background, this);
		roomDisDim = new Rectangle((int)(gameDim.getX() + gameDim.getWidth() * 0.00694444), (int)(gameDim.getY() + gameDim.getHeight() * 0.01851851), (int)(gameDim.getWidth() * 0.98611111), (int)(gameDim.getHeight() * 0.76851851));
		strings = new ArrayList<String>(10);
		strings.add("You started a Game");
		stringCount = 2000;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(background, (int)backgroundDim.getX(), (int)backgroundDim.getY(), (int)backgroundDim.getWidth(), (int)backgroundDim.getHeight(), this);
		drawGame(g2);
		//g2.drawImage(gameInterface, (int)origin.getX(), (int)origin.getY(), (int)(gameInterface.getWidth(this) * scale), (int)(gameInterface.getHeight(this) * scale), this);
		g2.setColor(Color.RED);
		//g2.fillRect(400, 40, 293, 43);
		g2.fillRect((int)(gameDim.getX() + gameDim.getWidth() * .11111111), (int)(gameDim.getY() + gameDim.getHeight() * 0.03981481), (int)(gameDim.getWidth() * 0.20347222 * ((double)Game.getPlayer().getHealth()/Game.getPlayer().getMaxHealth())), (int)(gameDim.getHeight() * 0.03981481));
		g2.setColor(Color.BLUE);
		updateLabels();
		g2.fillRect((int)(gameDim.getX() + gameDim.getWidth() * .11111111), (int)(gameDim.getY() + gameDim.getHeight() * 0.08981482), (int)(gameDim.getWidth() * 0.20347222 * ((double)Game.getPlayer().getMana()/Game.getPlayer().getMaxMana())), (int)(gameDim.getHeight() * 0.03981481));
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
		drawProjectiles(g2);
	}
	
	private void drawRoom(Graphics2D g2) {
		Room room = Game.getMap().getCurrentRoom();
		Image image = new ImageIcon(getClass().getResource("/" + room.getImageName() + ".png")).getImage();
		int w = image.getWidth(this);
		int h = image.getHeight(this);
		double scale1 = roomDisDim.getWidth()/w, scale2 = roomDisDim.getHeight()/h;
		if(scale1 > scale2) {
			g2.drawImage(image, (int)(Math.abs((w * scale2) - roomDisDim.getWidth()) / 2 + roomDisDim.getX()), (int)roomDisDim.getY(), (int)(w * scale2), (int)roomDisDim.getHeight(), this);
			//innerRoom = new Point((int)((Math.abs((w * scale2) - roomDisDim.getWidth()) / 2 + roomDisDim.getX()) + (w * (100/(room.getDim().getWidth() + 200)))), (int)(roomDisDim.getY() + (h * (100/(room.getDim().getHeight() + 200)))));
			innerRoom = new Point((int)((Math.abs((w * scale2) - roomDisDim.getWidth()) / 2 + roomDisDim.getX()) + ((w * scale2) * (100/(room.getDim().getWidth() + 200)))), (int)(roomDisDim.getY() + (roomDisDim.getHeight() * (100/(room.getDim().getHeight() + 200)))));
			centiunitToPixel = (w * scale2) / (room.getDim().getWidth() + 200);
		} else {
			g2.drawImage(image, (int)roomDisDim.getX(), (int)(Math.abs((h * scale1) - roomDisDim.getHeight()) / 2 + roomDisDim.getY()), (int)roomDisDim.getWidth(), (int)(h * scale1), this);
			innerRoom = new Point((int)(roomDisDim.getX()  + (roomDisDim.getWidth() * (100/(room.getDim().getWidth() + 200)))), (int)((Math.abs((h * scale1) - roomDisDim.getHeight()) / 2 + roomDisDim.getY()) + ((h * scale1) * (100/(room.getDim().getHeight() + 200)))));
			centiunitToPixel = (h * scale1) / (room.getDim().getHeight() + 200);
		}
		for(int i = 0; i < Game.getMap().getCurrentRoom().getNumberOfObstacles(); i++)
			drawObstacle(Game.getMap().getCurrentRoom().getObstacle(i), g2);
		//g2.drawRect((int)roomDim.getX(), (int)roomDim.getY(), (int)roomDim.getWidth(), (int)roomDim.getHeight());
	}
	
	private void drawObstacle(Obstacle o, Graphics2D g2) {
		g2.drawImage(o.getImage(), (int)(innerRoom.getX() + (o.getDimensions().getX() * centiunitToPixel)), (int)(innerRoom.getY() + (o.getDimensions().getY() * centiunitToPixel)), (int)(o.getDimensions().getWidth() * centiunitToPixel), (int)(o.getDimensions().getHeight() * centiunitToPixel), this);
		//g2.drawRect((int)(innerRoom.getX() + (o.getDimensions().getX() * centiunitToPixel)), (int)(innerRoom.getY() + (o.getDimensions().getY() * centiunitToPixel)), (int)(o.getDimensions().getWidth() * centiunitToPixel), (int)(o.getDimensions().getHeight() * centiunitToPixel));
	}
	
	private void drawCharacters(Graphics2D g2) {
		drawCharacter(player, g2);
		if(player.getAttackCount() > 0){
			g2.drawImage(player.getSword().getImage(), (int)(innerRoom.getX() + player.getSword().getDim().getX() * centiunitToPixel), (int)(innerRoom.getY() + player.getSword().getDim().getY() * centiunitToPixel), (int)(player.getSword().getDim().getWidth() * centiunitToPixel), (int)(player.getSword().getDim().getHeight() * centiunitToPixel), this);
		}
		ArrayList<Enemies> e = Game.getEnemies();
		for(int i = 0; i < e.size(); i++) {
			drawCharacter(e.get(i), g2);
		}
	}
	
	private void drawCharacter(Charactor.Character c, Graphics2D g2) {
		Image image = c.getImage();
		g2.drawImage(image, (int)(innerRoom.getX() + (c.getDimensions().getX() * centiunitToPixel)), (int)(innerRoom.getY() + (c.getDimensions().getY() * centiunitToPixel)), (int)(c.getDimensions().getWidth() * centiunitToPixel), (int)(c.getDimensions().getHeight() * centiunitToPixel), this);
	}
	
	private void drawProjectiles(Graphics2D g2) {
		ArrayList<Projectile> p = Game.getProjectiles();
		for(int i = 0; i < p.size(); i++) {
			drawProjectile(p.get(i), g2);
		}
	}
	
	private void drawProjectile(Projectile p, Graphics2D g2) {
		g2.drawImage(p.getImage(), (int)(innerRoom.getX() + (p.getDim().getX() * centiunitToPixel)), (int)(innerRoom.getY() + (p.getDim().getY() * centiunitToPixel)), (int)(p.getDim().getWidth() * centiunitToPixel), (int)(p.getDim().getHeight() * centiunitToPixel), this);
	}
	
	private void setJComp() {
		labels = new JLabel[12];
		//
		labels[0] = new JLabel("Level");
		labels[0].setText("" + player.getLevel());
		labels[0].setBounds((int)(gameDim.getWidth() * 0.03819444 + gameDim.getX()), (int)(gameDim.getHeight() * 0.06018518 + gameDim.getY()), (int)(gameDim.getWidth() * 0.03055555), (int)(gameDim.getHeight() * 0.04814814));
		labels[0].setHorizontalAlignment(SwingConstants.CENTER);
		labels[0].setVerticalAlignment(SwingConstants.CENTER);
		labels[0].setFont(new Font("Jokerman", Font.BOLD, 20));
		labels[0].setForeground(Color.YELLOW);
		//
		labels[1] = new JLabel("Health Percentage");
		labels[1].setText("" + (int)((double)player.getHealth() / player.getMaxHealth() * 100) + "%");
		labels[1].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .31458333 + 20), (int)(gameDim.getY() + gameDim.getHeight() * 0.03981481), 100, (int)(gameDim.getHeight() * 0.03981481));
		labels[1].setHorizontalAlignment(SwingConstants.LEFT);
		labels[1].setVerticalAlignment(SwingConstants.CENTER);
		labels[1].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[1].setForeground(Color.BLACK);
		//
		labels[2] = new JLabel("Mana Percentage");
		labels[2].setText("" + (int)((double)player.getMana() / player.getMaxMana() * 100) + "%");
		labels[2].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .31458333 + 20), (int)(gameDim.getY() + gameDim.getHeight() * 0.08981482), 100, (int)(gameDim.getHeight() * 0.03981481));
		labels[2].setHorizontalAlignment(SwingConstants.LEFT);
		labels[2].setVerticalAlignment(SwingConstants.CENTER);
		labels[2].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[2].setForeground(Color.BLACK);
		//
		labels[3] = new JLabel("Selected Sword");
		labels[3].setText("" + player.getSword().getName());
		labels[3].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .03472222), (int)(gameDim.getY() + gameDim.getHeight() * 0.86666666), (int)(gameDim.getWidth() * 0.15277777), (int)(gameDim.getHeight() * 0.03703703));
		labels[3].setHorizontalAlignment(SwingConstants.LEFT);
		labels[3].setVerticalAlignment(SwingConstants.CENTER);
		labels[3].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[3].setForeground(Color.BLACK);
		//
		labels[4] = new JLabel("Selected Consumable");
		labels[4].setText("" + player.getEquippedPotionName() + " Potion  " + "(" + player.getEquippedPotion().getQuantity() + ")");
		labels[4].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .03472222), (int)(gameDim.getY() + gameDim.getHeight() * 0.93981481), (int)(gameDim.getWidth() * 0.15277777), (int)(gameDim.getHeight() * 0.03703703));
		labels[4].setHorizontalAlignment(SwingConstants.LEFT);
		labels[4].setVerticalAlignment(SwingConstants.CENTER);
		labels[4].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[4].setForeground(Color.BLACK);
		//
		labels[5] = new JLabel("Fire Lobber");
		labels[5].setText("Locked");
		if(true)
			labels[5].setText("Fire Lobber");
		labels[5].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .26180555), (int)(gameDim.getY() + gameDim.getHeight() * 0.87407407), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		labels[5].setHorizontalAlignment(SwingConstants.LEFT);
		labels[5].setVerticalAlignment(SwingConstants.CENTER);
		labels[5].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[5].setForeground(Color.BLACK);
		//
		labels[6] = new JLabel("Lightning Strike");
		labels[6].setText("Locked");
		if(true)
			labels[6].setText("Lightning Strike");
		labels[6].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .26180555), (int)(gameDim.getY() + gameDim.getHeight() * 0.90925925), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		labels[6].setHorizontalAlignment(SwingConstants.LEFT);
		labels[6].setVerticalAlignment(SwingConstants.CENTER);
		labels[6].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[6].setForeground(Color.BLACK);
		//
		labels[7] = new JLabel("\"Magic\" Powder");
		labels[7].setText("Locked");
		if(true)
			labels[7].setText("\"Magic\" Powder");
		labels[7].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .26180555), (int)(gameDim.getY() + gameDim.getHeight() * 0.94259259), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		labels[7].setHorizontalAlignment(SwingConstants.LEFT);
		labels[7].setVerticalAlignment(SwingConstants.CENTER);
		labels[7].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[7].setForeground(Color.BLACK);
		//
		labels[8] = new JLabel("Armor Plated Armor");
		labels[8].setText("locked");
		if(true)
			labels[8].setText("Armor Plated Armor");
		labels[8].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .40138888), (int)(gameDim.getY() + gameDim.getHeight() * 0.87407407), (int)(gameDim.getWidth() * 0.22222222), (int)(gameDim.getHeight() * 0.01851851));
		labels[8].setHorizontalAlignment(SwingConstants.LEFT);
		labels[8].setVerticalAlignment(SwingConstants.CENTER);
		labels[8].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[8].setForeground(Color.BLACK);
		//
		labels[9] = new JLabel("Enchanted Steel");
		labels[9].setText("Locked");
		if(true)
			labels[9].setText("Enchanted Steel");
		labels[9].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .40138888), (int)(gameDim.getY() + gameDim.getHeight() * 0.90925925), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		labels[9].setHorizontalAlignment(SwingConstants.LEFT);
		labels[9].setVerticalAlignment(SwingConstants.CENTER);
		labels[9].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[9].setForeground(Color.BLACK);
		//
		labels[10] = new JLabel("Mind Control");
		labels[10].setText("Locked");
		if(true)
			labels[10].setText("Mind Control");
		labels[10].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .40138888), (int)(gameDim.getY() + gameDim.getHeight() * 0.94259259), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		labels[10].setHorizontalAlignment(SwingConstants.LEFT);
		labels[10].setVerticalAlignment(SwingConstants.CENTER);
		labels[10].setFont(new Font("Arial", Font.PLAIN, 20));
		labels[10].setForeground(Color.BLACK);
		//
		labels[11] = new JLabel("Strings");
		if(!strings.isEmpty())
			labels[11].setText(strings.get(0));
		labels[11].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .38680555), (int)(gameDim.getY() + gameDim.getHeight() * 0.03981481), (int)(gameDim.getWidth() * 0.60000000), (int)(gameDim.getHeight() * 0.12592592));
		labels[11].setHorizontalAlignment(SwingConstants.LEFT);
		labels[11].setVerticalAlignment(SwingConstants.CENTER);
		labels[11].setFont(new Font("Joker", Font.PLAIN, 40));
		labels[11].setForeground(Color.GREEN);
	}
	
	private void updateLabels() {
		labels[0].setText("" + player.getLevel());
		labels[0].setBounds((int)(gameDim.getWidth() * 0.03819444 + gameDim.getX()), (int)(gameDim.getHeight() * 0.06018518 + gameDim.getY()), (int)(gameDim.getWidth() * 0.03055555), (int)(gameDim.getHeight() * 0.04814814));
		labels[1].setText("" + (int)((double)player.getHealth() / player.getMaxHealth() * 100) + "%");
		labels[1].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .31458333 + 20), (int)(gameDim.getY() + gameDim.getHeight() * 0.03981481), 100, (int)(gameDim.getHeight() * 0.03981481));
		labels[2].setText("" + (int)((double)player.getMana() / player.getMaxMana() * 100) + "%");
		labels[2].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .31458333 + 20), (int)(gameDim.getY() + gameDim.getHeight() * 0.08981482), 100, (int)(gameDim.getHeight() * 0.03981481));
		labels[3].setText("" + player.getSword().getName());
		labels[3].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .03472222), (int)(gameDim.getY() + gameDim.getHeight() * 0.86666666), (int)(gameDim.getWidth() * 0.15277777), (int)(gameDim.getHeight() * 0.03703703));
		labels[4].setText("" + player.getEquippedPotionName() + " Potion  " + "(" + player.getEquippedPotion().getQuantity() + ")");
		labels[4].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .03472222), (int)(gameDim.getY() + gameDim.getHeight() * 0.93981481), (int)(gameDim.getWidth() * 0.15277777), (int)(gameDim.getHeight() * 0.03703703));
		labels[5].setText("Locked");
		if(true)
			labels[5].setText("Fire Lobber");
		labels[5].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .26180555), (int)(gameDim.getY() + gameDim.getHeight() * 0.87407407), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		labels[6].setText("Locked");
		if(true)
			labels[6].setText("Lightning Strike");
		labels[6].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .26180555), (int)(gameDim.getY() + gameDim.getHeight() * 0.90925925), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		labels[7].setText("Locked");
		if(true)
			labels[7].setText("\"Magic\" Powder");
		labels[7].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .26180555), (int)(gameDim.getY() + gameDim.getHeight() * 0.94259259), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		labels[8].setText("locked");
		if(true)
			labels[8].setText("Armor Plated Armor");
		labels[8].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .40138888), (int)(gameDim.getY() + gameDim.getHeight() * 0.87407407), (int)(gameDim.getWidth() * 0.22222222), (int)(gameDim.getHeight() * 0.01851851));
		labels[9].setText("Locked");
		if(true)
			labels[9].setText("Enchanted Steel");
		labels[9].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .40138888), (int)(gameDim.getY() + gameDim.getHeight() * 0.90925925), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		labels[10].setText("Locked");
		if(true)
			labels[10].setText("Mind Control");
		labels[10].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .40138888), (int)(gameDim.getY() + gameDim.getHeight() * 0.94259259), (int)(gameDim.getWidth() * 0.11111111), (int)(gameDim.getHeight() * 0.01851851));
		if(stringCount > 0) {
			stringCount--;
		} else {
			stringCount = 2000;
			if(!strings.isEmpty())
				strings.remove(0);
		}
		if(!strings.isEmpty())
			labels[11].setText(strings.get(0));
		else
			labels[11].setText("");
		labels[11].setBounds((int)(gameDim.getX() + gameDim.getWidth() * .38680555), (int)(gameDim.getY() + gameDim.getHeight() * 0.03981481), (int)(gameDim.getWidth() * 0.34305555), (int)(gameDim.getHeight() * 0.12592592));
		if(labels[player.getSelectedSpell() + 4].getForeground() != Color.GREEN)
			labels[5].setForeground(Color.BLACK);
			labels[6].setForeground(Color.BLACK);
			labels[7].setForeground(Color.BLACK);
			labels[8].setForeground(Color.BLACK);
			labels[9].setForeground(Color.BLACK);
			labels[player.getSelectedSpell() + 4].setForeground(Color.GREEN);
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
	
	public void addString(String s) {
		strings.add(s);
	}
	
	public Point mouseToPointConv(Point m) {
		return new Point((int)(m.getX() - innerRoom.getX() - Game.getPlayer().getDimensions().getCenterX() * centiunitToPixel), (int)(m.getY() - innerRoom.getY() - Game.getPlayer().getDimensions().getCenterY() * centiunitToPixel));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
