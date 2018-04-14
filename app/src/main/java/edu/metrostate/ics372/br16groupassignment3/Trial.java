package edu.metrostate.ics372.br16groupassignment3;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class encompasses the logic of the application
 * Aggregates patients and clinics; provides methods for manipulating the patient data
 * @author rordyniec
 *
 */
public class Trial {
    private ArrayList<Patient> patients =  null;
    private ArrayList<Clinic> clinics = null;

    /**
     * Constructor - builds the patient list and associated data
     * @param patientReadings The hashmap of patient reading data
     */
    public Trial(ArrayList<Reading> patientReadings) {
        createObjects();
        processReadingsList(patientReadings);
    }

    /**
     * Constructor - initializes the Patient and Clinic list objects
     */
    public Trial() {
        createObjects();
    }

    /**
     * Processes a readings list adding information as needed to the patient and clinic objects
     * @param patientReadings
     */
    public void processReadingsList(ArrayList<Reading> patientReadings) {
        //iterate through the readings building the patient list and adding readings
        for (Reading patientReading : patientReadings) {
            // Instantiate a Patient and add to patients list if the patient does not exist in the list
            String patientId = patientReading.getPatientId();
            if (!isExistingPatient(patientId)) {
                patients.add(new Patient(patientId));
            }

            //add to the clinics list
            if(!isExistingClinic(patientReading.getClinicID(), patientReading.getClinicName())) {
                clinics.add(new Clinic(patientReading.getClinicID(), patientReading.getClinicName()));
            }
            // Add new Reading to the corresponding Patient's readings list
            addReadingToPatient(patientReading);
        }
    }

    /**
     * Initializes the patient and clinic list objects
     */
    private void createObjects() {
        if(patients == null)
            patients = new ArrayList<Patient>();
        if(clinics == null)
            clinics = new ArrayList<Clinic>();
    }


    /**
     * Determine if a patient is already in our list
     * @param patientId the patient to find
     * @return boolean indicating the found status
     */
    private boolean isExistingPatient(String patientId) {
        boolean isExistingPatient = false;
        for (Patient patient : patients) {
            if (patient.getId() == patientId) {
                isExistingPatient = true;
                break;
            }
        }
        return isExistingPatient;
    }

    /**
     * Getter for the patients list
     * @return ArrayList of Patients
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    /**
     * Adds a reading to the patient in the Reading object, if it isn't found then the patient is added
     * @param reading the reading to add
     */
    public void addReadingToPatient(Reading reading) {
        Patient patient = findPatientById(reading.getPatientId());
        if(patient==null) {
            patients.add(new Patient(reading.getPatientId()));
            patient = findPatientById(reading.getPatientId());
        }
        if (patient != null && patient.isPartOfTrial()) {
            patient.addReading(reading);
        }
        this.addClinic(reading.getClinicID(), reading.getClinicName());
    }

    /**
     * Adds a reading to the target patient, all values are set by the caller
     * @param patientId the patient id
     * @param type the reading type
     * @param id the reading id
     * @param value the reading value
     * @param date the reading date
     * @param unit the reading unit
     * @param clinicId the clinic id
     * @param clinicName the clinic name
     */
    public void addReadingToPatient(String patientId, String type, String id, Object value, Long date, String unit, int clinicId, String clinicName) {
        addReadingToPatient(new Reading(patientId, type, id, value, date, unit, clinicId, clinicName));
    }

    /**
     * Adds a reading to the target patient, all values are set by the caller
     * @param patientId the patient id
     * @param type the reading type
     * @param id the reading id
     * @param value the reading value
     * @param date the reading date
     * @param unit the reading unit
     */
    public void addReadingToPatient(String patientId, String type, String id, Object value, Long date, String unit) {
        addReadingToPatient(new Reading(patientId, type, id, value, date, unit));
    }

    /**
     * Set the trial status of a patient
     * @param patientId the patient to update
     * @param status the new status
     */
    public void setPatientTrailStatus(String patientId, boolean status) {
        findPatientById(patientId).setPartOfTrial(status);
    }

    /**
     * Get the trial status of a patient
     * @param patientId
     * @return boolean indicating patient trial status
     */
    public boolean getPatientTrialStatus(String patientId) {
        return findPatientById(patientId).isPartOfTrial();
    }

    /**
     * format the object for json output
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n" + "\t\"patient_readings\":[\n");
        for (Patient patient : patients) {
            for (Reading reading : patient.getReadings()) {
                builder.append("\t\t{\n");
                builder.append(reading.toString());
                builder.append("\t\t},\n");
            }
        }
        String returnValue = builder.substring(0, builder.length()-2) + "\n\t]\n" + "}";
        return returnValue;
    }

    /**
     * Find a patient using the ID
     * @param patientId the patient to find
     * @return Patient found, null if not found
     */
    public Patient findPatientById(String patientId) {
        Patient matchingPatient = null;
        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                matchingPatient = patient;
                break;
            }
        }
        return matchingPatient;
    }


    /**
     * Saves the trial data as JSON
     * @param filePath the path to save as
     * @throws IOException thrown on file write error
     */
    public void saveTrialStatus(String filePath) throws IOException{
        jsonFileOperations fio = new jsonFileOperations();
        fio.writeFile(filePath, this.toString());
    }

    /**
     * Add a clinic to our list
     * @param id the clinic id
     * @param name the clinic name
     */
    public void addClinic(int id, String name) {
        if(!isExistingClinic(id, name)) {
            Clinic newClinic = new Clinic(id, name);
            clinics.add(newClinic);
        }
    }

    /**
     * Find a clinic
     * @param id the clinic id to find
     * @return Clinic found, null if not found
     */
    public Clinic findClinicById(int id) {
        Clinic matchingClinic = null;
        for (Clinic clinic : clinics) {
            if (clinic.getClinicId() == id) {
                matchingClinic = clinic;
                break;
            }
        }
        return matchingClinic;
    }

    /**
     * Find a clinic
     * @param name the name of the clinic
     * @return Clinic found, null if not found
     */
    public Clinic findClinicByName(String name) {
        Clinic matchingClinic = null;
        for (Clinic clinic : clinics) {
            if (clinic.getClinicName().equals(name)) {
                matchingClinic = clinic;
                break;
            }
        }
        return matchingClinic;
    }

    /**
     * See if a Clinic exists
     * @param id the id to search for
     * @param name the name to search
     * @return true if either name or id is matched
     */
    public boolean isExistingClinic(int id, String name) {
        boolean exists = false;
        for (Clinic clinic : clinics) {
            if (clinic.getClinicId() == id || clinic.getClinicName().equals(name)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Getter for the Clinic list
     * @return the clinics
     */
    public ArrayList<Clinic> getClinics() {
        return clinics;
    }

    /**
     * Add a patient to the trial
     * @param patient the patient to add
     */
    public void addPatient(Patient patient) {
        if(!isExistingPatient(patient.getId())) {
            patients.add(patient);
        }
    }
}
