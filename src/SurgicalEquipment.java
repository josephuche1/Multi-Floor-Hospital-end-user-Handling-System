
public class SurgicalEquipment extends HospitalEquipment{
	private String equipmentType;
	
	public SurgicalEquipment(String id, String name, String location) {
		super(id, name, location);
		this.equipmentType = "Surgical Equipment";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.equipmentType + ", Name: "+this.getName()+", Location: "+this.getLocation();
	}

	@Override
	public String getEquipmentType() {
		// TODO Auto-generated method stub
		return this.equipmentType;
	}
	
	
}
