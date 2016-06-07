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
    
    //constructor(s)
    public Room(Obstacle[] objs, Rectangle dim, String imgName, int rNum)
    {
     for(int i = 0; i<objs.length; i++)
      objects[i] = objs[i];     
     dimensions = new Rectangle(dim);
     imageName = imgName;
     roomNumber = rNum;
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
