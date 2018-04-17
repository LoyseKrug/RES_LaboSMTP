package main;

import pranks.PranksMaker;
import smtp.Client;

import java.io.IOException;
import java.util.Properties;

/**
 * Main class, starts the PranksMaker
 */
public class Main {

    public static void main(String ... args) throws IOException {

        Properties properties = new Properties();


        Client smtpClient = new Client();
        int numberOfPranks = Integer.parseInt(args[1]);
        int groupSize = Integer.parseInt(args[2]);

        new PranksMaker(smtpClient, numberOfPranks, groupSize);
    }

}
