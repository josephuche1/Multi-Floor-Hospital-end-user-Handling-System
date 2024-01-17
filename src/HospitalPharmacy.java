import java.io.Serializable;
import java.util.ArrayList;

/**
 * HospitalPharmacy is a class that represents the pharmacy within a hospital.
 * It manages an inventory of medicines and tracks the count of different medicines available.
 * This class is responsible for adding, removing, and finding medicines in the pharmacy's inventory.
 * It implements Serializable to allow pharmacy data to be saved and restored as needed.
 * 
 * @author Uche Joseph
 * @version 1.0
 */
public class HospitalPharmacy implements Serializable{
	private ArrayList<Medicine> medicines;
	private int countMedicine;
	

    /**
     * Default constructor that initializes a new HospitalPharmacy with an empty list of medicines.
     */
	public HospitalPharmacy() {
		this.medicines = new ArrayList<Medicine>();
		countMedicine = 0;
	}
	
    /**
     * Constructor that initializes a new HospitalPharmacy with a provided list of medicines.
     *
     * @param medicines The initial list of medicines to be added to the pharmacy.
     */
	public HospitalPharmacy(ArrayList<Medicine> medicines) {
		this.medicines = new ArrayList<Medicine>();
		for(Medicine medicine : medicines) {
			this.medicines.add(medicine);
		}
		countMedicine = medicines.size();
	}
	
    /**
     * Retrieves the list of medicines in the pharmacy.
     *
     * @return An ArrayList of Medicine objects.
     */
	public ArrayList<Medicine> getMedicines(){
		return this.medicines;
	}
	
    /**
     * Adds a medicine to the pharmacy's inventory and increments the medicine count.
     *
     * @param medicine The Medicine object to be added.
     */
	public void addMedicine(Medicine medicine) {
		this.medicines.add(medicine);
		countMedicine++;
	}
	
    /**
     * Removes a medicine from the pharmacy's inventory and decrements the medicine count.
     *
     * @param medicine The Medicine object to be removed.
     */
	public void removeMedicine(Medicine medicine) {
		this.medicines.remove(medicine);
		countMedicine--;
	}
	
    /**
     * Finds a medicine by its name in the pharmacy's inventory.
     *
     * @param name The name of the medicine to find.
     * @return The Medicine object if found, or null otherwise.
     */
	public Medicine findMedicine(String name) {
		for(Medicine medicine : this.medicines) {
			if(medicine.getName() == name) {
				return medicine;
			}
		}
		return null;
	}
	public int getCount() {
		return this.countMedicine;
	}
	
	
    /**
     * Retrieves the count of medicines in the pharmacy.
     *
     * @return The count of medicines as an integer.
     */
	@Override
	public String toString() {
		return "Pharmacy: \n Number of medicine: "+this.medicines.size();
	}
}
