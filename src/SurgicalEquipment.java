
public class SurgicalEquipment extends HospitalEquipment{
	private String equipmentType;
	
	public SurgicalEquipment(String id, String name, String location, String type) {
		super(id, name, location);
		this.equipmentType = "Surgical Equipment";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.equipmentType + ", Name: "+this.getName()+", Location: "+this.getLocation();
	}
	
	
}
