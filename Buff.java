
/**
 * Write a description of class Buff here.
 * 
 * @author Nathan Nash 
 * @version (a version number or a date)
 */
import java.util.Timer;
import java.util.TimerTask;

import Main.Game;

public class Buff
{
    //fields
    private String name; //name of the buff
    private String affectedStat; //stat that the buff affects
    private int duration; //how long the buff lasts
    private int severity; //the magnitude of the buff
    private Charactor.Character affected;
    
    //constructor
    public Buff(String n) {
    	name = n;
    	if(name.equals("burn")) { //Burning debuff
    		affectedStat = "health";
    		duration = 5;
    		severity = 5;
    	} else if(name.equals("stun")) { //stunned debuff
    		affectedStat = "enabled";
    		duration = 2;
    		severity = 5;
    	} else if(name.equals("speed")) { //"Magic" Powder buff
    		affectedStat = "speed";
    		duration = 5;
    		severity = 10;
    	} else if(name.equals("defense")) { //Armor Plated Armor buff
    		affectedStat = "defense";
    		duration = 5;
    		severity = 10;
    	} else if(name.equals("attack")) {
    		affectedStat = "attack";
    		duration = 5;
    		severity = 10;
    	}
    }
    
    //methods
    public String getName() //returns the name of the buff
    {return name;}
    
    public String getAffected() //returns the stat that the buff changes
    {return affectedStat;}
    
    public int getDuration() //returns how long the buff lasts
    {return duration;}
    
    public int getSeverity() //returns the magnitude of the buff
    {return severity;}
    
    public void add(Charactor.Character c) { //adds to stats
    	affected = c;
    	Game.getScreen().getGameGraphics().addString("You gave the " + c.getName() + " a " + name + " buff");
    	final Timer timer = new Timer();
        timer.schedule(new TimerTask(){
       		private int seconds = 0;
           	public void run()
           	{
               	if(affectedStat.equals("health")) {
               		affected.setHealth(affected.getHealth() - severity);
               	} else if(affectedStat.equals("enabled")) {
               		affected.setEnabled(false);
               	} else if(affectedStat.equals("speed")) {
               		if(seconds == 0)
               			affected.setSpeed(affected.getSpeed() + severity);
               	} else if(affectedStat.equals("defense")) {
               		if(seconds == 0)
               			affected.setDefense(affected.getDefense() + severity);
               	} else if(affectedStat.equals("attack")) {
               		if(seconds == 0)
               			affected.setAttack(affected.getAttack() + severity);
               	}
               	seconds++; 
               	if(seconds >= duration)
                {
               		seconds = 0;
               		remove();
                    timer.cancel();
                    timer.purge(); 
                }
            }
        }, 1000, 1000);
    }
    
    private void remove() { //lowers stats
    	if(affectedStat.equals("speed")) {
       			affected.setSpeed(affected.getSpeed() - severity);
       	} else if(affectedStat.equals("defense")) {
       			affected.setDefense(affected.getDefense() - severity);
       	} else if(affectedStat.equals("attack")) {
       			affected.setAttack(affected.getAttack() - severity);
       	} else if(affectedStat.equals("enabled")) {
       		affected.setEnabled(true);
       	}
    	affected.removeBuff(this);
    }
    
}
