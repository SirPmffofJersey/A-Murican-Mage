import java.util.Timer;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.event.*;
import javax.swing.*;
import java.util.TimerTask;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Rectangle;
public class Cultists extends Enemies
{
    private static int spellAttack; 
    public Cultists(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Point loc, Rectangle dim, Item e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,loc,dim,e,direct);
    }


    public void castSpell(Character c)
    {
        c.attacked(super.getAttack());
    }
    
    public boolean canAttack(Character c)
    {
        int coordinateX = Math.abs((int)(c.getLocation().getX() - super.getLocation().getX()));
        int coordinateY = Math.abs((int)(c.getLocation().getY() - super.getLocation().getY()));
        int distance = coordinateX + coordinateY; 
        if(distance <= 200) 
            return true;
        return false;  
    }
    
    public void attack(Character c)
    {
        if(canAttack(c))
        {
            castSpell(c); 
        }
    }
    
    public void draw(Graphics g) 
    {
        if(super.getHealth() > 0)
        {
            g.fillOval((int)super.getLocation().getX(), (int)super.getLocation().getY(), 30,30);
        }
    }
}
