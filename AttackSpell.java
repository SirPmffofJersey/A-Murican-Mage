
/**
 * Write a description of class AttackSpell here.
 * 
 * @Nathan Nash
 * @version (a version number or a date)
 */
import java.awt.Point;

import Item.Projectile;
import Main.Game;

public class AttackSpell
{
    private final String NAME;
    private final int RANGE;
    private final int DAMAGE;
    private final int COST;
    private final double COOL_DOWN_LENGTH;
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
    
    public void createProjectile(Point p) {
    	Game.addProjectile(new Projectile(this, p));
    }
    
    public String getName()
    { return NAME;}
    
    public int getRange()
    { return RANGE;}
    
    public int getDamage()
    { return DAMAGE;}
    
    public int getCost()
    { return COST;}
        
    public double getCoolDownLength()
    { return COOL_DOWN_LENGTH;}
    
    public int getCoolDown()
    { return coolDown;}
    
    public void coolDown()
    {coolDown--;}
    
}
