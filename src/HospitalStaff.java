
public abstract  class HospitalStaff {
  private String id;
  private String name;
  private String position;
  
  //constructors
  public HospitalStaff() {
	  this.id = "";
	  this.name = "";
	  this.position = "";
	  
  }
  public HospitalStaff(String id, String name) {
	  this.id = id;
	  this.name =name;
	  this.position = "";
  }
  public HospitalStaff(String id, String name, String position) {
	  this.id = id;
	  this.name =name;
	  this.position = position;	  
  }
  
  // public instance methods
  public String getId() {
	  return this.id;
  }
  public String getName() {
	  return this.name;
  }
  public String getPosition() {
	  return this.position;
  }
  
  @Override
  public String toString() {
	  return "ID: " + this.id + "\n Name: "+this.name+"\n Position: "+this.position;
  }
}
