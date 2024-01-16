import java.io.Serializable;

public class Medicine implements Serializable{
    private String id;
    private String name;
    private String description;
    private double price;
    
    //constructor
    public Medicine(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    // public instance methods
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public double getPrice() {
        return this.price;
    }
    
    @Override
    public String toString() {
        return "Medicine ID: " + this.id + ", Name: " + this.name + ", Description: " + this.description + ", Price: " + this.price;
    }
}