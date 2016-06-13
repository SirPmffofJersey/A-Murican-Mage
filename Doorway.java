import java.awt.*;
/**
 * Write a description of class Doorway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Doorway extends Obstacle
{
    //fields    
    private int roomSendingToIndex;    
    
    //constructor
    public Doorway(int sendingToIndex)
    {
     super(new Rectangle(100,100), "Doorway disabled.gif");
     roomSendingToIndex = sendingToIndex;
    }
    
    //methods    
    public int getRoomSendingToIndex()
    {return roomSendingToIndex;}
       

    public void travel()
    {
     
    }

}
