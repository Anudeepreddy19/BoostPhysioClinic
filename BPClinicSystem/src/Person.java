// Making an abstract class called Person
public abstract class Person {
    // Keeping some protected fields for id, name, address, and phone
    protected String id;
    protected String name;
    protected String address;
    protected String phone;

    // Setting up a constructor for initializing id, name, address, and phone
    public Person(String id, String name, String address, String phone) {
        this.id = id; // Assigning id
        this.name = name; // Assigning name
        this.address = address; // Assigning address
        this.phone = phone; // Assigning phone
    }

    // Adding getters for grabbing id, name, address, and phone
    public String getId() { return id; } // Getting id
    public String getName() { return name; } // Getting name
    public String getAddress() { return address; } // Getting address
    public String getPhone() { return phone; } // Getting phone

    // Overriding toString for returning a nice string representation
    @Override
    public String toString() {
        return name + " (ID: " + id + ")"; // Returning name and id in a readable format
    }
}
