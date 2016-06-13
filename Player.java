import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Item.Item;
import Item.Sword;
public class Player extends Character
{
    private int exp = 0;
    private int expLimit = 100;
    private Sword s = new Sword("Dagger");
    private Potion[] potions;
    
    public Player()
    {
       super("player", 100, 50, 100, 50, 30, 30, 40, 1,new Point(0,0), new Rectangle(40,40), null, 1);
       s = new Sword("Dagger");
       potions[0] = new Potion(0);
       potions[1] = new Potion(1);
    }

    public void levelUp()
    {
        setLevel(getLevel() +1);
        setMaxHealth((int)(getMaxHealth() + getMaxHealth() * .25));
        setHealth(getMaxHealth());
        setMaxMana((int)(getMaxMana() + getMaxMana() * .25));
        setMana(getMaxMana());
        setAttack((int)(getAttack() + getAttack() * .25));
        setDefense((int)(getDefense() + getDefense() * .25));
    }
    
    public void regenerate()
    {
        if(getHealth() < getMaxHealth())
        {
            setHealth(getHealth() + 1);
        }
        
        if(getMana() < getMaxMana())
        {
            setMana(getMana() + 1);
        }
    }
    
    public void getLoot(Enemies other)
    {
        //inventory.add(other.dropLoot());
    }
    
    public void move(KeyEvent e) 
    {      
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
        	 System.out.println("tyes:");
             setLocation(new Point((int)super.getLocation().getX(),(int)super.getLocation().getY() - super.getSpeed())); 
             //hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_A)
         {
             super.setLocation(new Point((int)super.getLocation().getX() - super.getSpeed(),(int)super.getLocation().getY())); 
             //super.hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_D)
         {
             super.setLocation(new Point((int)super.getLocation().getX() + super.getSpeed(),(int)super.getLocation().getY())); 
            // super.hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_S)
         {
             super.setLocation(new Point((int)super.getLocation().getX(),(int)super.getLocation().getY() + super.getSpeed())); 
           //  super.hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_Q)
         {
             if(potion[0].getEquipped)
                potion[0].use(this);
            else
                potion[1].use(this);
         }
         
    }
    
    public void attack(KeyEvent e, Character c)
    {
        if(e.getKeyCode() == KeyEvent.VK_M)
        {
            if(super.canAttack(c))
            {
                c.attacked(super.getAttack());
                if(c.getHealth() == 0)
                    addExperiance();
            }
        }
    }
    
    public void addExperiance() 
    {
        exp += 10; 
        if(exp == 100)
        {
            System.out.print("You leveled up, you are now level " + super.getLevel()); 
            exp = 0; 
            levelUp(); 
        }
    }
}
