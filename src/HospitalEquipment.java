import java.io.Serializable;

public abstract class HospitalEquipment implements Serializable{
	private String id;
	private String name;
	private double price;
	
	//constructors
	public HospitalEquipment() {
		this.id = "";
		this.name="";
		this.price = 0.0;
	}
	public HospitalEquipment(String id, String name) {
		this.id = id;
		this.name = name;
		this.price = 0.0;
	}
	public HospitalEquipment(String id, String name, double price) {
		this.id = id;
		this.name =name;
		this.price = price;
	}
	
	//public instance variables
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public double getPrice() {
		return this.price;
	}
	
	public abstract String getEquipmentType();
	
	@Override
	public abstract String toString();
}
