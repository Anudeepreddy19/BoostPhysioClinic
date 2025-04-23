import java.time.LocalDateTime; // importing LocalDateTime for handling date and time
import java.util.*; // importing all utility classes
import java.util.stream.Collectors; // importing Collectors for stream operations

public class ClinicSystem { // defining the main class for the clinic system
    private List<Patient> patients = new ArrayList<>(); // creating a list to store patients
    private List<Physiotherapist> physiotherapists = new ArrayList<>(); // creating a list to store physiotherapists
    private List<Appointment> appointments = new ArrayList<>(); // creating a list to store appointments

    public void addPatient(Patient patient) { // adding a new patient to the list
        patients.add(patient); // adding the patient to the patients list
    }

    public void removePatient(String id) { // removing a patient by their ID
        patients.removeIf(p -> p.getId().equals(id)); // filtering and removing the patient with the matching ID
    }

    public void addPhysiotherapist(Physiotherapist physio) { // adding a new physiotherapist
        physiotherapists.add(physio); // adding the physiotherapist to the list
    }

    public List<Patient> getPatients() { // getting the list of all patients
        return patients; // returning the patients list
    }

    public List<Physiotherapist> getPhysiotherapists() { // getting the list of all physiotherapists
        return physiotherapists; // returning the physiotherapists list
    }

    public List<Appointment> getAppointments() { // getting the list of all appointments
        return appointments; // returning the appointments list
    }

    public Patient findPatientById(String id) { // finding a patient by their ID
        return patients.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null); // searching and returning the patient or null if not found
    }

    public Physiotherapist findPhysioById(String id) { // finding a physiotherapist by their ID
        return physiotherapists.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null); // searching and returning the physiotherapist or null if not found
    }

    public List<Physiotherapist> findPhysiosByExpertise(String expertise) { // finding physiotherapists by expertise
        List<Physiotherapist> result = new ArrayList<>(); // creating a list to store matching physiotherapists
        for (Physiotherapist p : physiotherapists) { // looping through all physiotherapists
            if (p.getExpertiseAreas().contains(expertise)) { // checking if the expertise matches
                result.add(p); // adding the physiotherapist to the result list
            }
        }
        return result; // returning the list of matching physiotherapists
    }

    public void bookAppointment(Appointment appointment) { // booking a new appointment
        for (Appointment a : appointments) { // looping through existing appointments
            if (a.getPhysiotherapist().equals(appointment.getPhysiotherapist()) && // checking if the physiotherapist matches
                a.getTime().equals(appointment.getTime())) { // checking if the time slot matches
                throw new IllegalArgumentException("Time slot is already booked for this physiotherapist"); // throwing an error if the slot is taken
            }
        }
        appointments.add(appointment); // adding the new appointment to the list
    }

    public void printReport() { // printing a detailed report
        Map<Physiotherapist, List<Appointment>> reportMap = new HashMap<>(); // creating a map to group appointments by physiotherapist
        for (Appointment appt : appointments) { // looping through all appointments
            reportMap.computeIfAbsent(appt.getPhysiotherapist(), k -> new ArrayList<>()).add(appt); // grouping appointments by physiotherapist
        }

        for (Map.Entry<Physiotherapist, List<Appointment>> entry : reportMap.entrySet()) { // iterating through the map entries
            System.out.println("Physiotherapist: " + entry.getKey().getName()); // printing the physiotherapist's name
            for (Appointment a : entry.getValue()) { // looping through their appointments
                System.out.println("  Treatment: " + a.getTreatment().getName() + ", Patient: " + a.getPatient().getName() + ", Time: " + a.getTime() + ", Status: " + a.getStatus()); // printing appointment details
            }
        }

        System.out.println("\n--- Ranking by Attended Appointments ---"); // printing a ranking header
        reportMap.entrySet().stream() // streaming the map entries
                .sorted((e1, e2) -> Long.compare( // sorting by the count of attended appointments
                        e2.getValue().stream().filter(a -> a.getStatus() == Appointment.Status.ATTENDED).count(),
                        e1.getValue().stream().filter(a -> a.getStatus() == Appointment.Status.ATTENDED).count()
                ))
                .forEach(e -> System.out.println(e.getKey().getName())); // printing the sorted physiotherapists
    }

    public void updateMissedAppointments() { // updating missed appointments
        LocalDateTime now = LocalDateTime.now(); // getting the current time
        for (Appointment a : appointments) { // looping through all appointments
            if (a.getStatus() == Appointment.Status.BOOKED && a.getTime().isBefore(now)) { // checking if the appointment is booked and in the past
                a.cancel(); // canceling the appointment
                a.setStatus(Appointment.Status.MISSED); // marking it as missed
                System.out.println("Appointment missed: " + a.getPatient().getName() + " with " + a.getPhysiotherapist().getName() + " at " + a.getTime()); // printing a missed appointment message
            }
        }
    }
    
    public void generateReport() { // generating a summary report
        System.out.println("=== Clinic System Report ==="); // printing the report header
        System.out.println("Total Patients: " + patients.size()); // printing the total number of patients
        System.out.println("Total Physiotherapists: " + physiotherapists.size()); // printing the total number of physiotherapists
        System.out.println("Total Appointments: " + appointments.size()); // printing the total number of appointments

        long attendedCount = appointments.stream() // counting attended appointments
                .filter(a -> a.getStatus() == Appointment.Status.ATTENDED)
                .count();
        long missedCount = appointments.stream() // counting missed appointments
                .filter(a -> a.getStatus() == Appointment.Status.MISSED)
                .count();

        System.out.println("Appointments Attended: " + attendedCount); // printing the count of attended appointments
        System.out.println("Appointments Missed: " + missedCount); // printing the count of missed appointments

        System.out.println("\n--- Physiotherapist Performance ---"); // printing a performance header
        Map<Physiotherapist, Long> physioPerformance = new HashMap<>(); // creating a map to track performance
        for (Appointment appt : appointments) { // looping through all appointments
            if (appt.getStatus() == Appointment.Status.ATTENDED) { // checking if the appointment was attended
                physioPerformance.put(appt.getPhysiotherapist(),
                        physioPerformance.getOrDefault(appt.getPhysiotherapist(), 0L) + 1); // updating the count for the physiotherapist
            }
        }

        physioPerformance.entrySet().stream() // streaming the performance map entries
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // sorting by the count of attended appointments
                .forEach(entry -> System.out.println(entry.getKey().getName() + ": " + entry.getValue() + " attended appointments")); // printing the sorted performance

        System.out.println("\n--- Expertise Distribution ---"); // printing an expertise distribution header
        Map<String, Long> expertiseDistribution = physiotherapists.stream() // streaming the physiotherapists
                .flatMap(p -> p.getExpertiseAreas().stream()) // flattening their expertise areas
                .collect(Collectors.groupingBy(e -> e, Collectors.counting())); // grouping and counting by expertise

        expertiseDistribution.forEach((expertise, count) -> // iterating through the expertise distribution
                System.out.println(expertise + ": " + count + " physiotherapists")); // printing the expertise and count
    }
}
