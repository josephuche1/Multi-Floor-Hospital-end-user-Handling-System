/**
 * Hospital is a core class representing the hospital entity within the Multi-floor Hospital end-user Handling System.
 * It encapsulates all the data and operations related to the hospital, such as managing floors, rooms, patients, staff,
 * and medical equipment. This class is responsible for maintaining the overall state of the hospital and providing methods
 * to access and modify its data.
 *
 * @author Uche Joseph
 * @version 1.0
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
  
  
  /**
   * Default constructor for the Hospital class.
   * Initializes a new Hospital instance with default values for name and password, and empty lists for patients, staff, rooms, equipment, and floors.
   * It also initializes the pharmacy and financial accounts, sets the counts for floors, staff, patients, rooms, and equipment to zero,
   * and invokes the saveDetails method to persist the initial state of the hospital.
   */
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
  
  /**
   * Constructs a Hospital instance with the specified name and password.
   * Initializes the pharmacy, financial accounts, and lists for patients, staff, rooms, equipment, and floors.
   * Sets the counts for floors, staff, patients, rooms, and equipment to zero and saves the hospital details.
   *
   * @param name     The name of the hospital.
   * @param password The password for securing the hospital's data.
   */
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
  
  /**
   * Overloaded constructor for the Hospital class that initializes a new Hospital instance with specific details.
   * Sets the hospital's name and password, and initializes the lists for patients, staff, and rooms with the provided collections.
   * Also initializes the pharmacy and financial accounts, and empty lists for equipment and floors.
   * The counts for staff, patients, and rooms are set based on the sizes of the provided collections, while floor and equipment counts are initialized to zero.
   * Finally, it invokes the saveDetails method to persist the state of the hospital.
   *
   * @param name     The name of the hospital.
   * @param password The password for hospital data security.
   * @param patients A list of patients to be added to the hospital.
   * @param staff    A list of staff members to be added to the hospital.
   * @param rooms    A list of rooms to be added to the hospital.
   */
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
  
  /**
   * Retrieves the name of the hospital.
   *
   * @return A string representing the name of the hospital.
   */
  public String getName() {
	  return this.name;
  }
  
  /**
   * Adds a new piece of equipment to the hospital's inventory.
   * Increments the equipment count upon successful addition.
   *
   * @param equipment The HospitalEquipment object to be added to the inventory.
   */
  public void addEquipment(HospitalEquipment equipment) {
	  this.equipments.add(equipment);
	  this.equipmentCount++;
  }
  
  /**
   * Adds a new patient to the hospital's patient list.
   * Increments the patient count to reflect the addition.
   *
   * @param patient The Patient object to be added to the hospital's list of patients.
   */
  public void addPatient(Patient patient) {
	  this.patients.add(patient);
	  this.patientsCount++;
  }
  
  /**
   * Adds a new staff member to the hospital's staff list.
   * Increments the staff count to keep track of the total number of staff members.
   *
   * @param staff The HospitalStaff object to be added to the hospital's staff list.
   */
  public void addStaff(HospitalStaff staff) {
	  this.staff.add(staff);
	  this.staffCount++;
  }
  
  /**
   * Adds a new floor to the hospital's list of floors.
   * Increments the floor count to update the total number of floors in the hospital.
   *
   * @param floor The HospitalFloor object to be added to the hospital's floor list.
   */
  public void addFloor(HospitalFloor floor) {
	  this.floors.add(floor);
	  this.floorCount++;
  }
  
  /**
   * Attempts to add a new room to a specified floor in the hospital.
   * Checks if the room already exists before adding it to the appropriate floor and increments the room count.
   * Returns a message indicating the success of the operation or the reason for failure.
   *
   * @param room        The HospitalRoom object to be added.
   * @param floorNumber The floor number where the room is to be added.
   * @return A string message indicating the result of the operation.
   */
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
  
  /**
   * Adds a new medicine to the hospital's pharmacy inventory.
   * The pharmacy is responsible for managing the addition and updating its records accordingly.
   *
   * @param medicine The Medicine object to be added to the pharmacy's inventory.
   */
  public void addMedicine(Medicine medicine) {
	  this.pharmacy.addMedicine(medicine);
  }
  
  /**
   * Adds a new room to the hospital's overall list of rooms without specifying a floor.
   * This method is typically used when the floor assignment is not necessary or has been handled elsewhere.
   *
   * @param room The HospitalRoom object to be added to the hospital's list of rooms.
   */
  public void addRoom(HospitalRoom room) {
	  this.rooms.add(room);
  }
  
  /**
   * Retrieves the password for the hospital's system.
   *
   * @return A string representing the hospital's password.
   */
  public String getPassword() {
	  return this.password;
  }
  
  /**
   * Sets the name of the hospital to the specified string.
   *
   * @param name The new name to be assigned to the hospital.
   */
  public void setName(String name) {
	  this.name = name;
  }
  
  /**
   * Updates the password for the hospital's system.
   *
   * @param password The new password to be set for the hospital.
   */
  public void setPassword(String password) {
	  this.password = password;
  }
  
  /**
   * Records a new financial transaction in the hospital's financial accounts.
   * The finances object is updated with the new transaction, reflecting changes in the hospital's financial status.
   *
   * @param transaction The Transaction object to be recorded in the hospital's financial ledger.
   */
  public void addTransaction(Transaction transaction) {
	  this.finances.addTransaction(transaction);
  }
  
  /**
   * Assigns a patient to an available room of type "General Ward".
   * It searches through the list of rooms and assigns the patient to the first available room that matches the criteria.
   * If no suitable room is available, it returns null.
   *
   * @param patient The Patient object to be assigned to a room.
   * @return The HospitalRoom to which the patient was assigned, or null if no suitable room was available.
   */
  public HospitalRoom assignPatient(Patient patient) {
	  for(HospitalRoom room : this.rooms) {
		  if(room.getAvailability() && room.getRoomType().equals("General Ward")) {
			  room.addPatient(patient);
			  return room;
		  }
	  }
	  return null;
  }
  
  /**
   * Retrieves the list of patients currently registered in the hospital.
   *
   * @return An ArrayList of Patient objects representing the hospital's patients.
   */
  public ArrayList<Patient> getPatients(){
	  return this.patients;
  }
  
  /**
   * Retrieves the list of rooms within the hospital.
   *
   * @return An ArrayList of HospitalRoom objects representing the hospital's rooms.
   */
  public ArrayList<HospitalRoom> getRooms(){
	  return this.rooms;
  }
  
  /**
   * Retrieves the list of floors in the hospital.
   *
   * @return An ArrayList of HospitalFloor objects representing the hospital's floors.
   */
  public ArrayList<HospitalFloor> getFloors(){
	  return this.floors;
  }
  
  /**
   * Retrieves the list of staff members working in the hospital.
   *
   * @return An ArrayList of HospitalStaff objects representing the hospital's staff.
   */
  public ArrayList<HospitalStaff> getStaff(){
	  return this.staff;
  }
  
  /**
   * Retrieves the list of equipment available in the hospital.
   *
   * @return An ArrayList of HospitalEquipment objects representing the hospital's equipment inventory.
   */
  public ArrayList<HospitalEquipment> getEquipments(){
	  return this.equipments;
  }
  
  /**
   * Retrieves the list of financial transactions recorded in the hospital's accounts.
   *
   * @return An ArrayList of Transaction objects representing the financial transactions of the hospital.
   */
  public ArrayList<Transaction> getTransactions(){
	  return this.finances.getTransactions();
  }
  
  /**
   * Retrieves the list of medicines available in the hospital's pharmacy.
   *
   * @return An ArrayList of Medicine objects representing the stock of medicines.
   */
  public ArrayList<Medicine> getMedicines(){
	  return this.pharmacy.getMedicines();
  }
  
  /**
   * Saves the current state of the hospital to a file.
   * Serializes the Hospital object and writes it to "Hospital Management System.bak".
   * If an IOException occurs during the save operation, the stack trace is printed.
   */
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
  
  /**
   * Provides a string representation of the hospital's basic statistics.
   * Includes the name of the hospital, staff population, number of rooms, and number of patients.
   *
   * @return A string summarizing the hospital's key information.
   */
  @Override
  public String toString() {
	  return "Name: " + this.name + ". \n Staff Population: " + this.staff.size()+". \n Number of rooms: " + this.rooms.size()+ "\n Number of patients: "+ this.patients.size();
  }
  
  /**
   * Retrieves the count of different medicines available in the hospital's pharmacy.
   *
   * @return The number of unique medicine types in the pharmacy's inventory.
   */
  public int getMedicineCount() {
	  return this.pharmacy.getCount();
  }
  
  /**
   * Retrieves the total number of financial transactions recorded in the hospital's financial accounts.
   *
   * @return The count of financial transactions.
   */
  public int getTransactionCount() {
	  return this.finances.getTransactionCount();
  }
  
  /**
   * Retrieves the current number of floors in the hospital.
   *
   * @return The total count of floors within the hospital.
   */
  public int getfloorCount() {
	  return this.floorCount;
  }
  
  /**
   * Retrieves the current number of rooms in the hospital.
   *
   * @return The total count of rooms within the hospital.
   */
  public int getRoomCount() {
	  return this.roomCount;
  }
  
  /**
   * Retrieves the current number of pieces of equipment in the hospital.
   *
   * @return The total count of equipment items within the hospital.
   */
  public int getEquipmentCount() {
	  return this.equipmentCount;
  }
  
  /**
   * Retrieves the current number of patients registered in the hospital.
   *
   * @return The total count of patients within the hospital.
   */
  public int getPatientCount() {
	  return this.patientsCount;
  }
  
  /**
   * Retrieves the current number of staff members employed in the hospital.
   *
   * @return The total count of staff within the hospital.
   */
  public int getStaffCount() {
	  return this.staffCount;
  }
}
