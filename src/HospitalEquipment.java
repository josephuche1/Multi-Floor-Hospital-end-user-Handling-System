import java.io.Serializable;

/**
 * HospitalEquipment is an abstract class that represents the general characteristics of equipment within a hospital.
 * It serves as a base class for different types of hospital equipment, encapsulating common properties such as ID, name, and price.
 * This class is designed to be extended by specific equipment types, each providing their own implementation of the abstract methods.
 * It implements Serializable to allow instances of its subclasses to be serialized for persistence or network transmission.
 * 
 * @author Uche Joseph
 * @version 1.0
 */
public abstract class HospitalEquipment implements Serializable{
	private String id;
	private String name;
	private double price;
	
	/**
	 * Default constructor for HospitalEquipment class.
	 * Initializes a new instance of HospitalEquipment with default values for id, name, and price.
	 */
	public HospitalEquipment() {
		this.id = "";
		this.name="";
		this.price = 0.0;
	}
	
	/**
	 * Constructs a HospitalEquipment instance with the specified ID and name, and a default price of 0.0.
	 *
	 * @param id   The unique identifier for the equipment.
	 * @param name The name of the equipment.
	 */
	public HospitalEquipment(String id, String name) {
		this.id = id;
		this.name = name;
		this.price = 0.0;
	}
	
	/**
	 * Constructs a HospitalEquipment instance with the specified ID, name, and price.
	 *
	 * @param id    The unique identifier for the equipment.
	 * @param name  The name of the equipment.
	 * @param price The price of the equipment.
	 */
	public HospitalEquipment(String id, String name, double price) {
		this.id = id;
		this.name =name;
		this.price = price;
	}
	
	/**
	 * Retrieves the unique identifier of the equipment.
	 *
	 * @return The ID of the equipment as a string.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Retrieves the name of the equipment.
	 *
	 * @return The name of the equipment as a string.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Retrieves the price of the equipment.
	 *
	 * @return The price of the equipment as a double.
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Abstract method that must be implemented by subclasses to return the specific type of the equipment.
	 *
	 * @return A string representing the type of the equipment.
	 */
	public abstract String getEquipmentType();
	
	/**
	 * Abstract method that must be implemented by subclasses to provide a string representation of the equipment.
	 * This typically includes details such as ID, name, and price.
	 *
	 * @return A string describing the equipment.
	 */
	@Override
	public abstract String toString();
}
