import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Hospital implements Serializable{
  private String name;
  private String password;
  private String filename;
  private HospitalPharmacy pharmacy;
  private HospitalFinancial_Accounts finances;
  private ArrayList<Patient> patients;
  private ArrayList<HospitalStaff> staff;
  private ArrayList<HospitalRoom> rooms;
  private ArrayList<HospitalEquipment> equipments;
  private ArrayList<HospitalFloor> floors;
  private int floorCount;
  private int staffCount;
  private int patientsCount;
  private int roomCount;
  private int equipmentCount;
  
  
  //Constructors
  public Hospital() {
	  this.name = "";
	  this.password = "";
	  this.pharmacy = new HospitalPharmacy();
	  this.finances = new HospitalFinancial_Accounts();
	  this.patients = new ArrayList<Patient>();
	  this.staff = new ArrayList<HospitalStaff>();
	  this.rooms = new ArrayList<HospitalRoom>();
	  this.equipments = new ArrayList<HospitalEquipment>();
	  this.floors = new ArrayList<HospitalFloor>();
	  this.floorCount = 0; 
	  this.staffCount = 0;
	  this.patientsCount = 0;
	  this.roomCount = 0; 
	  this.equipmentCount = 0;
	  this.saveDetails();
  }
  
  public Hospital(String name, String password) {
	  this.name = name;
	  this.password = password;
	  this.pharmacy = new HospitalPharmacy();
	  this.finances = new HospitalFinancial_Accounts();
	  this.patients = new ArrayList<Patient>();
	  this.staff = new ArrayList<HospitalStaff>();
	  this.rooms = new ArrayList<HospitalRoom>();
	  this.equipments = new ArrayList<HospitalEquipment>();
	  this.floors = new ArrayList<HospitalFloor>();
	  this.floorCount = 0; 
	  this.staffCount = 0;
	  this.patientsCount = 0;
	  this.roomCount = 0; 
	  this.equipmentCount = 0;
	  saveDetails();
  }
  
  public Hospital(String name, String password, ArrayList<Patient> patients, ArrayList<HospitalStaff> staff, ArrayList<HospitalRoom> rooms) {
	  this.name = name;
	  this.password = password;
	  this.pharmacy = new HospitalPharmacy();
	  this.finances = new HospitalFinancial_Accounts();
	  this.patients = new ArrayList<Patient>();
	  this.staff = new ArrayList<HospitalStaff>();
	  this.rooms = new ArrayList<HospitalRoom>();
	  this.equipments = new ArrayList<HospitalEquipment>();
	  this.floors = new ArrayList<HospitalFloor>();
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
	  this.floorCount = 0; 
	  this.staffCount = staff.size();
	  this.patientsCount = patients.size();
	  this.roomCount = rooms.size(); 
	  this.equipmentCount = 0;
	  this.saveDetails();
  }
  
  // public instance methods
  public String getName() {
	  return this.name;
  }
  
  public void addEquipment(HospitalEquipment equipment) {
	  this.equipments.add(equipment);
	  this.equipmentCount++;
  }
  
   public void addPatient(Patient patient) {
	  this.patients.add(patient);
	  this.patientsCount++;
  }
  
  public void addStaff(HospitalStaff staff) {
	  this.staff.add(staff);
	  this.staffCount++;
  }
  
  public void addFloor(HospitalFloor floor) {
	  this.floors.add(floor);
	  this.floorCount++;
  }
  
  public String addRoom(HospitalRoom room, String floorNumber) {
	  for(HospitalRoom roomI : this.rooms) {
		  if(roomI.getRoomNumber().equals(room.getRoomNumber())) {
			  return "Room Already Exists";
		  }
	  }
	  for(HospitalFloor floor: this.floors) {
		  if(floor.getFloorNumber().equals(floorNumber)) {
			  floor.addRoom(room);
			  this.addRoom(room);
			  this.roomCount++;
			  return "Success";
		  }
	  }
      return "Floor does not exist";
  }
  
  public void addMedicine(Medicine medicine) {
	  this.pharmacy.addMedicine(medicine);
  }
  
  public void addRoom(HospitalRoom room) {
	  this.rooms.add(room);
  }
  
  public String getPassword() {
	  return this.password;
  }
  
  public void setName(String name) {
	  this.name = name;
  }
  
  public void setPassword(String password) {
	  this.password = password;
  }
  
  public void addTransaction(Transaction transaction) {
	  this.finances.addTransaction(transaction);
  }
  
  public HospitalRoom assignPatient(Patient patient) {
	  for(HospitalRoom room : this.rooms) {
		  if(room.getAvailability() && room.getRoomType().equals("General Ward")) {
			  room.addPatient(patient);
			  return room;
		  }
	  }
	  return null;
  }
  
  public ArrayList<Patient> getPatients(){
	  return this.patients;
  }
  
  public ArrayList<HospitalRoom> getRooms(){
	  return this.rooms;
  }
  
  public ArrayList<HospitalFloor> getFloors(){
	  return this.floors;
  }
  
  public ArrayList<HospitalStaff> getStaff(){
	  return this.staff;
  }
  
  public ArrayList<HospitalEquipment> getEquipment(){
	  return this.equipments;
  }
  public ArrayList<Transaction> getTransactions(){
	  return this.finances.getTransactions();
  }
  
  public ArrayList<Medicine> getMedicines(){
	  return this.pharmacy.getMedicines();
  }
  
  
  public void saveDetails() {
	  try {
		  FileOutputStream fileOut = new FileOutputStream("Hospital Management System.bak");
		  ObjectOutputStream out = new ObjectOutputStream(fileOut);
		  out.writeObject(this);
		  out.close();
		  fileOut.close();
	  } catch(IOException e) {
		  e.printStackTrace();
	  }
  }
  
  public void loadDetails(Hospital hospital) {
	  try {
		FileInputStream fileIn = new FileInputStream("Hospital Management System.bak");
		ObjectInputStream in  = new ObjectInputStream(fileIn);
		hospital = (Hospital) in.readObject();
		fileIn.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("User class not found");
		e.printStackTrace();
	}
  }
  
  public void saveHospital() {
	  try {
		  FileOutputStream fileOut = new FileOutputStream(filename);
		  ObjectOutputStream out = new ObjectOutputStream(fileOut);
		  out.writeObject(this);
		  out.close();
		  fileOut.close();
	  } catch(IOException e) {
		  e.printStackTrace();
	  }
  }
  
  public void loadHospital(Hospital hospital) {
	  try {
		FileInputStream fileIn = new FileInputStream(this.filename);
		ObjectInputStream in  = new ObjectInputStream(fileIn);
		hospital = (Hospital) in.readObject();
		fileIn.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("User class not found");
		e.printStackTrace();
	}
  }
  
  private void saveFilename() {
	  try {
		PrintWriter writer = new PrintWriter("filename.txt", "UTF-8");
		writer.println(filename);
		writer.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
  
  private void loadFilename() {
	  try {
		BufferedReader reader = new BufferedReader(new FileReader("filename.txt"));
		this.filename = reader.readLine();
		reader.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  @Override
  public String toString() {
	  return "Name: " + this.name + ". \n Staff Population: " + this.staff.size()+". \n Number of rooms: " + this.rooms.size()+ "\n Number of patients: "+ this.patients.size();
  }
  
  public int getMedicineCount() {
	  return this.pharmacy.getCount();
  }
  
  public int getTransactionCount() {
	  return this.finances.getTransactionCount();
  }
  
  public int getfloorCount() {
	  return this.floorCount;
  }
  public int getRoomCount() {
	  return this.roomCount;
  }
  public int getEquipmentCount() {
	  return this.equipmentCount;
  }
  public int getPatientCount() {
	  return this.patientsCount;
  }
  public int getStaffCount() {
	  return this.staffCount;
  }
}
