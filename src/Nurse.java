
public class Nurse extends HospitalStaff{
    public Nurse(String id, String name) {
    	super(id, name, "Nurse");
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + this.getId()+ ", Name: "+this.getName() + "Position: " + this.getPosition();
	}
}
