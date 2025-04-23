// Defining the Treatment class
public class Treatment {
    // Declaring a private field for storing the treatment name
    private String name;
    // Declaring a private field for storing the expertise area
    private String expertiseArea;

    // Creating a constructor for initializing name and expertiseArea
    public Treatment(String name, String expertiseArea) {
        // Assigning the name parameter to the name field
        this.name = name;
        // Assigning the expertiseArea parameter to the expertiseArea field
        this.expertiseArea = expertiseArea;
    }

    // Adding a getter method for fetching the treatment name
    public String getName() { return name; }
    // Adding a getter method for fetching the expertise area
    public String getExpertiseArea() { return expertiseArea; }

    // Overriding the toString method for returning a string representation
    @Override
    public String toString() {
        // Returning the name and expertise area in a formatted string
        return name + " [" + expertiseArea + "]";
    }
}
