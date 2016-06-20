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

    // calls super constructor
    public Enemies(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Rectangle dim, Sword s,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,dim,s,direct);
    }
    
    //if enemy hits a wall it doesn't move
    public void dontMove()
    {
        super.setLocation(new Point((int)super.getDimensions().getX() ,(int)super.getDimensions().getY())); 
        super.hitWall(); 
    } 
    
    public void draw(Graphics g) 
    {
        if(super.getHealth() > 0)
            g.fillOval((int)super.getDimensions().getX(),(int)super.getDimensions().getY(),50,50); 
    }
    
    // Drops a random loot based ona random number from 1-100
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
