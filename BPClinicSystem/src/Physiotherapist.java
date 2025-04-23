import java.util.*; // Importing the utility package for using List and ArrayList

public class Physiotherapist { // Defining the Physiotherapist class
    private String id; // Declaring a private variable for storing the ID
    private String name; // Declaring a private variable for storing the name
    private String address; // Declaring a private variable for storing the address
    private String phone; // Declaring a private variable for storing the phone number
    private List<String> expertiseAreas; // Declaring a private list for storing expertise areas
    private List<Treatment> treatments; // Declaring a private list for storing treatments

    public Physiotherapist(String id, String name, String address, String phone, List<String> expertiseAreas) {
        // Creating a constructor for initializing the Physiotherapist object
        this.id = id; // Assigning the ID to the instance variable
        this.name = name; // Assigning the name to the instance variable
        this.address = address; // Assigning the address to the instance variable
        this.phone = phone; // Assigning the phone number to the instance variable
        this.expertiseAreas = expertiseAreas; // Assigning the expertise areas to the instance variable
        this.treatments = new ArrayList<>(); // Initializing the treatments list as an empty ArrayList
    }

    public void addTreatment(Treatment t) { // Defining a method for adding a treatment
        treatments.add(t); // Adding the treatment to the treatments list
    }

    public List<Treatment> getTreatments() { // Defining a method for getting the treatments list
        return treatments; // Returning the treatments list
    }

    public String getName() { // Defining a method for getting the name
        return name; // Returning the name
    }

    public String getId() { // Defining a method for getting the ID
        return id; // Returning the ID
    }

    public List<String> getExpertiseAreas() { // Defining a method for getting the expertise areas
        return expertiseAreas; // Returning the expertise areas list
    }

    public String getAddress() { // Defining a method for getting the address
        return address; // Returning the address
    }

    public String getPhone() { // Defining a method for getting the phone number
        return phone; // Returning the phone number
    }

    @Override
    public String toString() { // Overriding the toString method for custom string representation
        return name + " (" + id + ")"; // Returning the name and ID in a formatted string
    }
}
