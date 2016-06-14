package Item;

import java.awt.Point;
import java.awt.Rectangle;

import Main.Game;
import Spell.Spell;

public class Projectile {
	private Rectangle dim;
	private Spell type;
	private int xRange;
	private int yRange;
	private String image;
	private double distanceToTravel;
	private double angle;
	private boolean toBeRemoved;
	
	public Projectile(Spell t, Point mouse) {
		type = t;
		angle = Math.atan(Game.getPlayer().getDimensions().getCenterY() - mouse.getY()/Game.getPlayer().getDimensions().getCenterX() - mouse.getX());
		yRange = (int) (t.getRange() * Math.sin(Math.toRadians(angle)));
		xRange = (int) (t.getRange() * Math.cos(Math.toRadians(angle)));
		int speed = 0;
		if(t.getName().equals("FireLobber"))
			speed = 5;
		else
			speed = 10;
		distanceToTravel = speed/t.getRange();
		if(type == t)
			dim = new Rectangle((int)Game.getPlayer().getDimensions().getX(), (int)Game.getPlayer().getDimensions().getY(), 50, 50);
		while(dim.intersects(Game.getPlayer().getDimensions()))
			move();
	}
	
	public void move() {
		dim.setLocation((int)(dim.getX() + xRange * distanceToTravel), (int)(dim.getX() + yRange * distanceToTravel));
		if(dim.getX() > xRange || dim.getY() > yRange)
			toBeRemoved = true;
	}
	
	public boolean toBeRemoved() {return toBeRemoved;}
	
	public Rectangle getDim() {return dim;}
}
