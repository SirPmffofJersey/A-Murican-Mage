package Item;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Main.Game;
import Spell.AttackSpell;
import Spell.Buff;

/**
 * <h1> Projectile Class </h1>
 * This class represents a projectile sent by
 * an AttackSpell
 * 
 * @author Douglas Fisher
 * @author 6/11/16
 */
public class Projectile {
	private Rectangle dim;
	private AttackSpell type;
	private int xRange;
	private int yRange;
	private int xStart;
	private int yStart;
	private double distanceToTravel;
	private boolean toBeRemoved;
	private Image image;
	private Buff b;
	
	/**
	 * This is a constructor that builds a projectile and 
	 * Initializes most of the fields.
	 * @param t This represents the AttackSpell that "sent" the projectile
	 * @param mouse This is where the player clicked to trigger a projectile being sent
	 */
	public Projectile(AttackSpell t, Point mouse) {
		type = t;
		mouse = Game.getScreen().getGameGraphics().mouseToPointConv(mouse);
		double ratio = Math.abs(mouse.getY())/Math.abs(mouse.getX());
		xRange = (int) Math.sqrt(Math.pow(t.getRange(), 2)/ (1 + ratio * ratio));
		yRange = (int)(xRange * ratio);
		if(mouse.getX() < 0)
			xRange *= -1;
		if(mouse.getY() < 0)
			yRange *= -1;
		double speed = 0;
		if(t.getName().equals("Fire Lobber")) {
			speed = 10;
			b = new Buff("burn");
		} else {
			speed = 20;
			b = null;
		}
		distanceToTravel = speed/t.getRange();
		dim = new Rectangle((int)Game.getPlayer().getDimensions().getCenterX() - 25, (int)Game.getPlayer().getDimensions().getCenterY() - 25, 50, 50);
		xStart = (int)dim.getX();
		yStart = (int)dim.getY();
		if(Math.abs(xRange) < Math.abs(yRange) && (yRange <  0 || t.getName().equals("Lightning Strike")))
			image = new ImageIcon(getClass().getResource("/" + t.getName() + "1.gif")).getImage();
		else if(Math.abs(xRange) > Math.abs(yRange) && (xRange >  0 || t.getName().equals("Lightning Strike")))
			image = new ImageIcon(getClass().getResource("/" + t.getName() + "2.gif")).getImage();
		else if(Math.abs(xRange) < Math.abs(yRange) && yRange >  0)
			image = new ImageIcon(getClass().getResource("/" + t.getName() + "3.gif")).getImage();
		else if(Math.abs(xRange) > Math.abs(yRange) && xRange <  0)
			image = new ImageIcon(getClass().getResource("/" + t.getName() + "4.gif")).getImage();
	}
	
	/**
	 * This method returns this projectile's image.
	 * @return Image This is the projectiles image
	 */
	public Image getImage() {return image;}
	
	/**
	 * This method returns this projectile's dimensions.
	 * @return Rectangle This is the projectile's dimensions
	 */
	public Rectangle getDim() {return dim;}
	
	/**
	 * This method returns the AttackSpell that "sent" this 
	 * projectile.
	 * @return AttackSpell This is the AttackSpell that "sent" this projectile
	 */
	public AttackSpell getSpell() {return type;}
	
	/**
	 * This method returns the buff associated with this projectile.
	 * Returns null is no Buff is associated with the projectile.
	 * @return Buff This is the buff associated with this projectile
	 */
	public Buff getBuff() {return b;}
	
	/**
	 * This method detects if the projectile has reached the bounds of the
	 * room and if so sets them to be deleted.
	 */
	public void hitWall() {
		int maxX = (int)(Game.getMap().getCurrentRoom().getDim().getX() + Game.getMap().getCurrentRoom().getDim().getWidth() - dim.getWidth());
		int maxY = (int)(Game.getMap().getCurrentRoom().getDim().getY() + Game.getMap().getCurrentRoom().getDim().getHeight() - dim.getHeight());
		if (dim.getX() >= maxX)
			toBeRemoved = true;
		if (dim.getX() <= 0)
			toBeRemoved = true;
		if (dim.getY() >= maxY)
			toBeRemoved = true;
		if (dim.getY() <= 0)
			toBeRemoved = true;
	}
	
	/**
	 * This method moves the projectile a preset distance in the x and y direction.
	 */
	public void move() {
		dim.setLocation((int)(dim.getX() + xRange * distanceToTravel), (int)(dim.getY() + yRange * distanceToTravel));
		if(Math.abs(dim.getX() - xStart) > Math.abs(xRange) && Math.abs(dim.getY()) - yStart > Math.abs(yRange))
			toBeRemoved = true;
		hitWall();
	}
	
	/**
	 * This method sets the toBeRomved variable to true.
	 */
	public void setToBeRemoved() {toBeRemoved = true;}
	
	/**
	 * This method returns a boolean representing if the projectile
	 * needs to be removed.
	 * @return boolean This is the field toBeRemoved
	 */
	public boolean toBeRemoved() {return toBeRemoved;}
}
