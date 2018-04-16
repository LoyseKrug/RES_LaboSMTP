package smtp;

import pranks.Email;

import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

public class Client {

    static final Logger LOG = Logger.getLogger(Client.class.getName());
    final static int BUFFER_SIZE = 1024;

    Socket clientSocket = null;
    PrintWriter pw = null;
    BufferedReader br = null;
    String host;
    int port;
    String email;

    public Client() throws IOException {
        //a varaible properties will contain the properties given in an external file
        Properties properties = new Properties();
        InputStream is = null;

        is = new FileInputStream("./src/main/resources/server.properties");
        /*
        System.out.println(new File(".").getAbsolutePath());
        //we extract the properties of the server from the server.properties file
        InputStream is = getClass().getClassLoader().getResourceAsStream("./src/main/resources/server.properties");
        if(is == null){
            System.out.println("The propreties file could not be loaded");
            throw new FileNotFoundException();
        }
        */
        properties.load(is);
        //once the properties are load in the file, we can access them through their name
        host = properties.getProperty("host");
        port = Integer.parseInt(properties.getProperty("port"));
        email = properties.getProperty("email");

        //A socket is created to be linked to the server
        this.clientSocket = new Socket(host, port);
        pw = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
        br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void disconnect(){
        //A IMPLEMENTER
    }

    public void sendEmail(Email email) throws IOException{
        String line = null;

        //Initialisation of the communication with the SMTP server
        String ehlo = "EHLO prank";
        pw.println(ehlo);
        pw.flush();
        System.out.println("Cli: " + ehlo);

        do {
            line = br.readLine();
            System.out.println("Srv: " + line.toUpperCase());
        } while (!line.startsWith("250 "));

        //we give the email address of the sender
        String mailFrom = "MAIL FROM: " + email.getSender();
        pw.println(mailFrom);
        pw.flush();
        System.out.println("Cli:" + mailFrom);


        do {
            line = br.readLine();
            System.out.println("Srv: " + line.toUpperCase());
        } while (line.startsWith("250 OK"));

        //We give the recipients'email addresses
        String mailTo = "RCPT TO: ";
        for(int i = 0; i < email.getRecipients().size(); ++i){
            mailTo += email.getRecipients().get(i) + ", ";
        }
        mailTo += this.email;
        pw.println(mailTo);
        pw.flush();
        System.out.println("Cli:" + mailTo);

        do {
            line = br.readLine();
            System.out.println("Srv: " + line.toUpperCase());
        } while (line.startsWith("250 Accepted"));

        String data = "DATA";
        pw.println(data);
        pw.flush();
        System.out.println("Cli:" + data);
        do {
            line = br.readLine();
            System.out.println("Srv: " + line.toUpperCase());
        } while (!line.startsWith(""));

        //The message is sent to the server
        pw.println("Cli:" + email.getMessage());
        pw.flush();
        System.out.print(email.getMessage());
        //The message is ended with a backslash, a  "." and a backslash
        pw.println(".");
        pw.flush();
        System.out.println(".");

    }




}
