package Assignment;

import java.util.*;

class Patient {
    private String id;
    private String name;
    private String contactNumber;
    private String address;

    public Patient(String id, String name, String contactNumber, String address) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("Patient ID: %s, Name: %s, Contact Number: %s, Address: %s", id, name, contactNumber, address);
    }
}

class Doctor {
    private String id;
    private String name;
    private String specialization;

    public Doctor(String id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return String.format("Doctor ID: %s, Name: %s, Specialization: %s", id, name, specialization);
    }
}

class Appointment {
    private String patientId;
    private String doctorId;
    private String date;
    private String time;

    public Appointment(String patientId, String doctorId, String date, String time) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("Appointment - Patient ID: %s, Doctor ID: %s, Date: %s, Time: %s", patientId, doctorId, date, time);
    }
}

class Hospital {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private Queue<Appointment> appointmentsQueue;
    private Stack<Appointment> appointmentsStack;
    private Appointment[] appointmentsArray;
    private int arrayIndex;

    public Hospital(int arraySize) {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointmentsQueue = new LinkedList<>();
        appointmentsStack = new Stack<>();
        appointmentsArray = new Appointment[arraySize];
        arrayIndex = 0;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(String id) {
        patients.removeIf(patient -> patient.getId().equals(id));
    }

    public Patient searchPatient(String id) {
        return patients.stream().filter(patient -> patient.getId().equals(id)).findFirst().orElse(null);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void removeDoctor(String id) {
        doctors.removeIf(doctor -> doctor.getId().equals(id));
    }

    public Doctor searchDoctor(String id) {
        return doctors.stream().filter(doctor -> doctor.getId().equals(id)).findFirst().orElse(null);
    }

    public void scheduleAppointment(Appointment appointment) {
        appointmentsQueue.add(appointment);
        appointmentsStack.push(appointment);
        if (arrayIndex < appointmentsArray.length) {
            appointmentsArray[arrayIndex++] = appointment;
        }
    }

    public Appointment getNextAppointmentQueue() {
        return appointmentsQueue.poll();
    }

    public Appointment getNextAppointmentStack() {
        return appointmentsStack.pop();
    }

    public Appointment getNextAppointmentArray() {
        if (arrayIndex > 0) {
            return appointmentsArray[--arrayIndex];
        }
        return null;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public Queue<Appointment> getAppointmentsQueue() {
        return appointmentsQueue;
    }

    public Stack<Appointment> getAppointmentsStack() {
        return appointmentsStack;
    }

    public Appointment[] getAppointmentsArray() {
        return appointmentsArray;
    }
}

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Hospital hospital = new Hospital(10);

        // Adding patients
        hospital.addPatient(new Patient("P001", "John Doe", "1234567890", "123 Main St"));
        hospital.addPatient(new Patient("P002", "Jane Smith", "0987654321", "456 Elm St"));

        // Adding doctors
        hospital.addDoctor(new Doctor("D001", "Dr. Adams", "Cardiology"));
        hospital.addDoctor(new Doctor("D002", "Dr. Baker", "Neurology"));

        // Scheduling appointments
        hospital.scheduleAppointment(new Appointment("P001", "D001", "2024-07-11", "09:00"));
        hospital.scheduleAppointment(new Appointment("P002", "D002", "2024-07-12", "10:00"));

        // Using the system
        System.out.println("Hospital Management System:");

        // Display all patients
        System.out.println("\nAll Patients:");
        hospital.getPatients().forEach(System.out::println);

        // Display all doctors
        System.out.println("\nAll Doctors:");
        hospital.getDoctors().forEach(System.out::println);

        // Schedule a new appointment
        System.out.println("\nScheduling a new appointment:");
        Appointment newAppointment = new Appointment("P001", "D002", "2024-08-15", "14:00");
        hospital.scheduleAppointment(newAppointment);
        System.out.println("Scheduled: " + newAppointment);

        // Display next appointment from the queue
        System.out.println("\nNext Appointment from Queue:");
        Appointment nextAppointmentQueue = hospital.getNextAppointmentQueue();
        System.out.println(nextAppointmentQueue);

        // Display next appointment from the stack
        System.out.println("\nNext Appointment from Stack:");
        Appointment nextAppointmentStack = hospital.getNextAppointmentStack();
        System.out.println(nextAppointmentStack);

        // Attempt to get the next appointment from the array
        System.out.println("\nNext Appointment from Array:");
        Appointment nextAppointmentArray = hospital.getNextAppointmentArray();
        if (nextAppointmentArray != null) {
            System.out.println(nextAppointmentArray);
        } else {
            System.out.println("No more appointments in the array.");
        }
    }
}
