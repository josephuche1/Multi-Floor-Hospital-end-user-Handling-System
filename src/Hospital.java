import java.util.ArrayList;

public class Hospital {
  private String name;
  private ArrayList<Patient> patients;
  private ArrayList<HospitalStaff> staff;
  private ArrayList<HospitalRoom> rooms;
  
  //Constructors
  public Hospital() {
	  String name = "";
	  patients = new ArrayList<Patient>();
	  staff = new ArrayList<HospitalStaff>();
	  rooms = new ArrayList<HospitalRoom>();
	  
  }
  
  public Hospital(String name) {
	  this.name = name;
	  this.patients = new ArrayList<Patient>();
	  this.staff = new ArrayList<HospitalStaff>();
	  this.rooms = new ArrayList<HospitalRoom>();
  }
  
  public Hospital(String name, ArrayList<Patient> patients, ArrayList<HospitalStaff> staff, ArrayList<HospitalRoom> rooms) {
	  this.name = name;
	  this.patients = new ArrayList<Patient>();
	  this.staff = new ArrayList<HospitalStaff>();
	  this.rooms = new ArrayList<HospitalRoom>();
	  
	  int indexPatients = 0;
	  
	  for(Patient patient : patients) {
		  this.patients.add(patient);
	  }
	  
	  for(HospitalStaff staff1 : staff) {
		  this.staff.add(staff1);
	  }
	  
	  for(HospitalRoom room : rooms) {
		  this.rooms.add(room);
	  }
  }
  
  // public instance methods
  public String getName() {
	  return this.name;
  }
  
  public void addPatient(Patient patient) {
	  this.patients.add(patient);
  }
  
  public void addStaff(HospitalStaff staff) {
	  this.staff.add(staff);
  }
  
  public void addRoom(HospitalRoom room) {
	  this.rooms.add(room);
  }
  
  public int getNumberOfPatients() {
	  return this.patients.size();
  }
  
  public int getNumberOfRooms() {
	  return this.rooms.size();
  }
  public int getNumberOfStaffMembers() {
	  return this.staff.size();
  }
  
  @Override
  public String toString() {
	  return "Name: " + this.name + ". \n Staff Population: " + this.staff.size()+". \n Number of rooms: " + this.rooms.size()+ "\n Number of patients: "+ this.patients.size();
  }
}
