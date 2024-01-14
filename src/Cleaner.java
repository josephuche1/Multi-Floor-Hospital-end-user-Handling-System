
public class Cleaner extends HospitalStaff{
    public Cleaner(String id, String name) {
    	super(id, name, "Cleaner");
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + this.getId()+ ", Name: "+this.getName() + "Position: " + this.getPosition();
	}
}
