
/**
 * Write a description of class Dump here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Point;
import java.awt.Rectangle;
public class Dump extends Bosses 
{
    int numSpawn = 10; 
    public Dump(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Point loc, Rectangle dim, Item e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,loc,dim,e,direct);
    }
    
    public void spawnEnemies()
    {
        for(int i = 0; i < numSpawn; i++) 
        {
            //spawnEnemies method 
        }
    }
}
