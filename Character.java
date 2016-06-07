import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Abstract class Character - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public class Character
{
    //Basic Character Stats
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
    private String imageName;
    private Item equip;

    public Character(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Point loc, Rectangle dim, Item e,int direct)
    {
        imageName = name;
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
        equip = e;
        direction = direct;
        state = 0;
    }
    //Accessor Methods
    public int getHealth()
    {
        return health;
    }

    public int getMana()
    {
        return mana;
    }
    
    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getMaxMana()
    {
        return maxMana;
    }
    
    public int getDefense()
    {
        return defense;
    }

    public int getAttack()
    {
        return attack;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getLevel()
    {
        return level;
    }

    public int getDirection()
    {
        return direction;
    }
    
    public Rectangle getDimensions()
    {
        return dimensions;
    }

    /* public Buffs[] getBuffs()
    {
    return buffs;
    }*/

    public String getImageName()
    {
        return imageName;
    }

    public Item getEquip()
    {
        return equip;
    }

    public Point getLocation()
    {
        return location;
    }

    //Modifying Methods
    public void setHealth(int hp)
    {
        health = hp;
    }

    public void setMana( int mp)
    {
        mana = mp;
    }

    public void setMaxHealth(int maxHp)
    {
        maxHealth = maxHp;
    }

    public void setMaxMana(int maxMp)
    {
        maxMana = maxMp;
    }
    
    public void setDefense(int def)
    {
        defense = def;
    }

    public void setAttack(int atk)
    {
        attack = atk;
    }

    public void setSpeed(int spd)
    {
        speed = spd;
    }

    public void setLevel( int lvl)
    {
        level = lvl;
    }

    public void setDimensions(int x, int y)
    {
        dimensions = new Rectangle(x, y);
    }

    /*public void setBuffs(Buffs[] b )
    {
    buffs = b;
    }*/

    public void setImageName(String name)
    {
        imageName = name;
    }

    public void setEquip(Item e)
    {
        equip = e;
    }

    public void setLocation(Point loc)
    {
        location = loc;
    }

    public int movementState()
    {
        return state % 4;
    }
    public void attack(Character other)
    {
        if(other.location.getX() - location.getX() <= equip.getRange() || other.location.getY() - location.getY() <= equip.getRange() || (other.location.getY() - location.getY())/(other.location.getX() - location.getX()) <= equip.getRange())
        {
            other.setHealth(other.getHealth() - equip.getDamage());
        }
    }

    public void move(KeyEvent event)
    {
        if(event.getKeyChar()=='w')
        {
            location.move((int)location.getX() - speed, (int)location.getY());
            direction = 1;
            state ++;
        }
        if(event.getKeyChar()=='d')
        {
            location.move((int)location.getX(), (int)location.getY() + speed);
            direction = 2;
            state ++;
        }
        if(event.getKeyChar()=='s')
        {
            location.move((int)location.getX() + speed, (int)location.getY());
            direction = 3;
            state ++;
        }
        if(event.getKeyChar()=='a')
        {
            location.move((int)location.getX(), (int)location.getY() - speed);
            direction = 4;
            state ++;
        }

    }

    public boolean isDead()
    {
        if(health == 0)
        {
            return true;
        }
        return false;
    }

}
