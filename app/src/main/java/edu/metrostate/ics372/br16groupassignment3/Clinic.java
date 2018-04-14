package edu.metrostate.ics372.br16groupassignment3;

/**
 * Object to hold clinic data
 * @author rordyniec
 *
 */
public class Clinic {
    private int clinicId;
    private String clinicName;

    public Clinic (int clinicId, String clinicName) {
        this.setClinicId(clinicId);
        this.setClinicName(clinicName);
    }

    /**
     * Getter
     * @return the clinicId
     */
    public int getClinicId() {
        return clinicId;
    }

    /**
     * Setter
     * @param clinicId the clinicId to set
     */
    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    /**
     * Getter
     * @return the clinicName
     */
    public String getClinicName() {
        return clinicName;
    }

    /**
     * Setter
     * @param clinicName the clinicName to set
     */
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

}
