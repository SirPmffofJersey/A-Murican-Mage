import java.awt.*;

/**
 * Write a description of class Room here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Room
{
    //fields    
    private Obstacle[] objects;
    private Rectangle dimensions;
    private String imageName;    
    private int roomIndex;
    //different room sizes
    
    private static final Rectangle SIZE1 = new Rectangle(10,10);
    private static final Rectangle SIZE2 = new Rectangle(15,5);
    private static final Rectangle SIZE3 = new Rectangle(15,10);
    private static final Rectangle SIZE4 = new Rectangle(7,7);
    
    //constructor(s)
    public Room(int rNum)
    {
     roomIndex = rNum;
     switch(roomIndex)
     {
      case 0:
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(1)};
      objects[0].setLoc(3*40,6*40);
      
     }
    }
    
    //methods
    public Obstacle getObstacle(int index)
    {}
    
    public Obstacle getCollidingObject(Point pos)
    {}
    
    public Obstacle getCollidingObject(Rectangle dim)
    {}
    
    public Doorway getDoor(int index)
    {}
    
    public Doorway getEnteredDoor(Point pos)
    {}
    
    public Doorway getEnteredDoor(Rectangle dim)
    {}
    
    public Rectangle getDim()
    {return dimensions;}
    
    
}
