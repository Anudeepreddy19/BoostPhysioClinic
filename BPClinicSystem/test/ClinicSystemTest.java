// Import necessary classes
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ClinicSystemTest {
    private ClinicSystem clinic; // setting up the clinic system
    private Patient patient; // holding patient info
    private Physiotherapist physio; // holding physiotherapist info
    private Treatment treatment; // holding treatment info
    private Appointment appointment; // holding appointment info

    @BeforeEach
    public void setup() {
        clinic = new ClinicSystem(); // initializing the clinic system

        // Adding multiple patients
        patient = new Patient("P001", "Alice", "123 Road", "1234567890");
        clinic.addPatient(patient);
        patient = new Patient("P002", "Mark", "22 Elm Street", "111222333");
        clinic.addPatient(patient);
        patient = new Patient("P003", "Sarah", "55 Oak Road", "444555666");
        clinic.addPatient(patient);
        patient = new Patient("P004", "John", "78 Pine Avenue", "777888999");
        clinic.addPatient(patient);

        // Adding multiple physiotherapists with expertise
        physio = new Physiotherapist("T001", "Dr. Bob", "456 Street", "0987654321",
                Arrays.asList("Massage", "Rehab"));
        treatment = new Treatment("Back Massage", "Massage");
        physio.addTreatment(treatment);
        clinic.addPhysiotherapist(physio);

        Physiotherapist physio2 = new Physiotherapist("T002", "Dr. Emily", "789 Avenue", "1122334455",
                Arrays.asList("Acupuncture", "Rehab"));
        Treatment treatment2 = new Treatment("Acupuncture Therapy", "Acupuncture");
        physio2.addTreatment(treatment2);
        clinic.addPhysiotherapist(physio2);

        // Booking multiple appointments
        appointment = new Appointment(patient, physio, treatment, LocalDateTime.of(2025, 4, 20, 10, 0));
        clinic.bookAppointment(appointment);

        Appointment appointment2 = new Appointment(patient, physio2, treatment2, LocalDateTime.of(2025, 4, 21, 11, 0));
        clinic.bookAppointment(appointment2);
    }

    @Test
    public void testAddPatient() {
        // checking if patient ID length is correct
        assertEquals(4, clinic.getAppointments().get(0).getPatient().getId().length());
    }

    @Test
    public void testBookAppointment() {
        // verifying appointment booking
        List<Appointment> all = clinic.getAppointments();
        assertEquals(2, all.size()); // making sure there are two appointments
        assertEquals(Appointment.Status.BOOKED, all.get(0).getStatus()); // checking status is BOOKED
    }

    @Test
    public void testAttendAppointment() {
        // marking appointment as attended and checking status
        appointment.attend();
        assertEquals(Appointment.Status.ATTENDED, appointment.getStatus());
    }

    @Test
    public void testCancelAppointment() {
        // canceling appointment and verifying status
        appointment.cancel();
        assertEquals(Appointment.Status.CANCELLED, appointment.getStatus());
    }

    @Test
    public void testSearchByExpertise() {
        // searching physiotherapists by expertise and checking results
        List<Physiotherapist> results = clinic.findPhysiosByExpertise("Massage");
        assertFalse(results.isEmpty()); // making sure results are not empty
        assertEquals("Dr. Bob", results.get(0).getName()); // confirming the correct physiotherapist is found

        results = clinic.findPhysiosByExpertise("Acupuncture");
        assertFalse(results.isEmpty());
        assertEquals("Dr. Emily", results.get(0).getName());
    }

    @Test
    public void testSearchPatientById() {
        // searching for a patient by ID
        Patient foundPatient = clinic.findPatientById("P003");
        assertNotNull(foundPatient);
        assertEquals("Sarah", foundPatient.getName());
    }

    @Test
    public void testSearchPhysioById() {
        // searching for a physiotherapist by ID
        Physiotherapist foundPhysio = clinic.findPhysioById("T002");
        assertNotNull(foundPhysio, "No physiotherapist found with the given ID");
        assertEquals("Dr. Emily", foundPhysio.getName());
    }

    @Test
    public void testListAllAppointments() {
        // listing all appointments
        List<Appointment> appointments = clinic.getAppointments();
        assertEquals(2, appointments.size());
    }

    @Test
    public void testOverlappingAppointments() {
        // testing overlapping appointments
        Appointment overlappingAppointment = new Appointment(patient, physio, treatment, LocalDateTime.of(2025, 4, 20, 10, 0));
        assertThrows(IllegalArgumentException.class, () -> clinic.bookAppointment(overlappingAppointment));
    }
}
