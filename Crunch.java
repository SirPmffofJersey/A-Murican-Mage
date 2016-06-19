/**
 * <h1> Crunch Class: </h1>
 * The Crunch class is the class that deasl with our mini-boss Captain Crunchstie. 
 * It holds the Constructor and 
 * Crunshtie's mini-boss ability to generate Police Officers.
 * 
 * @author Will Roberts
 * @version 3
 * @since 6/17/16
 */
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Item.Sword;
import Main.Game;
public class Crunch extends Bosses 
{
	
    public Crunch(String name, int hp, int mp, int maxHp, int maxMp,int atk, int def, int spd, int lvl, Rectangle dim, Sword e,int direct)
    {
    	
        super(name,hp,mp,maxHp,maxMp,atk,def,spd,lvl,dim,e,direct);
    }
    
    public String dropLoot() {
    	return "Saber";
    }
    
    public boolean isDead() {
		if (getHealth() <= 0) {
			Game.getPlayer().getLoot((Enemies)this);
			Game.getPlayer().addExperiance();
			Game.getScreen().getGameGraphics().addString("You Beat Cap'n Crunchstie");
			Game.getScreen().getGameGraphics().addString("You unlocked the saber");
			return true;
		}
		return false;
	}
    
    public void spawnEnemies(ArrayList<Enemies> e, Character c){
    	final Timer timer = new Timer();
        timer.schedule(new TimerTask(){
           	public void run(){
           		
                e.add(new Enemies("Police officer", 50, 50, 50, 50, 5, 20, 2, 1, new Rectangle((int)(Math.random()* 1200 + 100), (int)(Math.random()* 700 + 100), 100, 100), new Sword(""), 1)); 	
           	
		        if(c.getHealth() <= 0){
		        	timer.cancel();
		            timer.purge(); 
		        }
           	}
        }, 2000, 2000);
        
        
    }
}
