
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
    map = new Room[]{new Room(0), new Room(1), new Room(2), new Room(3),
                     new Room(4), new Room(5), new Room(6), new Room(7),
                     new Room(8), new Room(9)};
    currentRoom = 0;                 
   }
   
   //methods
   public Room getRoom(int roomIndex)
   {return map[roomIndex];}
   
   public Room getCurrentRoom()
   {
    return map[getCurrentRoomIndex()];
   }
   
    public int getCurrentRoomIndex()
   {return currentRoomIndex;}
}
