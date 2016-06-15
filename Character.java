import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Item.Sword;
import Main.Game;
import Map.Room;

/**
 * Abstract class Character - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public class Character {
	// Basic Character Stats
	private String name;
	private int health;
	private int mana;
	private int maxHealth;
	private int maxMana;
	private int attack;
	private int defense;
	private int speed;
	private int level;
	private int direction;
	private int state;
	// private Buffs[] buffs;
	private Rectangle dimensions;
	private Point location;
	private Image image;
	private Sword sword;
	public Character(String name, int hp, int mp, int maxHp, int maxMp, int atk, int def, int spd, int lvl, Point loc,
			Rectangle dim, Sword s, int direct) {
		this.name = name;
		health = hp;
		mana = mp;
		maxHealth = maxHp;
		maxMana = maxMp;
		attack = atk;
		defense = def;
		speed = spd;
		level = lvl;
		location = loc;
		dimensions = dim;
		sword = s;
		direction = direct;
		state = 0;
		image = new ImageIcon(getClass().getResource("/" + name + direction + ".gif")).getImage();
	}

	// Accessor Methods
	public int getHealth() {
		return health;
	}

	public int getMana() {
		return mana;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public int getDefense() {
		return defense;
	}

	public int getAttack() {
		return attack;
	}

	public int getSpeed() {
		return speed;
	}

	public int getLevel() {
		return level;
	}

	public int getDirection() {
		return direction;
	}

	public Rectangle getDimensions() {
		return dimensions;
	}

	/*
	 * public Buffs[] getBuffs() { return buffs; }
	 */

	public Image getImage() {
		return image;
	}

	public Sword getSword() {
		return sword;
	}

	public Point getLocation() {
		return location;
	}

	// Modifying Methods
	public void setHealth(int hp) {
		health = hp;
	}

	public void setMana(int mp) {
		mana = mp;
	}

	public void setMaxHealth(int maxHp) {
		maxHealth = maxHp;
	}

	public void setMaxMana(int maxMp) {
		maxMana = maxMp;
	}

	public void setDefense(int def) {
		defense = def;
	}

	public void setAttack(int atk) {
		attack = atk;
	}

	public void setSpeed(int spd) {
		speed = spd;
	}

	public void setLevel(int lvl) {
		level = lvl;
	}
	
	public void setDirection(int d) {
		direction = d;
		resetImage();
	}

	public void setDimensions(int x, int y) {
		dimensions = new Rectangle(x, y);
	}

	/*
	 * public void setBuffs(Buffs[] b ) { buffs = b; }
	 */

	public void resetImage() {
		image = new ImageIcon(getClass().getResource("/" + name + direction + ".gif")).getImage();;
	}

	public void setSword(Sword s) {
		sword = s;
	}

	public void setLocation(Point loc) {
		location = loc;
	}

	public int movementState() {
		return state % 4;
	}

	public void attack(Character other) {
		if (other.location.getX() - location.getX() <= sword.getRange()
				|| other.location.getY() - location.getY() <= sword.getRange()
				|| (other.location.getY() - location.getY()) / (other.location.getX() - location.getX()) <= sword
						.getRange()) {
			other.setHealth(other.getHealth() - sword.getDamage());
		}
	}

	public boolean isDead() {
		if (health == 0) {
			return true;
		}
		return false;
	}

	public void move() {
		location.move((int) location.getX() + speed, (int) location.getY() + speed);
	}

	public void attacked(int a) {
		health -= (a - defense);
	}

	public boolean collision(Character c) {
		int coordinateX = Math.abs((int) c.getDimensions().getX() - (int) location.getX());
		int coordinateY = Math.abs((int) c.getDimensions().getY() - (int) location.getY());
		int distance = coordinateX + coordinateY;
		if (distance <= 50)
			return true;
		return false;
	}

	public void hitWall() {
		int maxX = (int)(Game.getMap().getCurrentRoom().getDim().getX() + Game.getMap().getCurrentRoom().getDim().getWidth() - dimensions.getWidth());
		int maxY = (int)(Game.getMap().getCurrentRoom().getDim().getY() + Game.getMap().getCurrentRoom().getDim().getHeight() - dimensions.getHeight());
		if (location.getX() >= maxX)
			setLocation(new Point(maxX, (int) location.getY()));
		if (location.getX() <= 0)
			setLocation(new Point(0, (int) location.getY()));
		if (location.getY() >= maxY)
			setLocation(new Point((int) location.getX(), maxY));
		if (location.getY() <= 0)
			setLocation(new Point((int) location.getX(), 0));

	}

	public boolean canAttack(Character c) {
		int coordinateX = Math.abs((int) c.getDimensions().getX() - (int) location.getX());
		int coordinateY = Math.abs((int) c.getDimensions().getY() - (int) location.getY());
		int distance = coordinateX + coordinateY;
		if (distance <= 100)
			return true;
		return false;
	}

	public boolean canUseMagic(Character c, int manaCost) {
		int coordinateX = Math.abs((int) c.getDimensions().getX() - (int) location.getX());
		int coordinateY = Math.abs((int) c.getDimensions().getY() - (int) location.getY());
		int distance = coordinateX + coordinateY;
		if (distance <= 100 && mana > manaCost)
			return true;
		return false;
	}

	public void magicAttack(Character c, int manaCost) {
		if (canUseMagic(c, manaCost)) {
			// c.attacked(magicAttack);
			mana -= manaCost;
		}
	}

	public void moveTowardPlayer(Character c) {
		int newD = 0;
		if (c.getLocation().getX() > (int) location.getX()) {
			setLocation(new Point((int) location.getX() + 10, (int) location.getY()));
			newD = 2;
			hitWall();
		} else if (c.getLocation().getX() < (int) location.getX()) {
			setLocation(new Point((int) location.getX() - 10, (int) location.getY()));
			newD = 4;
			hitWall();
		}
		if (c.getLocation().getY() > (int) location.getY()) {
			setLocation(new Point((int) location.getX(), (int) location.getY() + 10));
			newD = 3;
			hitWall();
		} else if (c.getLocation().getY() < (int) location.getY()) {
			setLocation(new Point((int) location.getX(), (int) location.getY() - 10));
			newD = 1;
			hitWall();
		}
		
		if(newD != 0 || newD != getDirection())
			setDirection(newD);

		/*
		 * if(c.getX() > getX() && getY() == c.getY()) facing = 3; else
		 * if(c.getX() < getX() && getY() == c.getY()) facing = 2; else
		 * if(c.getX() == getX() && getY() > c.getY()) facing = 4; else
		 * if(c.getX() == getX() && getY() < c.getY()) facing = 1;
		 * 
		 */

	}
	
	public boolean insideMap(int roomNum)
    	{
        	return location.getX() > new Room(roomNum).getDim().getX() && location.getY() > new Room(roomNum).getDim().getY() && location.getX() < new Room(roomNum).getDim().getX() + new Room(roomNum).getDim().getWidth() && location.getY() < new Room(roomNum).getDim().getY() + new Room(roomNum).getDim().getHeight();
    	}

}
