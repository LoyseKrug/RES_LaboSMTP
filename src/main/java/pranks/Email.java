package pranks;

import java.util.LinkedList;

/**
 * Class representing formatted emails
 */
public class Email {

    LinkedList<String> recipients = new LinkedList();
    String sender;
    String joke;

    /**
     * Create the structure of the email
     * @param group
     * @param joke
     */
    public Email(LinkedList<String> group, String joke){
        this.joke = joke;
        this.sender = group.get(0);
        for(int i = 1; i < group.size(); ++i){
            recipients.add(group.get(i));
        }
    }

    /**
     * Getter for the list of recipients of the mail
     * @return list of recipients as a LinkedList<String>
     */
    public LinkedList<String> getRecipients(){
        return recipients;
    }

    /**
     * Getter for the sender of the mail
     * @return sender of the mail as a String
     */
    public String getSender() { return sender; }

    /**
     * Method that create the content of the message to send
     * @return the message to send, as a LinkedList<String>
     */
    public String getMessage() {
        //We create a LinkedList of Strings, containing the message in order to send it line by line to the server
        //and therefore get the correct format for backslashes
        String message = "From: " + sender + "\r\n" + "To: ";
        for(int i = 0; i < recipients.size(); ++i){
            if(i != 0){
                message += ", ";
            }
            message += recipients.get(i);
        }
        message += joke;
        return message;
    }
}
