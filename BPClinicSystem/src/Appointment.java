// Importing the LocalDateTime class for handling date and time
import java.time.LocalDateTime;

// Defining the Appointment class
public class Appointment {
    // Defining an enum for appointment statuses
    public enum Status { BOOKED, CANCELLED, ATTENDED, MISSED }

    // Declaring a Patient object to store patient details
    private Patient patient;
    // Declaring a Physiotherapist object to store physiotherapist details
    private Physiotherapist physiotherapist;
    // Declaring a Treatment object to store treatment details
    private Treatment treatment;
    // Declaring a LocalDateTime object to store appointment time
    private LocalDateTime time;
    // Declaring a Status variable to store the current status of the appointment
    private Status status;

    // Creating a constructor to initialize the appointment details
    public Appointment(Patient p, Physiotherapist pt, Treatment t, LocalDateTime time) {
        // Assigning the patient to the appointment
        this.patient = p;
        // Assigning the physiotherapist to the appointment
        this.physiotherapist = pt;
        // Assigning the treatment to the appointment
        this.treatment = t;
        // Setting the appointment time
        this.time = time;
        // Setting the initial status to BOOKED
        this.status = Status.BOOKED;
    }

    // Marking the appointment as attended
    public void attend() { status = Status.ATTENDED; }
    // Marking the appointment as cancelled
    public void cancel() { status = Status.CANCELLED; }
    // Marking the appointment as missed
    public void miss() { status = Status.MISSED; }

    // Getting the current status of the appointment
    public Status getStatus() { return status; }
    // Setting a new status for the appointment
    public void setStatus(Status status) { this.status = status; }
    // Updating the appointment time
    public void setTime(LocalDateTime time) { this.time = time; }
    // Retrieving the appointment time
    public LocalDateTime getTime() { return time; }
    // Retrieving the treatment details
    public Treatment getTreatment() { return treatment; }
    // Retrieving the physiotherapist details
    public Physiotherapist getPhysiotherapist() { return physiotherapist; }
    // Retrieving the patient details
    public Patient getPatient() { return patient; }

    // Overriding the toString method to return a string representation of the appointment
    @Override
    public String toString() {
        // Returning a formatted string with time, treatment, patient, and status
        return time + " | " + treatment + " | " + patient + " | " + status;
    }
}
