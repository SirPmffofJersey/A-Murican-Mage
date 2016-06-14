import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Item.Item;
import Item.Potion;
import Item.Sword;
public class Player extends Character
{
    private int exp = 0;
    private int expLimit = 100;
    private Sword s = new Sword("Dagger");
    private Potion[] potions;
	private boolean saberUnlocked;
	private boolean katanaUnlocked;
    
    public Player()
    {
        super("player",50,50,100,100,10,10,10,1,new Point(50, 50), new Rectangle(100, 100), new Sword("Dagger"), 1);
        potions = new Potion[2];
		potions[0] = new Potion(0);
		potions[1] = new Potion(1);
		potions[0].setEquipped(true);
		saberUnlocked = true;
		katanaUnlocked = true;
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
    
    public Potion getEquippedPotion() {
		if(potions[0].getEquipped())
			return potions[0];
		return potions[1];
	}
    
    public String getEquippedPotionName() {
    	if(potions[0].getEquipped())
			return "Health";
		return "Mana";
    }

	public void setSelectedPotion(String type) {
		if(type.equals("Health")) {
			potions[0].setEquipped(true);
			potions[1].setEquipped(false);
		} else {
			potions[0].setEquipped(false);
			potions[1].setEquipped(true);
		}
	}
	
	public boolean getSaberUnlocked() {return saberUnlocked;}
	
	public boolean getKatanaUnlocked() {return katanaUnlocked;}
	
	public void setSaberUnlocked(boolean s) {saberUnlocked = s;}
	
	public void setKatanaUnlocked(boolean k) {katanaUnlocked = k;}
    
    public void move(KeyEvent e) 
    {      
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
             System.out.println("tyes:");
             setLocation(new Point((int)getLocation().getX(),(int)getLocation().getY() - getSpeed())); 
             super.hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_A)
         {
             setLocation(new Point((int)getLocation().getX() - getSpeed(),(int)getLocation().getY())); 
             super.hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_D)
         {
             setLocation(new Point((int)getLocation().getX() + getSpeed(),(int)getLocation().getY())); 
             super.hitWall();
         }
         else if(e.getKeyCode() == KeyEvent.VK_S)
         {
             setLocation(new Point((int)getLocation().getX(),(int)getLocation().getY() + getSpeed())); 
             super.hitWall();
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
