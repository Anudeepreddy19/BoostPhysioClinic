import java.time.LocalDateTime; // importing LocalDateTime for handling date and time
import java.time.format.DateTimeFormatter; // importing DateTimeFormatter for formatting date and time
import java.util.*; // importing utility classes like Scanner, List, etc.

public class Main {
        private static final Scanner scanner = new Scanner(System.in); // setting up scanner for user input
        private static final ClinicSystem clinic = new ClinicSystem(); // creating the clinic system instance
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // setting up date & time format

        public static void main(String[] args) {
                seedSampleData(); // adding some sample data for testing

                boolean exit = false; // initializing exit flag
                while (!exit) { // looping until user chooses to exit
                        System.out.println("\n======== BOOST PHYSIO CLINIC SYSTEM ========"); // showing menu header
                        System.out.println("1. Add a patient"); // showing option to add a patient
                        System.out.println("2. Add a physiotherapist"); // showing option to add a physiotherapist
                        System.out.println("3. View all patients"); // showing option to view all patients
                        System.out.println("4. View all physiotherapists"); // showing option to view all physiotherapists
                        System.out.println("5. Book appointment by expertise"); // showing option to book appointment by expertise
                        System.out.println("6. Book appointment by physiotherapist name"); // showing option to book appointment by name
                        System.out.println("7. Cancel appointment"); // showing option to cancel an appointment
                        System.out.println("8. Mark appointment as attended"); // showing option to mark appointment as attended
                        System.out.println("9. Generate report"); // showing option to generate a report
                        System.out.println("0. Exit"); // showing option to exit
                        System.out.print("Enter your choice: "); // prompting user for input

                        switch (scanner.nextLine()) { // handling user input for menu options
                                case "1" -> addPatient(); // calling method to add a new patient
                                case "2" -> addPhysiotherapist(); // calling method to add a new physiotherapist
                                case "3" -> viewPatients(); // calling method to view all patients
                                case "4" -> viewPhysiotherapists(); // calling method to view all physiotherapists
                                case "5" -> bookByExpertise(); // calling method to book appointment by expertise
                                case "6" -> bookByName(); // calling method to book appointment by physiotherapist name
                                case "7" -> cancelAppointment(); // calling method to cancel an appointment
                                case "8" -> attendAppointment(); // calling method to mark an appointment as attended
                                case "9" -> clinic.printReport(); // calling method to generate a report
                                case "0" -> exit = true; // setting exit flag to true to exit the loop
                                default -> System.out.println("Invalid choice. Try again."); // handling invalid input
                        }
                }
        }

        private static void addPatient() {
                // collecting patient details from user
                System.out.print("Enter ID: "); // asking for patient ID
                String id = scanner.nextLine(); // reading patient ID
                System.out.print("Enter name: "); // asking for patient name
                String name = scanner.nextLine(); // reading patient name
                System.out.print("Enter address: "); // asking for patient address
                String address = scanner.nextLine(); // reading patient address
                System.out.print("Enter phone: "); // asking for patient phone
                String phone = scanner.nextLine(); // reading patient phone
                clinic.addPatient(new Patient(id, name, address, phone)); // adding patient to the clinic system
                System.out.println("Patient added."); // confirming patient addition
        }

        private static void addPhysiotherapist() {
                // collecting physiotherapist details from user
                System.out.print("Enter ID: "); // asking for physiotherapist ID
                String id = scanner.nextLine(); // reading physiotherapist ID
                System.out.print("Enter name: "); // asking for physiotherapist name
                String name = scanner.nextLine(); // reading physiotherapist name
                System.out.print("Enter address: "); // asking for physiotherapist address
                String address = scanner.nextLine(); // reading physiotherapist address
                System.out.print("Enter phone: "); // asking for physiotherapist phone
                String phone = scanner.nextLine(); // reading physiotherapist phone
                System.out.print("Enter expertise areas (comma-separated): "); // asking for expertise areas
                List<String> expertise = Arrays.asList(scanner.nextLine().split(",")); // splitting expertise areas into a list
                Physiotherapist physio = new Physiotherapist(id, name, address, phone, expertise); // creating physiotherapist object

                // adding treatments for the physiotherapist
                System.out.print("How many treatments to add? "); // asking how many treatments to add
                int count = Integer.parseInt(scanner.nextLine()); // reading the number of treatments
                for (int i = 0; i < count; i++) { // looping through the number of treatments
                        System.out.print("Treatment name: "); // asking for treatment name
                        String treatmentName = scanner.nextLine(); // reading treatment name
                        System.out.print("Expertise area: "); // asking for expertise area
                        String area = scanner.nextLine(); // reading expertise area
                        physio.addTreatment(new Treatment(treatmentName, area)); // adding treatment to physiotherapist
                }

                clinic.addPhysiotherapist(physio); // adding physiotherapist to the clinic system
                System.out.println("Physiotherapist added."); // confirming physiotherapist addition
        }

