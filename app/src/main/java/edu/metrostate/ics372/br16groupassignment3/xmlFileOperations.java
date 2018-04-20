package edu.metrostate.ics372.br16groupassignment3;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * A simple class for processing a reading Xml File
 * @author rordyniec
 *
 */
public class xmlFileOperations implements IReadingFileOperation {

    ArrayList<Reading> readings = null;
    /**
     * Reads and parses an Xml file of patient readings
     * @param path the path to the file to load
     * @return ArrayList of Readings
     * @throws IOException for file read errors
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<Reading> getFile(String path) throws IOException {
        DocumentBuilder b = null;
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            b = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            File file = new File(path);
            doc = b.parse(file);
            NodeList clinicList = doc.getElementsByTagName("Clinic");
            NodeList readingList = doc.getElementsByTagName("Reading");
            readings = processReadingList(clinicList, readingList);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return readings;
    }


    /**
     * Processes the clinic and readings nodes
     * @param clinic NodeList of clinics, should always be one
     * @param readings NodeList of Readings
     * @return ArrayList of Readings
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<Reading> processReadingList(NodeList clinic, NodeList readings) {
        int count = readings.getLength();
        int clinicId = Integer.parseUnsignedInt(clinic.item(0).getAttributes().getNamedItem("id").getNodeValue());
        String clinicName = clinic.item(0).getTextContent();
        ArrayList<Reading> r = new ArrayList<Reading>();
        for(int i = 0; i<count; i++) {
            r.add(buildReadingFromXml(clinicId, clinicName, readings.item(i)));
        }
        return r;
    }

    /**
     * Creates a Reading object from the input Xml node and clinic information
     * @param clinicId the ID for a clinic
     * @param clinicName the Name of the clinic
     * @param item the reading information
     * @return Reading object with data as found in the Xml node
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private Reading buildReadingFromXml(int clinicId, String clinicName, Node item) {
        String patientId = "";
        String readingId = "";
        String readingType = "";
        String readingUnit = "";
        String readingValue = "";

        //The file doesn't have a date, create one
        LocalDateTime dt =LocalDateTime.now();
        Long readingDate = dt.toEpochSecond(ZoneOffset.ofHours(0))*1000;

        //Process the Node
        if(item != null && item.hasChildNodes()) {
            //Get the Reading type
            if(item.getAttributes().getNamedItem("type") != null) {
                readingType = item.getAttributes().getNamedItem("type").getNodeValue();

            }
            //Get the reading ID
            if(item.getAttributes().getNamedItem("id") != null) {
                readingId = item.getAttributes().getNamedItem("id").getNodeValue();
            }

            if(item.getChildNodes().getLength()>0 ) {
                int count = item.getChildNodes().getLength();
                for(int i = 0; i < count; i++) {
                    Node element = item.getChildNodes().item(i);
                    switch (element.getNodeName()) {
                        //Get the reading value and unit value
                        case "Value":
                            if(element.getAttributes().getLength()>0) {
                                if(element.getAttributes().getNamedItem("unit") != null) {
                                    readingUnit = element.getAttributes().getNamedItem("unit").getNodeValue();
                                }
                            }
                            readingValue = element.getTextContent();
                            break;
                        //get the patient id
                        case "Patient":
                            patientId = element.getTextContent();
                            break;
                    }
                }
            }
        }
        Reading r = new Reading(patientId, readingType, readingId, readingValue, readingDate, readingUnit, clinicId, clinicName);
        return r;

    }

    /**
     * This method is not implemented
     */
    @Override
    public void writeFile(String path, String content) throws IOException {
    }

}
