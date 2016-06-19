
/**
 * Abstract class Bosses - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
import java.awt.Rectangle;
import java.util.ArrayList;

import Item.Sword;
public abstract class Bosses extends Enemies 
{
    public Bosses(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Rectangle dim, Sword e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,dim,e,direct);
    }
    
    public abstract void spawnEnemies(ArrayList<Enemies> e, Character c); 
}
