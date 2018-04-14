package edu.metrostate.ics372.br16groupassignment3;

import java.util.HashMap;

/**
 * This class encapsulates a reading
 * @author rordyniec
 *
 */
public class Reading {
    private String patientId;
    private String type;
    private String id;
    private Object value;
    private Long date;
    private Integer clinicId;
    private String clinicName;
    private String unit;

    private String[] keys = {"patient_id", "reading_type", "reading_id", "reading_value", "reading_date", "clinic_id", "clinic_name", "reading_unit"};
    /**
     * Constructor
     * @param reading Hashmap of reading data
     */
    public Reading(HashMap<String, Object> reading) throws Exception {
        for(String key: keys) {
            if(!reading.containsKey(key))
                throw new Exception("Invalid input data");
        }
        this.patientId = (String) reading.get("patient_id");
        this.type      = (String) reading.get("reading_type");
        this.id        = (String) reading.get("reading_id");
        this.value     = reading.get("reading_value");
        this.date      = ((Double)reading.get("reading_date")).longValue();
        this.unit = (String) reading.get("reading_unit");
        this.clinicId = ((Double)reading.get("clinic_id")).intValue();
        this.clinicName = (String) reading.get("clinic_name");
        System.out.println(this.toString());
    }

    /**
     * Constructor allows individual values to be set without a hashmap
     * @param patientId
     * @param type
     * @param id
     * @param value
     * @param date
     */
    public Reading(String patientId, String type, String id, Object value, Long date, String unit) {
        this.patientId = patientId;
        this.type = type;
        this.id = id;
        this.value = value;
        this.date = date;
        this.unit = unit;
    }

    /**
     * Constructor allows individual values to be set without a hashmap
     * @param patientId
     * @param type
     * @param id
     * @param value
     * @param date
     * @param unit
     * @param clinicId
     * @param clinicName
     */
    public Reading(String patientId, String type, String id, Object value, Long date, String unit, int clinicId, String clinicName) {
        this.patientId = patientId;
        this.type = type;
        this.id = id;
        this.value = value;
        this.date = date;
        this.unit = unit;
        this.clinicId = clinicId;
        this.clinicName = clinicName;
    }

    public Reading() {}

    public String getPatientId() {
        return patientId;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    public Long getDate() {
        return date;
    }


    /**
     * formats the object output for json
     */
    @Override
    public String toString() {
        String returnValue = "\t\t\t\"patient_id\" : \""   + patientId  + "\",\n" +
                "\t\t\t\"reading_type\" : \"" + type       + "\",\n" +
                "\t\t\t\"reading_id\" : \""   + id         + "\",\n" +
                "\t\t\t\"reading_value\" : "  + (value.getClass().getName()=="java.lang.String"?"\"" + value + "\"":value) + ",\n"  +
                "\t\t\t\"reading_date\" : "   + date       + ",\n" +
                "\t\t\t\"reading_unit\" : \""   + unit       + "\",\n" +
                "\t\t\t\"clinic_id\" : "   + clinicId       + ",\n" +
                "\t\t\t\"clinic_name\" : \""   + clinicName       + "\"\n";

        //System.out.println(returnValue);
        return returnValue;
    }

    /**
     * @return the clinicID
     */
    public Integer getClinicID() {
        return clinicId;
    }

    /**
     * @param clinicID the clinicID to set
     */
    public void setClinicID(Integer clinicID) {
        this.clinicId = clinicID;
    }

    /**
     * @return the clinicName
     */
    public String getClinicName() {
        return clinicName;
    }

    /**
     * @param clinicName the clinicName to set
     */
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    /**
     * @return the readingunit
     */
    public String getReadingunit() {
        return unit;
    }

    /**
     * @param readingunit the readingunit to set
     */
    public void setReadingunit(String readingunit) {
        this.unit = readingunit;
    }
}
