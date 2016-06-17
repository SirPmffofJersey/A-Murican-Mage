
/**
 * Abstract class Bosses - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
import java.awt.Point;
import java.awt.Rectangle;
public abstract class Bosses extends Enemies 
{
    public Bosses(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Point loc, Rectangle dim, Item e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,loc,dim,e,direct);
    }
    
    public abstract void spawnEnemies(); 
}
