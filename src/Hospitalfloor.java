import java.util.ArrayList;

public class HospitalFloor {
   private String floorNumber;
   private ArrayList<HospitalRoom> rooms;
   
   //constructors
   public HospitalFloor() {
	   this.floorNumber = "";
	   this.rooms = new ArrayList<HospitalRoom>();
	   
   }
   public HospitalFloor(String floorNumber) {
	   this.floorNumber = floorNumber;
	   this.rooms = new ArrayList<HospitalRoom>();
   }
   public HospitalFloor(String floorNumber, ArrayList<HospitalRoom> rooms) {
	   this.floorNumber = floorNumber;
	   this.rooms = new ArrayList<HospitalRoom>();
	   
	   for(HospitalRoom room : rooms) {
		   this.rooms.add(room);
	   }
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
   }
   
   @Override
   public String toString() {
	   return "Floor Number: "+ this.floorNumber + ", Number of rooms: " +this.rooms.size();
   }
}
