
/**
 * Holds an array of rooms and holds which room the player is in.
 * 
 * @author Nathan Nash
 * @version (a version number or a date)
 */
public class Map {
   //fields
   private Room[] map; //array of rooms in the game
   private int playerRoomLoc; //current number room the player is in
   
   //constructor
   public Map()
   {
    map = new Room[10];
    for(int i = 0; i < map.length; i++)
    	map[i] = new Room(i); //creates all the proper rooms
   }
   
   //methods
   public Room getRoom(int roomIndex) //returns the room that is asked for
   {return map[roomIndex];}
   
   public int getPlayerRoomLoc() //returns which number room the player is in
   {return playerRoomLoc;}
   
   public Room getCurrentRoom() {
	   return map[playerRoomLoc];
   }

   public void setCurrentRoom(int roomSendingToIndex) { //changes which room number the player is in
		playerRoomLoc = roomSendingToIndex;
   }
}
