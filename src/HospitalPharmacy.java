import java.io.Serializable;
import java.util.ArrayList;

public class HospitalPharmacy implements Serializable{
	private ArrayList<Medicine> medicines;
	private int countMedicine;
	
	//constructors
	public HospitalPharmacy() {
		this.medicines = new ArrayList<Medicine>();
		countMedicine = 0;
	}
	public HospitalPharmacy(ArrayList<Medicine> medicines) {
		this.medicines = new ArrayList<Medicine>();
		for(Medicine medicine : medicines) {
			this.medicines.add(medicine);
		}
		countMedicine = medicines.size();
	}
	
	// public instance methods
	public ArrayList<Medicine> getMedicines(){
		return this.medicines;
	}
	
	public void addMedicine(Medicine medicine) {
		this.medicines.add(medicine);
		countMedicine++;
	}
	
	public void removeMedicine(Medicine medicine) {
		this.medicines.remove(medicine);
		countMedicine--;
	}
	
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
	
	@Override
	public String toString() {
		return "Pharmacy: \n Number of medicine: "+this.medicines.size();
	}
}
