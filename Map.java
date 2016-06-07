
/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map
{
   //fields
   private Room[] map;
   private int playerRoomLoc;
   
   //constructor
   
   
   //methods
   public Room getRoom(int roomIndex)
   {return map[roomIndex];}
   
   public int getPlayerRoomLoc()
   {return playerRoomLoc;}
}
