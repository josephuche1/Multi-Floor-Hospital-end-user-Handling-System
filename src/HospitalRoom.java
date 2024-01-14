import java.util.ArrayList;

public abstract class HospitalRoom {
	private String roomNumber;
	private ArrayList<HospitalEquipment> equipments;
	private ArrayList<Patient> patients;
	protected String roomType;
	
	// Constructors
	public  HospitalRoom() {
		this.roomNumber = "";
		this.equipments = new ArrayList<HospitalEquipment>();
		this.patients = new ArrayList<Patient>();
	}
	public HospitalRoom(String roomNumber) {
		this.roomNumber = roomNumber;
		this.equipments = new ArrayList<HospitalEquipment>();
		this.patients = new ArrayList<Patient>();
	}
	public HospitalRoom(String roomNumber, ArrayList<HospitalEquipment> equipments, ArrayList<Patient> patients) {
		this.roomNumber = roomNumber;
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
	public ArrayList<HospitalEquipment> getEquipments(){
		return equipments;
	}
	public void addEquipment(HospitalEquipment equipment) {
		this.equipments.add(equipment);
	}
	public void removeEquipment(HospitalEquipment equipment) {
		this.equipments.remove(equipment);
	}
	public String getRoomType() {
		return this.roomType;
	}
	public void addPatient(Patient patient) {
		this.patients.add(patient);
	}
	public void removePatient(Patient paient) {
		this.patients.remove(paient);
	}
	
    @Override
    public abstract String toString();
}
