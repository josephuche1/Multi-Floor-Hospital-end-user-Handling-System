import java.util.ArrayList;

public abstract class HospitalRoom {
	private String roomNumber;
	private ArrayList<HospitalEquipment> equipments;
	
	// Constructors
	public  HospitalRoom() {
		this.roomNumber = "";
		this.equipments = new ArrayList<HospitalEquipment>();
	}
	public HospitalRoom(String roomNumber) {
		this.roomNumber = roomNumber;
		this.equipments = new ArrayList<HospitalEquipment>();    
	}
	public HospitalRoom(String roomNumber, ArrayList<HospitalEquipment> equipments) {
		this.roomNumber = roomNumber;
		this.equipments = new ArrayList<HospitalEquipment>();
		
		for(HospitalEquipment equipment : equipments) {
			this.equipments.add(equipment);
		}
	}
	
	//public instance methods
	public String getRoomNumber() {
		return this.roomNumber;
	}
	public ArrayList<HospitalEquipment> getEquipments(){
		return equipments;
	}
	public void addEquipment(HospitalEquipment equipment) {
		this.equipments.add(equipment);
	}
	
    @Override
    public abstract String toString();
}
