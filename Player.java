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
    
    public Player(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Point loc, Rectangle dim, Item e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,loc,dim,e,direct);
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
             setLocation(new Point((int)getLocation().getX(),(int)getLocation().getY() - getSpeed())); 
             //hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_A)
         {
             setLocation(new Point((int)getLocation().getX() - getSpeed(),(int)getLocation().getY())); 
             //super.hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_D)
         {
             setLocation(new Point((int)getLocation().getX() + getSpeed(),(int)getLocation().getY())); 
            // super.hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_S)
         {
             setLocation(new Point((int)getLocation().getX(),(int)getLocation().getY() + getSpeed())); 
           //  super.hitWall();
         }
         
    }
    
    public void attack(MouseEvent e, Character c)
    {
        if(e.getClickCount() == 1)
        {
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                System.out.println("You attacked"); 
                if(canAttack(c))
                {
                    c.attacked(getAttack());
                    if(c.getHealth() == 0)
                        addExperiance();
                }
            }
            if(e.getButton() == MouseEvent.BUTTON3)
            {
                System.out.println("You used a spell"); 
                if(canUseMagic(c,0))
                {
                    c.magicAttack(c,0);
                    c.attacked(getAttack());
                    if(c.getHealth() == 0)
                        addExperiance();
                }
            }
        }
    }
    
    public void addExperiance() 
    {
        exp += 10; 
        if(exp == 100)
        {
            System.out.print("You leveled up, you are now level " + getLevel()); 
            exp = 0; 
            levelUp(); 
        }
    }
}
