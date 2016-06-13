
/**
 * Holds an array of rooms and holds which room the player is in.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map
{
   //fields
   private Room[] map;
   private int currentRoomIndex;
   
   //constructor
   public Map()
   {
    map = new Room[]{}
   }
   
   //methods
   public Room getRoom(int roomIndex)
   {return map[roomIndex];}
   
    public int getCurrentRoomIndex()
   {return currentRoomIndex;}
}
