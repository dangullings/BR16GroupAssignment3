package edu.metrostate.ics372.br16groupassignment3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class jsonFileOperations implements IReadingFileOperation {


    /**
     * Load the data from a json file
     */

    //this holds the data in an ArrayList of HashMaps
    private ArrayList<Reading> readings =  new ArrayList<Reading>();
    private boolean lastLoadAllValid;

    /**
     * Load the data from a json file
     * @param path the file to load
     * @return ArrayList of readings
     * @throws IOException for missing or invalid file
     */
    @Override
    public ArrayList<Reading> getFile(String path) throws IOException {
        FileReader fr = new FileReader(path);
        JsonElement data = new JsonParser().parse(fr);
        getReading(data);
        return readings;
    }

    /**
     * Write the content to the path provided
     * @param path the filename to write to
     * @param content the string to write to the file
     * @throws IOException for missing or invalid file
     */
    @Override
    public void writeFile(String path, String content) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            writer.write(content);
        }
        finally {
            if(writer != null)
                writer.close();
        }
    }


    /**
     * this reads the json file
     * @param data a Json element
     * @throws IOException for missing or invalid file
     */
    private void getReading(JsonElement data) throws IOException{
        //checks if it's an object with class values
        setLastLoadAllValid(true);
        if (data.isJsonObject()) {
            //System.out.println("Is an object");
            JsonObject jObj = data.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String,JsonElement>> entries = jObj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entries.iterator();

            //travels through the object
            while (iter.hasNext()) {
                java.util.Map.Entry<String,JsonElement> entry = iter.next();
                Gson gson = new Gson();
                if(entry.getValue().isJsonArray())
                {
                    for(JsonElement j: entry.getValue().getAsJsonArray()) {
                        HashMap<String, Object> hm = gson.fromJson(j, HashMap.class);
                        try {
                            Reading r = new Reading(hm);
                            readings.add(r);
                        }catch(Exception e) {
                            setLastLoadAllValid(false);
                        }
                    }
                }

            }
        }
    }

    /**
     * @return the lastLoadAllValid
     */
    public boolean isLastLoadAllValid() {
        return lastLoadAllValid;
    }

    /**
     * @param lastLoadAllValid the lastLoadAllValid to set
     */
    public void setLastLoadAllValid(boolean lastLoadAllValid) {
        this.lastLoadAllValid = lastLoadAllValid;
    }
}
