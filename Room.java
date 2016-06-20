import java.awt.*;

/**
 * Write a description of class Room here.
 * 
 * @author Nathan Nash
 * @version (a version number or a date)
 */
public class Room
{
    //fields    
    private Obstacle[] objects; //RULES FOR THIS ARRAY: the first element is the door that would come from a lower number room, and if there is a second door in the room it is the second element in the array
    private Rectangle dimensions; //bounds and location of the room
    private String imageName; //name of the image that should be displayed
    private int roomIndex; //index of the room
    
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
      objects = new Obstacle[]{new Doorway(1), new Obstacle(new Rectangle(100, 100), "obstacle"), new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle")};
      objects[0].setLoc(600,300); //right door
      objects[1].setLoc(600, 600); //rest are just normal obstacles
      objects[2].setLoc(600,0);
      objects[3].setLoc(0, 0);
      objects[4].setLoc(0,600);
      imageName = "Room0";
      break;
      
      case 1: //Room 1
      dimensions = new Rectangle(SIZE2);
      objects = new Obstacle[]{new Doorway(0), new Doorway(2),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle")};
      objects[0].setLoc(0, 200); //left door
      objects[1].setLoc(1400, 200); //top door
      objects[2].setLoc(100, 200); //rest are just normal obstacles
      objects[3].setLoc(700, 400);
      objects[4].setLoc(700, 0);
      imageName = "Room1";
      break;
      
      case 2: //Room 2
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(1), new Doorway(3),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle")};
      objects[0].setLoc(200,600); //bottom door
      objects[1].setLoc(600,300); //right door
      objects[2].setLoc(350, 350); //rest are just normal obstacles
      objects[3].setLoc(250,350);
      objects[4].setLoc(350, 250);
      imageName = "Room2";
      break;
      
      case 3: //Room 3
      dimensions = new Rectangle(SIZE1);
      objects = new Obstacle[]{new Doorway(2), new Doorway(4),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle")};
      objects[0].setLoc(0,200); //left door
      objects[1].setLoc(900,200); //right door
      objects[2].setLoc(100,100); //rest are just normal obstacles
      objects[3].setLoc(100,300);
      objects[4].setLoc(800,100);
      objects[5].setLoc(800,300);
      objects[6].setLoc(400,700);
      objects[7].setLoc(500,700);
      objects[8].setLoc(600,700);
      objects[9].setLoc(300,700);
      imageName = "Room3";
      break;
      
      case 4: //Room 4
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(3), new Doorway(5),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle")};
      objects[0].setLoc(0,300); //left door
      objects[1].setLoc(300,0); //top door
      objects[2].setLoc(300,300); //rest are just normal obstacles
      objects[3].setLoc(200,300);
      objects[4].setLoc(300,200);
      imageName = "Room4";
      break;
      
      case 5: //Room 5
      dimensions = new Rectangle(SIZE3);
      objects = new Obstacle[]{new Doorway(4), new Doorway(6),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle")};
      objects[0].setLoc(700,900); //bottom door
      objects[1].setLoc(0,500); //left door
      objects[2].setLoc(0,0); //rest are just normal obstacles
      objects[3].setLoc(1000, 0);
      objects[4].setLoc(0, 900);
      objects[5].setLoc(1000,900);
      objects[6].setLoc(200, 150);
      objects[7].setLoc(400, 300);
      objects[8].setLoc(800, 150);
      objects[9].setLoc(600, 300);
      objects[10].setLoc(200, 750);
      objects[11].setLoc(400, 500);
      objects[12].setLoc(600, 500);
      objects[13].setLoc(800,700);
      imageName = "Room5";
      break;
      
      case 6: //Room 6
      dimensions = new Rectangle(SIZE2);
      objects = new Obstacle[]{new Doorway(5), new Doorway(7),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle")};
      objects[0].setLoc(1400,200); //right door
      objects[1].setLoc(200,0); //top door
      objects[2].setLoc(400,100); //rest are just normal obstacles
      objects[3].setLoc(400, 200);
      objects[4].setLoc(1000, 200);
      objects[5].setLoc(1000, 300);
      imageName = "Room6";
      break;
      
      case 7: //Room 7
      dimensions = new Rectangle(SIZE1);
      objects = new Obstacle[]{new Doorway(6), new Doorway(8),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle")};
      objects[0].setLoc(200,900); //bottom door
      objects[1].setLoc(900,200); //right door
      objects[2].setLoc(200,450); //rest are just normal obstacles
      objects[3].setLoc(450,450);
      objects[4].setLoc(700,450);
      objects[5].setLoc(450,800);
      objects[6].setLoc(450,100);
      imageName = "Room7";
      break;
      
      case 8: //Room 8
      dimensions = new Rectangle(SIZE4);
      objects = new Obstacle[]{new Doorway(7), new Doorway(9),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle"),new Obstacle(new Rectangle(100, 100), "obstacle")};
      objects[0].setLoc(0,300); //left door
      objects[1].setLoc(300,0); //top door
      objects[2].setLoc(300,200);
      objects[3].setLoc(300,300);
      objects[4].setLoc(300,400);
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
    public Obstacle getObstacle(int index) //returns the obstacle within the objects array
    {return objects[index];}
    
    public int getNumberOfObstacles()
    {return objects.length;}
            
    public Point getEnteredDoorLocation(Rectangle ricky) //purpose: so as to find out where to place the player
    {return ricky.getLocation();}
    
    public void enableDoors() //if all the enemies in the room are dead, all the doorways become enabled
    {
    	for(Obstacle o : objects)
    		if(o instanceof Doorway)
    			((Doorway) o).setEnabled(true);
    }
    
    public void disableDoors() //if there are enemies in the room, all the doorways are disabled
    {
    	for(Obstacle o : objects)
    		if(o instanceof Doorway)
    			((Doorway) o).setEnabled(false);
    }
    
    public Rectangle getDim() //returns the 'dimensions' field
    {return dimensions;}    
    
    public String getImageName() //returns the name of the image that should be displayed
    {return imageName;}
}
