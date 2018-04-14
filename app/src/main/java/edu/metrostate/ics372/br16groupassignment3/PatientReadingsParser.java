package edu.metrostate.ics372.br16groupassignment3;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to read in a json file to a hashmap
 * @author rordyniec
 *
 */
public class PatientReadingsParser {

    private static boolean loadSuccessful = true;

    /**
     * Load a file and convert to an ArrayList of Reading objects
     * @param location The file to load
     * @throws IOException
     */
    public static ArrayList<Reading> loadFile(String location) throws IOException {
        String path = location;
        ArrayList<Reading> r = null;
        switch (path.toLowerCase().split("[.]")[1]){
            case "xml":
                xmlFileOperations x = new xmlFileOperations();
                r = x.getFile(path);
                setLoadSuccessful(true);
                break;
            case "json":
                jsonFileOperations j = new jsonFileOperations();
                r = j.getFile(path);
                setLoadSuccessful(j.isLastLoadAllValid());
        }
        return r;
    }

    /**
     * Indicates status of the last file load
     * @return the loadSuccessful value
     */
    public static boolean isLoadSuccessful() {
        return loadSuccessful;
    }

    /**
     * Setter
     * @param loadSuccessful the loadSuccessful to set
     */
    private static void setLoadSuccessful(boolean loadSuccessful) {
        PatientReadingsParser.loadSuccessful = loadSuccessful;
    }

}
