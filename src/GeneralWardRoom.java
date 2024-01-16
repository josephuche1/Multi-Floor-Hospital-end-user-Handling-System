import java.util.ArrayList;

public class GeneralWardRoom extends HospitalRoom{
	public GeneralWardRoom(String roomNumber, String floorNumber, boolean isAvailable) {
		super(roomNumber, isAvailable, floorNumber);
		this.roomType = "General Ward";
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "General Ward, Room Number: "+ this.getRoomNumber();
	}
}
