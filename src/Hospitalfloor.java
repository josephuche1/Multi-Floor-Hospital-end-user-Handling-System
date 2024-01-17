import java.io.Serializable;
import java.util.ArrayList;

public class HospitalFloor implements Serializable{
   private String floorNumber;
   private ArrayList<HospitalRoom> rooms;
   private int roomCount;
   
   //constructors
   public HospitalFloor() {
	   this.floorNumber = "";
	   this.rooms = new ArrayList<HospitalRoom>();
	   roomCount =0;
   }
   public HospitalFloor(String floorNumber) {
	   this.floorNumber = floorNumber;
	   this.rooms = new ArrayList<HospitalRoom>();
	   roomCount =0;
   }
   public HospitalFloor(String floorNumber, ArrayList<HospitalRoom> rooms) {
	   this.floorNumber = floorNumber;
	   this.rooms = new ArrayList<HospitalRoom>();
	   
	   for(HospitalRoom room : rooms) {
		   this.rooms.add(room);
	   }
	   roomCount = rooms.size();
   }
   
   //public instance methods
   public String getFloorNumber() {
	   return this.floorNumber;
   }
   public ArrayList<HospitalRoom> getRooms(){
	   return this.rooms;
   }
   public void addRoom(HospitalRoom room) {
	   this.rooms.add(room);
	   roomCount++;
   }
   
   
   @Override
   public String toString() {
	   return "Floor Number: "+ this.floorNumber + ", Number of rooms: " +this.rooms.size();
   }
}
