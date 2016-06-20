import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import Item.Potion;
import Item.Sword;
import Main.Game;
import Spell.AttackSpell;
import Spell.Buff;

/**
 * Player Class:
 * The player class ectends the character class and is responsible 
 * for all of the players actions; how they fight, move, use items etc. 
 *
 * 
 * @author Jimmy Greaves
 * @version 5.6 6/18/16
 */
public class Player extends Character
{
    private int exp = 0;
    private int expLimit = 100;
    private Potion[] potions;
    private int attackCounter;
	private boolean saberUnlocked;
	private boolean katanaUnlocked;
	private boolean lUnlocked;
	private boolean mUnlocked;
	private boolean aUnlocked;
	private boolean eUnlocked;
	private boolean mcUnlocked;
	private AttackSpell fire, lightning;
	private int selectedSpell;
	private int repCount;
    
    public Player()
    {
        super("player",100,100,100,100,10,10,10,1, new Rectangle(150, 150, 100, 100), new Sword("Dagger"), 1);
        potions = new Potion[2];
		potions[0] = new Potion(0);
		potions[1] = new Potion(1);
		potions[0].setEquipped(true);
		saberUnlocked = false;
		katanaUnlocked = false;
		lUnlocked = true;
		mUnlocked = true;
		aUnlocked = true;
		eUnlocked = true;
		attackCounter = -1;
		selectedSpell = 1;
		fire = new AttackSpell("fire");
		lightning = new AttackSpell("lightning");
		repCount = 10;
    }
    
    //Increases all the stats when the character gains a level
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
    
    //method to regain health and mana 
    public void regenerate()
    {
    	if(repCount <= 0) {
    		repCount = 10;
	        if(getHealth() < getMaxHealth())
	        {
	            setHealth(getHealth() + 1);
	        }
	        
	        if(getMana() < getMaxMana())
	        {
	            setMana(getMana() + 1);
	        }
    	} else {
    		repCount--;
    	}
    }
    
