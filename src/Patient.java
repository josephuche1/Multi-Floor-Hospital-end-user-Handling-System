import java.io.Serializable;

public class Patient implements Serializable{
  private String id;
  private String name;
  private String illnesses;
  private String roomNumber;
  
  //constructors
  public Patient() {
	this.id = "";
	this.name = "";
	this.illnesses = "";
  }
  public Patient(String id, String name) {
	  this.id = id;
	  this.name = name;
	  this.illnesses = "";
  }
  public Patient(String id, String name, String illness) {
	  this.id  = id;
	  this.name =name;
	  this.illnesses = "";
  }
  
  // Public instance methods
  public String getId() {
	  return this.id;
  }
  public String getName() {
	  return this.name;
  }
  public String getIllnesses() {
	  return this.illnesses;
  }
  public String getRoomNumber() {
	  return this.roomNumber;
  }
  public void setRoomNumber(String roomNumber) {
	  this.roomNumber = roomNumber; 
  }
  
  @Override
  public String toString() {
	  String str = "ID: " + this.id + ", Name: " + this.name + ", Illness(es): " +this.getIllnesses();

	  return str;
  }
  
}
