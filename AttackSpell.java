
/**
 * Write a description of class AttackSpell here.
 * 
 * @author Nathan Nash
 * @version (a version number or a date)
 */
import java.awt.Point;

import Item.Projectile;
import Main.Game;

public class AttackSpell
{
    private final String NAME; //Name of the spell
    private final int RANGE; //distance the spell goes
    private final int DAMAGE; //damage the spell does
    private final int COST; //how much mana it costs to use the spell
    private final double COOL_DOWN_LENGTH; //how long it takes before you can use the spell again
    private int coolDown; 
    public AttackSpell(String name)
    {
     if(name.equals("fire"))
     {
      NAME="Fire Lobber";
      RANGE=250;
      DAMAGE=20;
      COST=10;
      COOL_DOWN_LENGTH=5;      
     }
     else
     {
      NAME="Lightning Strike";
      RANGE=500;
      DAMAGE=50;
      COST=15;
      COOL_DOWN_LENGTH=8;      
     }
     
    }
    
    public boolean use(Point p) {
    	if(Game.getPlayer().getMana() > COST && coolDown <= 0) {
    		Game.getPlayer().setMana(Game.getPlayer().getMana() - COST);
    		coolDown += 5;
    		createProjectile(p);
    		return true;
    	}
    	return false;
    }
    
    public void createProjectile(Point p) { //makes a projectile that contains the information about the spell
    	Game.addProjectile(new Projectile(this, p));
    }
    
    public String getName() //returns the name of the spell
    { return NAME;}
    
    public int getRange() //returns the range of the spell
    { return RANGE;}
    
    public int getDamage() //returns the damage of the spell
    { return DAMAGE;}
    
    public int getCost() //returns the mana cost of the spell
    { return COST;}
        
    public double getCoolDownLength() //returns the time it takes before you can use the spell
    { return COOL_DOWN_LENGTH;}
    
    public int getCoolDown()
    { return coolDown;}
    
    public void coolDown()
    {coolDown--;}
    
}
