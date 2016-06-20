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

/**
 * RiotOfficer Class:
 * The RiotOfficer class is a specific type of enemy that the only special
 * thing about it, is that they take half damage.
 * 
 * @author Jimmy Greaves
 * @version 3.14 6/15/16
 */

public class RiotOfficer extends Enemies 
{

    /**
     * Constructor for objects of class RiotOfficer
     */
    public RiotOfficer(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Point loc, Rectangle dim, Item e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,loc,dim,e,direct);
    }
    
    //Has the riot officer only take half the damage of an attack
    public void attacked(Character c)
    {
        super.attacked((c.getAttack() / 2));
    }
    

}
