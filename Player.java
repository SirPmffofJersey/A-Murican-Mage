import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Player extends Character
{
    private int exp = 0;
    private int expLimit = 100;
    private Item e = new Sword();
    private ArrayList<Item> inventory = new ArrayList<Item>();
    
    public Player()
    {
       super("player", 100, 50, 100, 50, 30, 30, 40, new Point(0,0), new Rectangle(40,40), null, 1);
    }

    public void levelUp()
    {
        setLevel(getLevel() +1);
        setMaxHealth(getMaxHealth() + getMaxHealth() * .25);
        setHealth(getMaxHealth());
        setMaxMana(getMaxMana() + getMaxMana() * .25);
        setMana(getMaxMana());
        setAttack(getAttack() + getAttack() * .25);
        setDefense(getDefense() + getDefense() * .25);
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
        inventory.add(other.dropLoot());
    }
    
    public void move(KeyEvent e) 
    {      
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
             super.setLocation(new Point((int)super.getLocation().getX(),(int)super.getLocation().getY() - super.getSpeed())); 
             super.hitWall();
             forward = true;
             left = false; 
             right = false;
             back = false;
         }
         else if(e.getKeyCode() == KeyEvent.VK_A)
         {
             super.setLocation(new Point((int)super.getLocation().getX() - super.getSpeed(),(int)super.getLocation().getY())); 
             super.hitWall();
             forward = false;
             left = true; 
             right = false;
             back = false; 
         }
         else if(e.getKeyCode() == KeyEvent.VK_D)
         {
             super.setLocation(new Point((int)super.getLocation().getX() + super.getSpeed(),(int)super.getLocation().getY())); 
             super.hitWall();
             forward = false;
             left = false; 
             right = true;
             back = false; 
         }
         else if(e.getKeyCode() == KeyEvent.VK_S)
         {
             super.setLocation(new Point((int)super.getLocation().getX(),(int)super.getLocation().getY() + super.getSpeed())); 
             super.hitWall();
             forward = false;
             left = false; 
             right = false;
             back = true; 
         }
         
    }
    
    public void draw(Graphics g) 
    {
        if(super.getHealth() > 0)
        {
            g.fillOval((int)super.getLocation().getX(),(int)super.getLocation().getY(),50,50); 
        }
        
        if(forward) 
        {
            g.fillRect((int)super.getLocation().getX(),(int)super.getLocation().getY() - 25,50,25);
            facing = 1; 
        }
        
        if(left) 
        {
            g.fillRect((int)super.getLocation().getX() - 25,(int)super.getLocation().getY(),50,25);
            facing = 2;
        }
        
        if(right) 
        {
            g.fillRect((int)super.getLocation().getX() + 25,(int)super.getLocation().getY(),50,25);
            facing = 3; 
        }
        
        if(back) 
        {
            g.fillRect((int)super.getLocation().getX(),(int)super.getLocation().getY() + 25,50,25);
            facing = 4; 
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
        
