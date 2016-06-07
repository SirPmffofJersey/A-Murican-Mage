
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Character
{
    private int exp = 0;
    private int expLimit = 100;

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
}
        