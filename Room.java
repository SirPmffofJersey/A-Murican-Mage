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
      case 0: //Start room
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(1)};
      objects[0].setLoc(3*40,6*40); //right door
      break;
      
      case 1: //Room 1
      dimensions = new Rectangle(SIZE2);
      objects = new Obstacle[]{new Doorway(0), new Doorway(2)};
      objects[0].setLoc(2*40,0); //left door
      objects[1].setLoc(2*40,11*40); //top door
      break;
      
      
     }
    }
    
    //methods
    public Obstacle getObstacle(int index)
    {return objects[index];}
            
    public Point getEnteredDoor(Point p) //purpose: so as to find out where to place the player
    {return }
    
    public Rectangle getDim()
    {return dimensions;}    
    
}
