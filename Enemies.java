import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import Item.Sword;
/**
 * Write a description of class Enemies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemies extends Character
{
    private boolean attacked = false; 
    private int facing = 1; 

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
        return "Health Potion"; 
    }
}
