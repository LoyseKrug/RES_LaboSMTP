package smtp;

import pranks.Email;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Logger;

import static smtp.SMTPProtocol.*;

/**
 * Class representing a Client that will be able to communicate with the SMTP server and send emails
 */
public class Client {

    Socket clientSocket = null;
    PrintWriter pw = null;
    BufferedReader br = null;
    String host;
    int port;

    /**
     * Creation of a client who connect to the mock SMTP server and beginning of the conversation with the servr
     * @throws IOException
     */
    public Client() throws IOException {
        //a varaible properties will contain the properties given in an external file
        Properties properties = new Properties();
        InputStream is = null;
        is = new FileInputStream("./src/main/resources/server.properties");
        properties.load(is);

        //once the properties are load in the file, we can access them through their name
        host = properties.getProperty("host");
        port = Integer.parseInt(properties.getProperty("port"));

        //A socket is created to be linked to the server
        this.clientSocket = new Socket(host, port);
        pw = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
        br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String line;
        System.out.println("Cli: waiting for server greeting message !");

        if((line = br.readLine()).startsWith("554 ")) {
            System.out.println("Srv: " + line.toUpperCase());
            pw.print(CMD_QUIT + "\r\n");
            pw.flush();
            System.out.println("Cli: " + CMD_QUIT);
            return;
        } else if(line.startsWith("220 ")){
            System.out.println("Srv: " + line.toUpperCase());
        } else {
            System.out.println("Srv: " + line.toUpperCase());

            System.out.println("Cli: Unrecognised command !");
            throw new IOException();
        }

        //If the server connect properly we can sent the EHLO command to start communication with the SMTP server
        String ehlo = CMD_CONNECT + " prank";
        pw.print(ehlo + "\r\n");
        pw.flush();
        System.out.println("Cli: " + ehlo);

        //We wait for the server answer
        System.out.println("Cli: waiting for server protocoles !");
        do {
            line = br.readLine();
            System.out.println("Srv: " + line.toUpperCase());
        } while (!line.startsWith("250 "));
        //The server is now ready to recept informations to send emails

    }

    /**
     * Close the connection with the SMTP server
     * @throws IOException
     */
    public void disconnect() throws IOException {
        pw.print(CMD_QUIT + "\r\n");
        pw.flush();
        System.out.println("Cli: QUIT");
        //The server will answer with BYE
        String line = br.readLine();
        System.out.println("Srv: " + line.toUpperCase());
        if(clientSocket.isConnected()){
            clientSocket.close();
        }
    }

    /**
     * Send an email given in parameters to the SMTP server
     * @param email
     * @throws IOException
     */
    public void sendEmail(Email email) throws IOException{
        String line = null;

        //we give the email address of the sender
        String mailFrom = CMD_FROM + email.getSender();
        pw.print(mailFrom + "\r\n");
        pw.flush();
        System.out.println("Cli:" + mailFrom);
        //Read the answer of the server
        do {
            line = br.readLine();
            System.out.println("Srv: " + line.toUpperCase());
        } while (!line.startsWith("250 "));

        for(int i = 0; i < email.getRecipients().size(); ++i){
        //We give the recipients'email addresses
            String mailTo = CMD_TO + email.getRecipients().get(i);
            pw.println(mailTo + "\r\n");
            pw.flush();
            System.out.println("Cli:" + mailTo);
            //read the answer of the server
            do {
                line = br.readLine();
                System.out.println("Srv: " + line.toUpperCase());
            } while (!line.startsWith("250 "));
        }

        pw.print(CMD_DATA + "\r\n");
        pw.flush();
        System.out.println("Cli:" + CMD_DATA);
        do {
            line = br.readLine();
            System.out.println("Srv: " + line.toUpperCase());
        } while (!line.startsWith(""));

        //The message is sent to the server
        pw.print(email.getMessage());
        pw.flush();
        System.out.println("Cli:" + email.getMessage());

        //The message is ended with a backslash, a  "." and a backslash
        pw.print(".\r\n");
        pw.flush();
        System.out.println(".");
        //Read the answer of the server
        line = br.readLine();
        System.out.println("Srv: " + line.toUpperCase());
    }




}
