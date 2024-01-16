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
  
  public Hospital(String name, String password) {
	  this.name = name;
	  this.password = password;
	  this.patients = new ArrayList<Patient>();
	  this.staff = new ArrayList<HospitalStaff>();
	  this.rooms = new ArrayList<HospitalRoom>();
	  this.saveDetails();
  }
  
  public Hospital(String name, String password, ArrayList<Patient> patients, ArrayList<HospitalStaff> staff, ArrayList<HospitalRoom> rooms) {
	  this.name = name;
	  this.password = password;
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
  
  public String getPassword() {
	  return this.password;
  }
  
  public void setName(String name) {
	  this.name = name;
  }
  
  public void setPassword(String password) {
	  this.password = password;
  }
  
  private void saveDetails() {
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
}
