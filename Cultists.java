import java.awt.Rectangle;

import Item.Sword;
import Spell.Buff;
public class Cultists extends Enemies
{
    private Buff b;
    
    public Cultists(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Rectangle dim, Sword e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,dim,e,direct);
        b = new Buff("stun");
    }
    
    public boolean canAttack(Character c)
    {
        int coordinateX = Math.abs((int)(c.getDimensions().getX() - super.getDimensions().getX()));
        int coordinateY = Math.abs((int)(c.getDimensions().getY() - super.getDimensions().getY()));
        int distance = coordinateX + coordinateY; 
        if(distance <= 200) 
        	if(getWait() <= 0)
        		return true;
        	else
        		decrementWait();
        return false;  
    }
    
    public void attack(Character c)
    {
        if(canAttack(c))
        {
        	setWait(5000);
        	c.attacked(super.getAttack());
			c.addBuff(b);
        } 
    }
}
