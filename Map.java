
/**
 * Holds an array of rooms and holds which room the player is in.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map {
   //fields
   private Room[] map;
   private int playerRoomLoc;
   
   //constructor
   public Map()
   {
    map = new Room[10];
    for(int i = 0; i < map.length; i++)
    	map[i] = new Room(i);
   }
   
   //methods
   public Room getRoom(int roomIndex)
   {return map[roomIndex];}
   
   public int getPlayerRoomLoc()
   {return playerRoomLoc;}
   
   public Room getCurrentRoom() {
	   return map[playerRoomLoc];
   }

   public void setCurrentRoom(int roomSendingToIndex) {
		playerRoomLoc = roomSendingToIndex;
   }
}
