import java.util.ArrayList;

public class HospitalPharmacy {
	private ArrayList<Medicine> medicines;
	private static  int medicineCount = 0;
	
	//constructors
	public HospitalPharmacy() {
		this.medicines = new ArrayList<Medicine>();
		this.medicineCount = this.medicines.size();
	}
	public HospitalPharmacy(ArrayList<Medicine> medicines) {
		this.medicines = new ArrayList<Medicine>();
		for(Medicine medicine : medicines) {
			this.medicines.add(medicine);
		}
		this.medicineCount = this.medicines.size();
	}
	
	public HospitalPharmacy(ArrayList<Medicine> medicines, boolean addNew) {
		this.medicines = new ArrayList<Medicine>();
		for(Medicine medicine : medicines) {
			this.medicines.add(medicine);
		}
		this.medicineCount = this.medicines.size();
	}
	
	// public instance methods
	public ArrayList<Medicine> getMedicines(){
		return this.medicines;
	}
	
	public void addMedicine(Medicine medicine) {
		this.medicines.add(medicine);
		this.medicineCount = this.medicines.size();
	}
	
	public void removeMedicine(Medicine medicine) {
		this.medicines.remove(medicine);
		this.medicineCount = this.medicines.size();
	}
	
	public Medicine findMedicine(String name) {
		for(Medicine medicine : this.medicines) {
			if(medicine.getName() == name) {
				return medicine;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Pharmacy: \n Number of medicine: "+this.medicineCount;
	}
}
