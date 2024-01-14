
public abstract class HospitalEquipment {
	private String id;
	private String name;
	private String location;
	
	//constructors
	public HospitalEquipment() {
		this.id = "";
		this.name="";
		this.location="";
	}
	public HospitalEquipment(String id, String name) {
		this.id = id;
		this.name = name;
		this.location  = "";
	}
	public HospitalEquipment(String id, String name, String location) {
		this.id = id;
		this.name =name;
		this.location  = location;
	}
	
	//public instance variables
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String Location) {
		this.location = location;
	}
	
	@Override
	public abstract String toString();
}
