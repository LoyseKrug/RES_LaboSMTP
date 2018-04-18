package main;

import pranks.PranksMaker;
import smtp.Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Main class, starts the PranksMaker
 */
public class Main {

    public static void main(String ... args) throws IOException {

        Properties properties = new Properties();
        InputStream is = null;
        is = new FileInputStream("./src/main/resources/prank.properties");
        properties.load(is);

        //once the properties are load in the file, we can access them through their name
        int numberOfPranks = Integer.parseInt(properties.getProperty("numberOfGroups"));
        int groupSize = Integer.parseInt(properties.getProperty("groupSize"));


        Client smtpClient = new Client();

        new PranksMaker(smtpClient, numberOfPranks, groupSize);
    }

}
