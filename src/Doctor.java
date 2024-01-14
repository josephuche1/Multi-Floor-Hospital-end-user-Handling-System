
public class Doctor extends HospitalStaff{
    public Doctor(String id, String name) {
    	super(id, name, "Doctor");
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + this.getId()+ ", Name: "+this.getName() + "Position: " + this.getPosition();
	}
}
