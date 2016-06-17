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
    private Obstacle[] objects; //RULES FOR THIS ARRAY: the first element is the door that would come from a lower number room, and if there is a second door in the room it is the second element in the array
    private Rectangle dimensions;
    private String imageName;    
    private int roomIndex;
    //different room sizes
    
    private static final Rectangle SIZE1 = new Rectangle(1000,1000);
    private static final Rectangle SIZE2 = new Rectangle(1500,500);
    private static final Rectangle SIZE3 = new Rectangle(1500,1000);
    private static final Rectangle SIZE4 = new Rectangle(700,700);
    
    //constructor(s)
    public Room(int rNum)
    {
     roomIndex = rNum;
     switch(roomIndex)
     {
      case 0: //Start room
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(1), new Obstacle(new Rectangle(600, 600, 100, 100), "obstacle")};
      objects[0].setLoc(600,300); //right door
      objects[1].setLoc(600, 600);
      imageName = "Room0";
      break;
      
      case 1: //Room 1
      dimensions = new Rectangle(SIZE2);
      objects = new Obstacle[]{new Doorway(0), new Doorway(2)};
      objects[0].setLoc(0, 200); //left door
      objects[1].setLoc(1400, 200); //top door
      imageName = "Room1";
      break;
      
      case 2: //Room 2
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(1), new Doorway(3)};
      objects[0].setLoc(200,600); //bottom door
      objects[1].setLoc(600,300); //right door
      imageName = "Room2";
      break;
      
      case 3: //Room 3
      dimensions = new Rectangle(SIZE1);
      objects = new Obstacle[]{new Doorway(2), new Doorway(4)};
      objects[0].setLoc(0,200); //left door
      objects[1].setLoc(900,200); //right door
      imageName = "Room3";
      break;
      
      case 4: //Room 4
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(3), new Doorway(5)};
      objects[0].setLoc(0,300); //left door
      objects[1].setLoc(300,0); //top door
      imageName = "Room4";
      break;
      
      case 5: //Room 5
      dimensions = new Rectangle(SIZE3);
      objects = new Obstacle[]{new Doorway(4), new Doorway(6)};
      objects[0].setLoc(700,900); //bottom door
      objects[1].setLoc(0,500); //left door
      imageName = "Room5";
      break;
      
      case 6: //Room 6
      dimensions = new Rectangle(SIZE2);
      objects = new Obstacle[]{new Doorway(5), new Doorway(7)};
      objects[0].setLoc(1400,200); //right door
      objects[1].setLoc(200,0); //top door
      imageName = "Room6";
      break;
      
      case 7: //Room 7
      dimensions = new Rectangle(SIZE1);
      objects = new Obstacle[]{new Doorway(6), new Doorway(8)};
      objects[0].setLoc(200,900); //bottom door
      objects[1].setLoc(900,200); //right door
      imageName = "Room7";
      break;
      
      case 8: //Room 8
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(7), new Doorway(9)};
      objects[0].setLoc(0,300); //left door
      objects[1].setLoc(300,0); //top door
      imageName = "Room8";
      break;
      
      case 9: //Room 9
      dimensions = new Rectangle(SIZE3);
      objects = new Obstacle[]{new Doorway(8)};
      objects[0].setLoc(700,900);
      imageName = "Room9";
      break;
      
      default: //if all else fails, make it the start room.
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(1)};
      objects[0].setLoc(400,700); //right door
      break;
     }     
    }
    
    //methods
    public Obstacle getObstacle(int index)
    {return objects[index];}
    
    public int getNumberOfObstacles()
    {return objects.length;}
            
    public Point getEnteredDoorLocation(Rectangle ricky) //purpose: so as to find out where to place the player
    {return ricky.getLocation();}
    
    public void enableDoors() 
    {
    	for(Obstacle o : objects)
    		if(o instanceof Doorway)
    			((Doorway) o).setEnabled(true);
    }
    
    public Rectangle getDim()
    {return dimensions;}    
    
    public String getImageName()
    {return imageName;}
}
