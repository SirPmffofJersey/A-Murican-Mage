import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Item.Potion;
import Item.Sword;
public class Player extends Character
{
    private int exp = 0;
    private int expLimit = 100;
    private Sword s = new Sword("Dagger");
    private Potion[] potions;
    private int attackCounter;
	private boolean saberUnlocked;
	private boolean katanaUnlocked;
    
    public Player()
    {
        super("player",50,50,100,100,10,10,10,1, new Rectangle(50, 50, 100, 100), new Sword("Dagger"), 1);
        potions = new Potion[2];
		potions[0] = new Potion(0);
		potions[1] = new Potion(1);
		potions[0].setEquipped(true);
		saberUnlocked = true;
		katanaUnlocked = true;
		attackCounter = -1;
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
        String d = other.dropLoot();
        if(d.equals("Health Potion")) {
        	potions[0].addPotion();
        	return;
        }
        if(d.equals("Mana Potion")) {
        	potions[1].addPotion();
        	return;
        }
        if(d.equals("Saber")) {
        	setSaberUnlocked(true);
        	return;
        }
        if(d.equals("Katana")) {
        	setKatanaUnlocked(true);
        }
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
	
	public int getAttackCount() {return attackCounter;}
	
	public void decrementAttackCount() {attackCounter--;}
	
	public Rectangle setSwordDim() {
		int d = getDirection();
		if(d == 1)
			return new Rectangle((int)(getDimensions().getX() + (getDimensions().getWidth() - getSword().getWidth())/2), (int)(getDimensions().getY() - getSword().getRange()), (int)getSword().getWidth(), (int)getSword().getRange());
		if(d == 2)
			return new Rectangle((int)(getDimensions().getX() + getDimensions().getWidth()), (int)(getDimensions().getY()+ (getDimensions().getHeight() - getSword().getWidth())/2), (int)getSword().getRange(), (int)getSword().getWidth());
		if(d == 3)
			return new Rectangle((int)(getDimensions().getX() + (getDimensions().getWidth() - getSword().getWidth())/2), (int)(getDimensions().getY() + getDimensions().getHeight()), (int)getSword().getWidth(), (int)getSword().getRange());
		if(d == 4)
			return new Rectangle((int)(getDimensions().getX() - getSword().getRange()), (int)(getDimensions().getY()+ (getDimensions().getHeight() - getSword().getWidth())/2), (int)getSword().getRange(), (int)getSword().getWidth());
		return null;
	}
	
	public void stopAttack() {
		getSword().setDim(null);
		attackCounter = -1;
	}
    
    public void move(KeyEvent e) 
    {    
    	 if(attackCounter <= 0) {
	         if(e.getKeyCode() == KeyEvent.VK_W)
	         {
	        	 setDirection(1);
	             setLocation(new Point((int)getDimensions().getX(),(int)getDimensions().getY() - getSpeed())); 
	             super.hitWall();
	         }
	         else if(e.getKeyCode() == KeyEvent.VK_A)
	         {
	        	 setDirection(4);
	             setLocation(new Point((int)getDimensions().getX() - getSpeed(),(int)getDimensions().getY())); 
	             super.hitWall();
	         }
	         else if(e.getKeyCode() == KeyEvent.VK_D)
	         {
	        	 setDirection(2);
	             setLocation(new Point((int)getDimensions().getX() + getSpeed(),(int)getDimensions().getY())); 
	             super.hitWall();
	         }
	         else if(e.getKeyCode() == KeyEvent.VK_S)
	         {
	        	 setDirection(3);
	             setLocation(new Point((int)getDimensions().getX(),(int)getDimensions().getY() + getSpeed())); 
	             super.hitWall();
	         }
	         else if(e.getKeyCode() == KeyEvent.VK_Q)
	         {
	             getEquippedPotion().use(this);
	         }
    	 }
         
    }
    
    public void attack(MouseEvent e)
    {
        if(e.getClickCount() == 1)
        {
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                System.out.println("You attacked"); 
                /*
                if(canAttack(c))
                {
                    c.attacked(getAttack());
                    if(c.getHealth() == 0)
                        addExperiance();
                }
                */
                if(attackCounter < 0) {
                	attackCounter = getSword().getSwingSpeed();
                	getSword().setDim(setSwordDim());
                }
            }
            if(e.getButton() == MouseEvent.BUTTON3)
            {
                System.out.println("You used a spell"); 
                /*
                if(canUseMagic(c,0))
                {
                    c.magicAttack(c,0);
                    c.attacked(getAttack());
                    if(c.getHealth() == 0)
                        addExperiance();
                }
                */
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
    
    public void forceTo(Point p) {
    	System.out.println(p.getX());
    	System.out.println(p.getY());
    	setDimensions((int)p.getX(), (int)p.getY(), (int)getDimensions().getWidth(), (int)getDimensions().getHeight());
    	System.out.println(getDimensions().getX());
    	System.out.println(getDimensions().getY());
    }
}
