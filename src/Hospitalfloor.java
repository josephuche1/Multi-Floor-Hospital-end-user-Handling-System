import java.io.Serializable;
import java.util.ArrayList;

/**
 * HospitalFloor is a class that represents a floor within a hospital.
 * It contains a list of rooms on the floor and tracks the number of rooms.
 * This class is responsible for managing the rooms and their count on a particular floor.
 * It implements Serializable to allow floor data to be saved and restored as needed.
 * 
 * @author Uche Joseph
 * @version 1.0
 */
public class HospitalFloor implements Serializable{
   private String floorNumber;
   private ArrayList<HospitalRoom> rooms;
   private int roomCount;
   
   /**
    * Default constructor that initializes a new HospitalFloor with an empty floor number and room list.
    */
   public HospitalFloor() {
	   this.floorNumber = "";
	   this.rooms = new ArrayList<HospitalRoom>();
	   roomCount =0;
   }
   
   /**
    * Constructor that initializes a new HospitalFloor with the specified floor number and an empty room list.
    *
    * @param floorNumber The floor number to be assigned to the floor.
    */
   public HospitalFloor(String floorNumber) {
	   this.floorNumber = floorNumber;
	   this.rooms = new ArrayList<HospitalRoom>();
	   roomCount =0;
   }
   
   /**
    * Constructor that initializes a new HospitalFloor with the specified floor number and room list.
    *
    * @param floorNumber The floor number to be assigned to the floor.
    * @param rooms       The initial list of rooms on the floor.
    */
   public HospitalFloor(String floorNumber, ArrayList<HospitalRoom> rooms) {
	   this.floorNumber = floorNumber;
	   this.rooms = new ArrayList<HospitalRoom>();
	   
	   for(HospitalRoom room : rooms) {
		   this.rooms.add(room);
	   }
	   roomCount = rooms.size();
   }
   
   /**
    * Retrieves the floor number of this HospitalFloor.
    *
    * @return The floor number as a string.
    */
   public String getFloorNumber() {
	   return this.floorNumber;
   }
   
   /**
    * Retrieves the list of rooms on this HospitalFloor.
    *
    * @return An ArrayList of HospitalRoom objects.
    */
   public ArrayList<HospitalRoom> getRooms(){
	   return this.rooms;
   }
   
   /**
    * Adds a room to the list of rooms on this floor and increments the room count.
    *
    * @param room The HospitalRoom to add to the floor.
    */
   public void addRoom(HospitalRoom room) {
	   this.rooms.add(room);
	   roomCount++;
   }
   
   /**
    * Retrieves the count of rooms on this HospitalFloor.
    *
    * @return The number of rooms as an integer.
    */
   public int getRoomCount() {
	   return this.roomCount;
   }
   
   /**
    * Provides a string representation of the HospitalFloor, including the floor number and the number of rooms.
    *
    * @return A string detailing the floor number and room count.
    */
   @Override
   public String toString() {
	   return "Floor Number: "+ this.floorNumber + ", Number of rooms: " +this.rooms.size();
   }
}
