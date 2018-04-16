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

        //if(args.length != 3){
        //    System.out.println("Wrong number of arguments");
        //return;
        //}
        //int numberOfPranks = Integer.parseInt(args[1]);
        //int groupSize = Integer.parseInt(args[2]);

        PranksMaker pm = new PranksMaker(smtpClient, 2, 3);
    }

}
