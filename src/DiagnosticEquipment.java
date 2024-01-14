
public class DiagnosticEquipment extends HospitalEquipment{
	private String equipmentType;
	
	public DiagnosticEquipment(String id, String name, String location) {
		super(id, name, location);
		this.equipmentType = "Diagnostic Equipment";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.equipmentType + ", Name: "+this.getName()+", Location: "+this.getLocation();
	}
	
	
}
