import java.io.Serializable;
import java.util.ArrayList;

public abstract class HospitalRoom implements Serializable{
	private String roomNumber;
	private boolean isAvailable;
	private String floorNumber;
	private ArrayList<HospitalEquipment> equipments;
	private ArrayList<Patient> patients;
	protected String  roomType;
	
	// Constructors
	public  HospitalRoom() {
		this.roomNumber = "";
		this.floorNumber = "";
		this.isAvailable = true;
		this.equipments = new ArrayList<HospitalEquipment>();
		this.patients = new ArrayList<Patient>();
	}
	public HospitalRoom(String roomNumber, boolean isAvailable, String floorNumber) {
		this.roomNumber = roomNumber;
		this.isAvailable = isAvailable;
        this.floorNumber = floorNumber;
		this.equipments = new ArrayList<HospitalEquipment>();
		this.patients = new ArrayList<Patient>();
	}
	public HospitalRoom(String roomNumber, ArrayList<HospitalEquipment> equipments, ArrayList<Patient> patients, boolean isAvailable, String floorNumber) {
		this.roomNumber = roomNumber;
		this.isAvailable = isAvailable;
		this.floorNumber = floorNumber;
		this.equipments = new ArrayList<HospitalEquipment>();
		this.patients = new ArrayList<Patient>();
		
		for(HospitalEquipment equipment : equipments) {
			this.equipments.add(equipment);
		}
		
		for(Patient patient : patients) {
			this.patients.add(patient);
		}
	}
	
	//public instance methods
	public String getRoomNumber() {
		return this.roomNumber;
	}
	public boolean getAvailability() {
		return this.isAvailable;
	}
	public ArrayList<HospitalEquipment> getEquipments(){
		return equipments;
	}
	public void addEquipment(HospitalEquipment equipment) {
		this.equipments.add(equipment);
	}
	public void removeEquipment(HospitalEquipment equipment) {
		this.equipments.remove(equipment);
	}
	public void addPatient(Patient patient) {
		this.patients.add(patient);
	}
	public void removePatient(Patient paient) {
		this.patients.remove(paient);
	}
	public String getRoomType() {
		return this.roomType;
	}
	
	public String getFloorNumber() {
		return this.floorNumber;
	}
    @Override
    public abstract String toString();
}
