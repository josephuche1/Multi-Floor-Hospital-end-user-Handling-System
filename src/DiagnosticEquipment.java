
public class DiagnosticEquipment extends HospitalEquipment{
	private String equipmentType;
	
	public DiagnosticEquipment(String id, String name, double price) {
		super(id, name, price);
		this.equipmentType = "Diagnostic";
	}
	
	@Override
	public  String getEquipmentType() {
		return this.equipmentType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.equipmentType + ", Name: "+this.getName()+", Price: "+this.getPrice();
	}
	
	
}
