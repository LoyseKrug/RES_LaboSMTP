package util;

import java.io.*;
import java.util.LinkedList;

/**
 * Utilitary class used to read lines of a file, ignoring the comments inside (the comments start with a #)
 */
public class CommentedFileReader {

    BufferedReader br = null;
    String line;

    /**
     * Constructor of the CommentedFileReader
     * @param path
     * @throws FileNotFoundException
     */
    public CommentedFileReader(String path) throws FileNotFoundException {
        br =  new BufferedReader(new FileReader(path));
    }

    /**
     *
     * @return the next line without comments
     * @throws IOException
     */
    public String getNextLine() throws IOException {
        String line =  br.readLine();
        if(line != null && line.startsWith("#")){
            return getNextLine();
        }
        else {
            return line;
        }
    }
}

