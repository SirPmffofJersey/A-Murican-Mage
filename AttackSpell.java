
/**
 * Write a description of class AttackSpell here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackSpell
{
    private final String NAME;
    private final int RANGE;
    private final int DAMAGE;
    private final int COST;
    private final double COOL_DOWN_LENGTH;
    private boolean coolingDown;
    
    public AttackSpell(String name)
    {
     if(name.equals("fire"))
     {
      NAME="Fire Lobber";
      RANGE=5;
      DAMAGE=20;
      COST=10;
      COOL_DOWN_LENGTH=0.5;      
     }
     else
     {
      NAME="Lightning Strike";
      RANGE=4;
      DAMAGE=30;
      COST=15;
      COOL_DOWN_LENGTH=0.75;      
     }
     
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
    
    public boolean isCoolingDown()
    { return coolingDown;}
    
}
