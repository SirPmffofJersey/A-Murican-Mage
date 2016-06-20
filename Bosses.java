
/**
 * Abstract class Bosses - Skeleton class for Dump and Crunch
 * 
 * @author Will Roberts
 * @version 2  6/17/16
 */
import java.awt.Rectangle;
import java.util.ArrayList;

import Item.Sword;
public abstract class Bosses extends Enemies 
{
    // Calls super constructor from Enemies
    public Bosses(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Rectangle dim, Sword e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,dim,e,direct);
    }
    
    //Abstract method for each boss to spawn there own enemies
    public abstract void spawnEnemies(ArrayList<Enemies> e, Character c); 
}
