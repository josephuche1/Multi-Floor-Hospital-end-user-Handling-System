
public class Other extends HospitalStaff{
    public Other(String id, String name) {
    	super(id, name, "Other");
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + this.getId()+ ", Name: "+this.getName() + "Position: " + this.getPosition();
	}
}
