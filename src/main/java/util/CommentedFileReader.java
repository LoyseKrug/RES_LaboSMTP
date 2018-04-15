package util;

import java.io.*;
import java.util.LinkedList;


public class CommentedFileReader {

    //we create a file from its path
    BufferedReader br = null;
    String line;
    public CommentedFileReader(String path) throws FileNotFoundException {
        br =  new BufferedReader(new FileReader(path));
    }

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

