
/**
 * Write a description of class Dump here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Item.Sword;
import Main.Game;
public class Dump extends Bosses 
{
    int numSpawn = 10; 
    public Dump(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Rectangle dim, Sword e,int direct)
    {
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,dim,e,direct);
    }
    
    public void spawnEnemies(ArrayList<Enemies> e, Character c){
    	final Timer timer = new Timer();
        timer.schedule(new TimerTask(){
           	public void run(){
           		
                e.add(new Enemies("Cultist", 50, 50, 50, 50, 8, 25, 2, 1, new Rectangle((int)(Math.random()* 1200 + 100), (int)(Math.random()* 700 + 100), 100, 100), new Sword(""), 1)); 	
                
		        if(c.getHealth() <= 0){
		        	timer.cancel();
		            timer.purge(); 
		        }
           	}
        }, 5000, 3000);
    } 
    
    public void setDirection(int d) {
    	return;
    }
    
    public boolean isDead() {
		if (getHealth() <= 0) {
			Game.getPlayer().getLoot((Enemies)this);
			Game.getPlayer().addExperiance();
			Game.getScreen().setMenu("Win");
			return true;
		}
		return false;
	}
}

