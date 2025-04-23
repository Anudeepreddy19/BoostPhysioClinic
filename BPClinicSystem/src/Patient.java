// Saying this class is for representing a patient
public class Patient {
    // Storing the patient's ID
    private String id;
    // Storing the patient's name
    private String name;
    // Storing the patient's address
    private String address;
    // Storing the patient's phone number
    private String phone;

    // Creating a constructor for initializing patient details
    public Patient(String id, String name, String address, String phone) {
        // Assigning the ID to the patient
        this.id = id;
        // Assigning the name to the patient
        this.name = name;
        // Assigning the address to the patient
        this.address = address;
        // Assigning the phone number to the patient
        this.phone = phone;
    }

    // Getting the patient's ID
    public String getId() { 
        // Returning the ID
        return id; 
    }
    // Getting the patient's name
    public String getName() { 
        // Returning the name
        return name; 
    }
    // Getting the patient's address
    public String getAddress() { 
        // Returning the address
        return address; 
    }
    // Getting the patient's phone number
    public String getPhone() { 
        // Returning the phone number
        return phone; 
    }

    // Converting the patient object to a string for easy display
    @Override
    public String toString() {
        // Returning the patient's name and ID in a readable format
        return name + " (" + id + ")";
    }
}
