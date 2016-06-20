import java.awt.Point;
import java.awt.Rectangle;

import Main.Game;
/**
 * Write a description of class Doorway here.
 * 
 * @author Nathan Nash
 * @version (a version number or a date)
 */
public class Doorway extends Obstacle
{
    //fields    
    private int roomSendingToIndex;
    private boolean enabled;
    
    //constructor
    public Doorway(int sendingToIndex)
    {
     super(new Rectangle(100,100), "Doorway disabled");
     roomSendingToIndex = sendingToIndex;
     enabled = false;
    }
    
    //methods    
    public int getRoomSendingToIndex()
    {return roomSendingToIndex;}
    
    public boolean getEnabled() 
    {return enabled;}
    
    public void setEnabled(boolean e)
    {
    	if(e)
    		setImage("Doorway enabled");
    	else
    		setImage("Doorway disabled");
    	enabled = e;
    }

    public void travel()
    {
     Game.getMap().getCurrentRoom().disableDoors();
     Game.getMap().setCurrentRoom(roomSendingToIndex);
     System.out.println("Forcing the player to move");
     Game.getPlayer().forceTo(new Point(100, 100));
     Game.generateEnemies();
    }
}
