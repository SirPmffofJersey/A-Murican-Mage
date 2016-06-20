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
    private int roomSendingToIndex; //the room that this door is sending the player to
    private boolean enabled; //whether or not the player can be sent anywhere yet by the door
    
    //constructor
    public Doorway(int sendingToIndex)
    {
     super(new Rectangle(100,100), "Doorway disabled");
     roomSendingToIndex = sendingToIndex;
     enabled = false;
    }
    
    //methods    
    public int getRoomSendingToIndex() //returns which room this doorway sends the player to
    {return roomSendingToIndex;}
    
    public boolean getEnabled() //returns whether or not the player can travel using this door
    {return enabled;}
    
    public void setEnabled(boolean e) //changes the enabled state of the doorway
    {
    	if(e)
    		setImage("Doorway enabled");
    	else
    		setImage("Doorway disabled");
    	enabled = e;
    }

    public void travel() //sends the player to the room that doorway sends to
    {
     Game.getMap().getCurrentRoom().disableDoors();
     Game.getMap().setCurrentRoom(roomSendingToIndex);
     System.out.println("Forcing the player to move");
     Game.getPlayer().forceTo(new Point(100, 100));
     Game.generateEnemies();
    }
}
