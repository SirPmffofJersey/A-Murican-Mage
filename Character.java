import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import Item.Sword;
import Main.Game;
import Map.Doorway;
import Map.Obstacle;
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
	private int seconds;
	private boolean immune = false;
	// private Buffs[] buffs;
	private Rectangle dimensions;
	private Rectangle nextDim;				//Used for collisions
	private Image image;
	private Sword sword;
	public Character(String name, int hp, int mp, int maxHp, int maxMp, int atk, int def, int spd, int lvl,
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
		dimensions = dim;
		nextDim = dim;
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

	public void setDimensions(int w, int h) {
		int x = (int)dimensions.getX();
		int y = (int)dimensions.getY();
		dimensions = new Rectangle(x, y, w, h);
	}
	
	public void setDimensions(int x, int y, int w, int h) {
		dimensions = new Rectangle(x, y, w, h);
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
		nextDim = new Rectangle((int)loc.getX(), (int)loc.getY(), (int)dimensions.getWidth(), (int)dimensions.getHeight());
		System.out.println("" + dimensions.getX() + " " + dimensions.getY() + " " + dimensions.getWidth() + " " + dimensions.getHeight() + " " + nextDim.getX() + " " + nextDim.getY() + " " + nextDim.getWidth() + " " + nextDim.getHeight());
		for(int i = 0; i < Game.getMap().getCurrentRoom().getNumberOfObstacles(); i++)
			collision(Game.getMap().getCurrentRoom().getObstacle(i));
		dimensions = nextDim;
		collision(Game.getPlayer().getSword(), loc);
	}

	public int movementState() {
		return state % 4;
	}

	public void attack(Character other) {
		if (other.dimensions.getX() - dimensions.getX() <= sword.getRange()
				|| other.dimensions.getY() - dimensions.getY() <= sword.getRange()
				|| (other.dimensions.getY() - dimensions.getY()) / (other.dimensions.getX() - dimensions.getX()) <= sword
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
		//dimensions.move((int) location.getX() + speed, (int) location.getY() + speed);
	}

	public void attacked(int a) {
		if((a + sword.getDamage() - defense) <= 0 && !immune)
            		health -= 1; 
        	else if(!immune) 
            		health -= (a + sword.getDamage() - defense);
                    
        	Timer timer = new Timer();
        	TimerTask task = new TimerTask(){
            	public void run()
            	{
                	seconds ++; 
                	immune = true; 
                	if(seconds >= 8)
                	{
                    		immune = false;  
                    		timer.cancel();
                    		timer.purge(); 
                	}
            	}
        	};
        	timer.schedule(task,250); 
        	//seconds = 0; 
	}
	
	/*
	public boolean collision(Character c) {
		int coordinateX = Math.abs((int) c.getDimensions().getX() - (int) location.getX());
		int coordinateY = Math.abs((int) c.getDimensions().getY() - (int) location.getY());
		int distance = coordinateX + coordinateY;
		if (distance <= 50)
			return true;
		return false;
	}
	*/
	
	public boolean collision(Character c) {
		return false;
	}
	
	public boolean collision(Sword s, Point p) {
		if(s.getDim() == null)
			return false;
		if(s.getDim().intersects(dimensions)) {
			attacked(Game.getPlayer().getAttack() + s.getDamage());
			return true;
		}
		return false;
	}
	
	public boolean collision(Obstacle o) {
		Doorway d = null;
		if(o instanceof Doorway) {
			d = (Doorway)o;
			if(d.getEnabled())
				d.travel();
			else
				return false;
		} else {
			if(o.getDimensions().intersects(nextDim)) {
				System.out.println("Intersection");
				if(o.getDimensions().getX() >= dimensions.getX() + dimensions.getWidth() && o.getDimensions().getX() < nextDim.getX() + nextDim.getWidth()) {
					System.out.println("Collision");
					nextDim = new Rectangle((int)(o.getDimensions().getX() - nextDim.getWidth()), (int)nextDim.getY(), (int)nextDim.getWidth(), (int)nextDim.getHeight());
					return true;
				} else if(o.getDimensions().getX() + o.getDimensions().getWidth() <= dimensions.getX() && o.getDimensions().getX() + o.getDimensions().getWidth() > nextDim.getX()) {
					System.out.println("Collision");
					nextDim = new Rectangle((int)(o.getDimensions().getX() + o.getDimensions().getWidth()), (int)nextDim.getY(), (int)nextDim.getWidth(), (int)nextDim.getHeight());
					return true;
				} else if(o.getDimensions().getY() >= dimensions.getY() + dimensions.getHeight() && o.getDimensions().getY() < nextDim.getY() + nextDim.getHeight()) {
					System.out.println("Collision");
					nextDim = new Rectangle((int)nextDim.getX(), (int)(o.getDimensions().getY() - nextDim.getHeight()), (int)nextDim.getWidth(), (int)nextDim.getHeight());
					return true;
				} else if(o.getDimensions().getY() + o.getDimensions().getHeight() <= dimensions.getY() && o.getDimensions().getY() + o.getDimensions().getHeight() > nextDim.getY()) {
					System.out.println("Collision");
					nextDim = new Rectangle((int)nextDim.getX(), (int)(o.getDimensions().getY() + o.getDimensions().getHeight()), (int)nextDim.getWidth(), (int)nextDim.getHeight());
					return true;
				}
			} else
				return false;
		}
		return false;
	}

	public void hitWall() {
		int maxX = (int)(Game.getMap().getCurrentRoom().getDim().getX() + Game.getMap().getCurrentRoom().getDim().getWidth() - dimensions.getWidth());
		int maxY = (int)(Game.getMap().getCurrentRoom().getDim().getY() + Game.getMap().getCurrentRoom().getDim().getHeight() - dimensions.getHeight());
		if (dimensions.getX() >= maxX)
			setLocation(new Point(maxX, (int) dimensions.getY()));
		if (dimensions.getX() <= 0)
			setLocation(new Point(0, (int) dimensions.getY()));
		if (dimensions.getY() >= maxY)
			setLocation(new Point((int) dimensions.getX(), maxY));
		if (dimensions.getY() <= 0)
			setLocation(new Point((int) dimensions.getX(), 0));

	}

	public boolean canAttack(Character c) {
		int coordinateX = Math.abs((int) c.getDimensions().getX() - (int) dimensions.getX());
		int coordinateY = Math.abs((int) c.getDimensions().getY() - (int) dimensions.getY());
		int distance = coordinateX + coordinateY;
		if (distance <= 100)
			return true;
		return false;
	}

	public boolean canUseMagic(Character c, int manaCost) {
		int coordinateX = Math.abs((int) c.getDimensions().getX() - (int) dimensions.getX());
		int coordinateY = Math.abs((int) c.getDimensions().getY() - (int) dimensions.getY());
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
		if (c.getDimensions().getX() > (int) dimensions.getX()) {
			setLocation(new Point((int) dimensions.getX() + 10, (int) dimensions.getY()));
			newD = 2;
			hitWall();
		} else if (c.getDimensions().getX() < (int) dimensions.getX()) {
			setLocation(new Point((int) dimensions.getX() - 10, (int) dimensions.getY()));
			newD = 4;
			hitWall();
		}
		if (c.getDimensions().getY() > (int) dimensions.getY()) {
			setLocation(new Point((int) dimensions.getX(), (int) dimensions.getY() + 10));
			newD = 3;
			hitWall();
		} else if (c.getDimensions().getY() < (int) dimensions.getY()) {
			setLocation(new Point((int) dimensions.getX(), (int) dimensions.getY() - 10));
			newD = 1;
			hitWall();
		}
		
		if(newD == 0)
			setDirection(newD);
		if(newD != getDirection())
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
        	return dimensions.getX() > new Room(roomNum).getDim().getX() && dimensions.getY() > new Room(roomNum).getDim().getY() && dimensions.getX() < new Room(roomNum).getDim().getX() + new Room(roomNum).getDim().getWidth() && dimensions.getY() < new Room(roomNum).getDim().getY() + new Room(roomNum).getDim().getHeight();
    	}

}
