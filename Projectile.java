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
	private int speed;
	private double angle;
	
	public Projectile(Spell t, Point mouse) {
		type = t;
		angle = Math.atan(Game.getPlayer().getDimensions().getCenterY() - mouse.getY()/Game.getPlayer().getDimensions().getCenterX() - mouse.getX());
		yRange = (int) (t.getRange() * Math.sin(Math.toRadians(angle)));
		xRange = (int) (t.getRange() * Math.cos(Math.toRadians(angle)));
		if(type == t)
			;
			//dim = new Rectangle(Game.getPlayer().getDimensions().getX(), );
	}
}
