import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import Item.Sword;
/**
 * <h1> Enemies Class: </h1>
 * The Enemies class is the super class that contains 
 * the basis for all enemies in the game. That includes droping loot,
 * as well as, knowing when and when not to move.
 * 
 * @author Will Roberts
 * @version 4
 * @since 6/6/17
 */
public class Enemies extends Character
{
    private boolean attacked = false; 

    public Enemies(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Rectangle dim, Sword s,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,dim,s,direct);
    }
    
    public void dontMove()
    {
        super.setLocation(new Point((int)super.getDimensions().getX() ,(int)super.getDimensions().getY())); 
        super.hitWall(); 
    } 
    
    public void draw(Graphics g) 
    {
        if(super.getHealth() > 0)
            g.fillOval((int)super.getDimensions().getX(),(int)super.getDimensions().getY(),50,50); 
            
        /*if(attacked)
        {
            if(facing == 3)
                g.fillOval(x - 100,y,50,50); 
            else if(facing == 2) 
                g.fillOval(x + 100, y, 50,50);
            else if(facing == 4)
                g.fillOval(x,y + 100, 50,50); 
            else 
                g.fillOval(x,y - 100, 50, 50); 
        } */
    }
    
    public String dropLoot()
    {
    	int r = (int)(Math.random() * 100);
    	if(r < 20)
    		return "Nothing";
    	else if(r < 60)
    		return "Health Potion";
    	else if(r < 90)
    		return "Mana Potion";
    	else if(r == 100)
    		return "Katana";
        return "Nothing"; 
    }
}
