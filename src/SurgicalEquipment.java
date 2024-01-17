
public class SurgicalEquipment extends HospitalEquipment{
	private String equipmentType;
	
	public SurgicalEquipment(String id, String name, double price) {
		super(id, name, price);
		this.equipmentType = "Surgical";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.equipmentType + ", Name: "+this.getName()+", Location: "+this.getPrice();
	}

	@Override
	public String getEquipmentType() {
		// TODO Auto-generated method stub
		return this.equipmentType;
	}
	
	
}
