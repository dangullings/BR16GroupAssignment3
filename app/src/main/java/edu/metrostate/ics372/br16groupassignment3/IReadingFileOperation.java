package edu.metrostate.ics372.br16groupassignment3;

import java.io.IOException;
import java.util.ArrayList;

/**
 * An interface for file processing operations
 * @author rordyniec
 *
 */
public interface IReadingFileOperation {
    public ArrayList<Reading> getFile(String path) throws IOException;
    public void writeFile(String path, String content) throws IOException;

}