        private static void viewPatients() {
                // displaying all patients
                System.out.println("All Patients:"); // showing header for patients
                clinic.getPatients().forEach(System.out::println); // printing all patients
        }

        private static void viewPhysiotherapists() {
                // displaying all physiotherapists
                System.out.println("All Physiotherapists:"); // showing header for physiotherapists
                clinic.getPhysiotherapists().forEach(System.out::println); // printing all physiotherapists
        }

        private static void bookByExpertise() {
                // booking an appointment based on expertise
                System.out.print("Enter patient ID: "); // asking for patient ID
                String pid = scanner.nextLine(); // reading patient ID
                Patient p = clinic.findPatientById(pid); // finding patient by ID
                if (p == null) { // checking if patient is not found
                        System.out.println("Patient not found."); // showing error message
                        return; // exiting the method
                }

                System.out.print("Enter area of expertise: "); // asking for expertise area
                String area = scanner.nextLine(); // reading expertise area
                List<Physiotherapist> physios = clinic.findPhysiosByExpertise(area); // finding physiotherapists by expertise

                if (physios.isEmpty()) { // checking if no physiotherapists are found
                        System.out.println("No physiotherapists found for that expertise."); // showing error message
                        return; // exiting the method
                }

                // displaying available physiotherapists
                System.out.println("Available physiotherapists:"); // showing header for physiotherapists
                for (int i = 0; i < physios.size(); i++) { // looping through physiotherapists
                        System.out.println((i + 1) + ". " + physios.get(i).getName()); // printing physiotherapist name
                }

                System.out.print("Choose one: "); // asking user to choose a physiotherapist
                int choice = Integer.parseInt(scanner.nextLine()) - 1; // reading user choice
                Physiotherapist chosen = physios.get(choice); // getting chosen physiotherapist

                // displaying available treatments
                System.out.println("Available treatments:"); // showing header for treatments
                chosen.getTreatments().stream() // getting treatments of chosen physiotherapist
                                .filter(t -> t.getExpertiseArea().equalsIgnoreCase(area)) // filtering treatments by expertise area
                                .forEach(System.out::println); // printing treatments

                System.out.print("Enter treatment name: "); // asking for treatment name
                String treatmentName = scanner.nextLine(); // reading treatment name
                Treatment treatment = chosen.getTreatments().stream() // getting treatments of chosen physiotherapist
                                .filter(t -> t.getName().equalsIgnoreCase(treatmentName)) // filtering treatments by name
                                .findFirst() // getting the first matching treatment
                                .orElse(null); // returning null if no match is found

                System.out.print("Enter date & time (yyyy-MM-dd HH:mm): "); // asking for date and time
                LocalDateTime time = LocalDateTime.parse(scanner.nextLine(), formatter); // parsing date and time

                clinic.bookAppointment(new Appointment(p, chosen, treatment, time)); // booking the appointment
                System.out.println("Appointment booked!"); // confirming appointment booking
        }

        private static void bookByName() {
                // booking an appointment based on physiotherapist name
                System.out.print("Enter patient ID: "); // asking for patient ID
                String pid = scanner.nextLine(); // reading patient ID
                Patient p = clinic.findPatientById(pid); // finding patient by ID
                if (p == null) { // checking if patient is not found
                        System.out.println("Patient not found."); // showing error message
                        return; // exiting the method
                }

                System.out.print("Enter physiotherapist name: "); // asking for physiotherapist name
                String name = scanner.nextLine(); // reading physiotherapist name
                Physiotherapist physio = clinic.getPhysiotherapists().stream() // getting all physiotherapists
                                .filter(pt -> pt.getName().equalsIgnoreCase(name)) // filtering physiotherapists by name
                                .findFirst() // getting the first matching physiotherapist
                                .orElse(null); // returning null if no match is found

                if (physio == null) { // checking if physiotherapist is not found
                        System.out.println("Physiotherapist not found."); // showing error message
                        return; // exiting the method
                }

                // displaying treatments offered by the physiotherapist
                System.out.println("Treatments:"); // showing header for treatments
                physio.getTreatments().forEach(System.out::println); // printing treatments

                System.out.print("Enter treatment name: "); // asking for treatment name
                String tname = scanner.nextLine(); // reading treatment name
                Treatment treatment = physio.getTreatments().stream() // getting treatments of physiotherapist
                                .filter(t -> t.getName().equalsIgnoreCase(tname)) // filtering treatments by name
                                .findFirst() // getting the first matching treatment
                                .orElse(null); // returning null if no match is found

                System.out.print("Enter date & time (yyyy-MM-dd HH:mm): "); // asking for date and time
                LocalDateTime time = LocalDateTime.parse(scanner.nextLine(), formatter); // parsing date and time

                clinic.bookAppointment(new Appointment(p, physio, treatment, time)); // booking the appointment
                System.out.println("Appointment booked!"); // confirming appointment booking
        }

