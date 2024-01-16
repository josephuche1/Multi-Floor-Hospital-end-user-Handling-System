import java.util.ArrayList;

public class ICURoom extends HospitalRoom{
	public ICURoom(String roomNumber, String floorNumber, boolean isAvailable) {
		super(roomNumber, isAvailable, floorNumber);
		this.roomType = "ICU";
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ICU Room, Room Number: "+ this.getRoomNumber();
	}
}
