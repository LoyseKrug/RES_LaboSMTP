package main;

import pranks.PranksMaker;
import smtp.Client;

import java.io.IOException;

/**
 * Main class, starts the PranksMaker
 */
public class Main {

    public static void main(String ... args) throws IOException {
        Client smtpClient = new Client();

        PranksMaker pm = new PranksMaker(smtpClient, 2, 3);
    }

}