        private static void cancelAppointment() {
                // canceling an appointment
                System.out.print("Enter patient ID: "); // asking for patient ID
                String pid = scanner.nextLine(); // reading patient ID
                List<Appointment> appts = clinic.getAppointments().stream() // getting all appointments
                                .filter(a -> a.getPatient().getId().equals(pid) && a.getStatus() == Appointment.Status.BOOKED) // filtering booked appointments for the patient
                                .toList(); // converting to list

                if (appts.isEmpty()) { // checking if no booked appointments are found
                        System.out.println("No booked appointments found."); // showing error message
                        return; // exiting the method
                }

                // displaying booked appointments
                for (int i = 0; i < appts.size(); i++) { // looping through booked appointments
                        System.out.println((i + 1) + ". " + appts.get(i)); // printing appointment details
                }

                System.out.print("Choose appointment to cancel: "); // asking user to choose an appointment to cancel
                int index = Integer.parseInt(scanner.nextLine()) - 1; // reading user choice
                appts.get(index).cancel(); // canceling the selected appointment
                System.out.println("Appointment cancelled."); // confirming appointment cancellation
        }

        private static void attendAppointment() {
                // marking an appointment as attended
                System.out.print("Enter patient ID: "); // asking for patient ID
                String pid = scanner.nextLine(); // reading patient ID
                List<Appointment> appts = clinic.getAppointments().stream() // getting all appointments
                                .filter(a -> a.getPatient().getId().equals(pid) && a.getStatus() == Appointment.Status.BOOKED) // filtering booked appointments for the patient
                                .toList(); // converting to list

                if (appts.isEmpty()) { // checking if no appointments are available to mark attended
                        System.out.println("No appointments available to mark attended."); // showing error message
                        return; // exiting the method
                }

                // displaying booked appointments
                for (int i = 0; i < appts.size(); i++) { // looping through booked appointments
                        System.out.println((i + 1) + ". " + appts.get(i)); // printing appointment details
                }

                System.out.print("Choose appointment to mark attended: "); // asking user to choose an appointment to mark attended
                int index = Integer.parseInt(scanner.nextLine()) - 1; // reading user choice
                appts.get(index).attend(); // marking the selected appointment as attended
                System.out.println("Appointment marked as attended."); // confirming appointment attendance
        }

        private static void seedSampleData() {
                // adding some sample patients and physiotherapists for testing
                clinic.addPatient(new Patient("P001", "Alice", "123 Road", "123456")); // adding sample patient Alice
                clinic.addPatient(new Patient("P002", "Bob", "456 Street", "789123")); // adding sample patient Bob

                Physiotherapist pt1 = new Physiotherapist("T001", "Dr. Smith", "10 Ave", "111222", List.of("Massage")); // creating sample physiotherapist Dr. Smith
                pt1.addTreatment(new Treatment("Neck Massage", "Massage")); // adding treatment Neck Massage
                pt1.addTreatment(new Treatment("Back Massage", "Massage")); // adding treatment Back Massage

                Physiotherapist pt2 = new Physiotherapist("T002", "Dr. Jane", "55 Road", "999888", List.of("Rehabilitation")); // creating sample physiotherapist Dr. Jane
                pt2.addTreatment(new Treatment("Pool Rehab", "Rehabilitation")); // adding treatment Pool Rehab

                clinic.addPhysiotherapist(pt1); // adding Dr. Smith to the clinic system
                clinic.addPhysiotherapist(pt2); // adding Dr. Jane to the clinic system
        }
}
