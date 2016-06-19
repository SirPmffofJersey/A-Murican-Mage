
/**
 * Write a description of class Buff here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Timer;
import java.util.TimerTask;

import Main.Game;

public class Buff
{
    //fields
    private String name;
    private String affectedStat;
    private int duration;
    private int severity;
    private Charactor.Character affected;
    
    //constructor
    public Buff(String n) {
    	name = n;
    	if(name.equals("burn")) {
    		affectedStat = "health";
    		duration = 5;
    		severity = 5;
    	} else if(name.equals("stun")) {
    		affectedStat = "enabled";
    		duration = 2;
    		severity = 5;
    	} else if(name.equals("speed")) {
    		affectedStat = "speed";
    		duration = 5;
    		severity = 10;
    	} else if(name.equals("defense")) {
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
    public String getName()
    {return name;}
    
    public String getAffected()
    {return affectedStat;}
    
    public int getDuration()
    {return duration;}
    
    public int getSeverity()
    {return severity;}
    
    public void add(Charactor.Character c) {
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
    
    private void remove() {
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
