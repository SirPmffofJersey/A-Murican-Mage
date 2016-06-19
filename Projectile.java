package Item;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Main.Game;
import Spell.AttackSpell;
import Spell.Buff;

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
	
	public void move() {
		dim.setLocation((int)(dim.getX() + xRange * distanceToTravel), (int)(dim.getY() + yRange * distanceToTravel));
		if(Math.abs(dim.getX() - xStart) > Math.abs(xRange) && Math.abs(dim.getY()) - yStart > Math.abs(yRange))
			toBeRemoved = true;
		hitWall();
	}
	
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
	
	public void setToBeRemoved() {
		toBeRemoved = true;
	}
	
	public boolean toBeRemoved() {return toBeRemoved;}
	
	public Image getImage() {
		return image;
	}
	
	public Rectangle getDim() {return dim;}
	
	public AttackSpell getSpell() {
		return type;
	}
	
	public Buff getBuff() {
		return b;
	}
}
