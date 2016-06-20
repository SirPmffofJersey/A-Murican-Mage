
/**
 * Dump CLass: 
 * This is the class for the final boss Tronald Dump, 
 * it includes the special methods that he uses, like 
 * the spawn enemies method. 
 * 
 * @author Jimmy Greaves
 * @version 2.3 6/18/16 
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
    
    //Method to spawn cultists every 3 seconds 
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
    
    //Method to check if Dump's dead and displays the win screen 
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

