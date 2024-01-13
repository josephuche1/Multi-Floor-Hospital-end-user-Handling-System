import java.util.ArrayList;

public class Patient {
  private String id;
  private String name;
  private ArrayList<String> illnesses;
  
  //constructors
  public Patient() {
	this.id = "";
	this.name = "";
	this.illnesses = new ArrayList<String>();
  }
  public Patient(String id, String name) {
	  this.id = id;
	  this.name = name;
	  this.illnesses = new ArrayList<String>();
  }
  public Patient(String id, String name, String illness) {
	  this.id  = id;
	  this.name =name;
	  this.illnesses = new ArrayList<String>();
	  illnesses.add(illness);
  }
  
  // Public instance methods
  public String getId() {
	  return this.id;
  }
  public String getName() {
	  return this.name;
  }
  public ArrayList<String> getIllnesses() {
	  return this.illnesses;
  }
  public void addIllness(String illness) {
	  illnesses.add(illness);
  }
  
  @Override
  public String toString() {
	  String str = "ID: " + this.id + "\n Name: " + this.name + "Illness(es): \n";
	  String strIllnesses = "";
	  for (String illness : this.illnesses) {
		  strIllnesses += illness + "\n";
	  }
	  str += strIllnesses;
	  return str;
  }
  
}
