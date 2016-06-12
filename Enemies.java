import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Rectangle;
/**
 * Write a description of class Enemies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Character
{
    private boolean attacked = false; 
    private int facing = 1; 

    public Enemy(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Point loc, Rectangle dim, Item e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,loc,dim,e,direct);
    }
    
    public void dontMove()
    {
        super.setLocation((int)super.getLocation().getX() ,(int)super.getLocation().getY() ); 
        super.hitWall(); 
    } 
    
    public void draw(Graphics g) 
    {
        if(super.getHealth() > 0)
            g.fillOval((int)super.getLocation().getX(),(int)super.getLocation().getY(),50,50); 
            
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
    
    public Item dropLoot()
    {
        return new Item(); 
    }
}
