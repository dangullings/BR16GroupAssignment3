package edu.metrostate.ics372.br16groupassignment3;

import java.util.ArrayList;

/**
 * An object to hold patient data
 * @author rordyniec
 *
 */
public class Patient {

    private String id;
    private ArrayList<Reading> readings;
    private boolean isPartOfTrial;

    /**
     * Constructor
     * @param id the patient ID
     */
    public Patient(String id) {
        this.id = id;
        readings = new ArrayList<Reading>();
        isPartOfTrial = true;
    }

    /**
     * Getter for ID
     * @return id value
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for ID
     * @param id the ID value
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Getter for Reading list
     * @return
     */
    public ArrayList<Reading> getReadings() {
        return readings;
    }

    /**
     * Add a reading to the Reading list if it doesn't already exist
     * @param reading the reading to add
     */
    public void addReading(Reading reading) {
        if (isPartOfTrial() && !readingExists(reading)) {
            readings.add(reading);
        }
    }

    /**
     * Check if a reading already exists in the list
     * @param reading the reading we are trying to find
     * @return found status
     */
    private boolean readingExists (Reading reading) {
        boolean found = false;
        for(Reading r: readings) {
            if(r.getId().equals(reading.getId())) {
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Determine if the patient is in the trial
     * @return trial participation status
     */
    public boolean isPartOfTrial() {
        return isPartOfTrial;
    }

    /**
     * Setter for isPartOfTrial
     * @param isPartOfTrial the value
     */
    public void setPartOfTrial(boolean isPartOfTrial) {
        this.isPartOfTrial = isPartOfTrial;
    }

    /**
     * return readings in JSON format
     */
    @Override
    public String toString() {
        String returnString = "";
        for (Reading reading : readings) {
            returnString += "{\n";
            returnString += reading.toString();
            returnString += "}";
        }
        return returnString;
    }
}
