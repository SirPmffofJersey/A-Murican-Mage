
/**
 * Write a description of class Doorway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Doorway extends Obstacle
{
    //fields
    private int roomNextIndex;
    private int roomPreviousIndex;
    private Rectangle dimensions;
    
    //constructor
    public Doorway()
    {
     
    }
    
    //methods
    public int getRoomNextIndex()
    {return roomNextIndex;}     
    
    public int getRoomPreviousIndex()
    {return roomPreviousIndex;}
    
    public void setRooms(int nextIndex, int prevIndex)
    {
     roomNextIndex = nextIndex;
     roomPreviousIndex = prevIndex;
    }
}