    //method to add what the enemy droped to the player's inventory 
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
        	Game.getScreen().getGameGraphics().addString("You unlocked the Saber");
        	return;
        }
        if(d.equals("Katana")) {
        	setKatanaUnlocked(true);
        	Game.getScreen().getGameGraphics().addString("You unlocked the Katana");
        	return;
        }
    }
    
    //returns the potion that is currently equipped 
    public Potion getEquippedPotion() {
		if(potions[0].getEquipped())
			return potions[0];
		return potions[1];
	}
    
    //returns the name of the equipped potion 
    public String getEquippedPotionName() {
    	if(potions[0].getEquipped())
			return "Health";
		return "Mana";
    }
	
	//Method to change the selected potion 
	public void setSelectedPotion(String type) {
		if(type.equals("Health")) {
			potions[0].setEquipped(true);
			potions[1].setEquipped(false);
		} else {
			potions[0].setEquipped(false);
			potions[1].setEquipped(true);
		}
	}
	
	//Checks if the saber is unlocked 
	public boolean getSaberUnlocked() {return saberUnlocked;}
	
	//Checks if the katana is unlocked 
	public boolean getKatanaUnlocked() {return katanaUnlocked;}
	
	public void setSaberUnlocked(boolean s) {saberUnlocked = s;}
	
	public void setKatanaUnlocked(boolean k) {katanaUnlocked = k;}
	
	public int getAttackCount() {return attackCounter;}
	
	public void decrementAttackCount() {attackCounter--;}
	
	//Method returns where the sword is based on the direction the player is facing 
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
	
	//Stops the sword and the player from attacking 
	public void stopAttack() {
		getSword().setDim(null, 1);
		attackCounter = -1;
	}
    
    //Moves the player based on which key is pressed
    public void move(KeyEvent e) 
    {   
    	if(getEnabled()) {
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
		         else if(e.getKeyCode() == KeyEvent.VK_1 ||e.getKeyCode() == KeyEvent.VK_NUMPAD1)
		         {
		        	 selectedSpell = 1;
		         }
		         else if(e.getKeyCode() == KeyEvent.VK_2 ||e.getKeyCode() == KeyEvent.VK_NUMPAD2)
		         {
		        	 selectedSpell = 2;
		         }
		         else if(e.getKeyCode() == KeyEvent.VK_3 ||e.getKeyCode() == KeyEvent.VK_NUMPAD3)
		         {
		        	 selectedSpell = 3;
		         }
		         else if(e.getKeyCode() == KeyEvent.VK_4 ||e.getKeyCode() == KeyEvent.VK_NUMPAD4)
		         {
		        	 selectedSpell = 4;
		         }
		         else if(e.getKeyCode() == KeyEvent.VK_5 ||e.getKeyCode() == KeyEvent.VK_NUMPAD5)
		         {
		        	 selectedSpell = 5;
		         }
		         else if(e.getKeyCode() == KeyEvent.VK_6 ||e.getKeyCode() == KeyEvent.VK_NUMPAD6)
		         {
		        	 Game.getScreen().getGameGraphics().addString("You Can't Select That Spell");
		         }
	    	 }
    	}
         
    }
    
    //Method to attack either spell or sword depending on the click  
    public void attack(MouseEvent e)
    {
    	if(getEnabled()) {
	        if(e.getClickCount() == 1)
	        {
	            if(e.getButton() == MouseEvent.BUTTON1)
	            {
	                if(attackCounter < 0) {
	                	attackCounter = getSword().getSwingSpeed();
	                	getSword().setDim(setSwordDim(), getDirection());
	                }
	            }
	            if(e.getButton() == MouseEvent.BUTTON3)
	            {
	                cast(e.getLocationOnScreen());
	            }
	        }
    	}
    }
    
    //Adds experiance to the exp and checks if the player levels up 
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
    
    //Checks what spell is currently equipped 
    public AttackSpell getEquippedSpell() {
    	if(selectedSpell == 1)
    		return fire;
    	if(selectedSpell == 2)
    		return lightning;
    	return null;
    }
    
    //returns an integer based on what spell the player has selected 
    public int getSelectedSpell() {
    	return selectedSpell;
    }
	//Casts the spell that is currently selected 
	public void cast(Point p) {
		Buff b;
		switch (selectedSpell) {
			case 1:
				fire.use(p);
				break;
			case 2:
				lightning.use(p);
				break;
			case 3:
				if(getMana() > 10) {
					setMana(getMana() - 10);
					b = new Buff("speed");
					addBuff(b);
					b.add(this);
				}
				break;
			case 4:
				if(getMana() > 20) {
					setMana(getMana() - 20);
					b = new Buff("defense");
					addBuff(b);
					b.add(this);
				}
				break;
			case 5:
				if(getMana() > 15) {
					setMana(getMana() - 15);
					b = new Buff("attack");
					addBuff(b);
					b.add(this);
				}
				break;
			default:
				selectedSpell = 1;
				fire.use(p);
		}
	}
    
    //Forces the player to a certain position 
    public void forceTo(Point p) {
    	System.out.println(p.getX());
    	System.out.println(p.getY());
    	setDimensions((int)p.getX(), (int)p.getY(), (int)getDimensions().getWidth(), (int)getDimensions().getHeight());
    	System.out.println(getDimensions().getX());
    	System.out.println(getDimensions().getY());
    }
    
    //Decreases own health based on how much damage was dealt
    public void attacked(int a) {
	if((a - getDefense()) <= 0)
           	setHealth(getHealth()-1); 
         else
        	setHealth(getHealth() - a + getDefense());
	}
	
	//Makes sure that the character can attack another character
	public boolean canAttack(Character c) {
		if(getEnabled()) {
			if(attackCounter <= 0)
				return true;
		}
		return false;
	}
}
