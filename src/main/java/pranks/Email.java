package pranks;

import java.util.LinkedList;

public class Email {

    LinkedList<String> recipients;
    String sender;
    String joke;

    public Email(LinkedList<String> group, String joke){
        this.joke = joke;
        this.sender = group.get(0);
        for(int i = 1; i < group.size(); ++i){
            recipients.add(group.get(i));
        }
    }

    public LinkedList<String> getRecipients(){
        return recipients;
    }

    public String getSender() { return sender; }

    public LinkedList<String> getMessage() {
        LinkedList<String> message = new LinkedList();
        String from = "From: " + sender;
        String to = "To: ";
        for(int i = 0; i < recipients.size(); ++i){
            if(i != 0){
                to += ", ";
            }
            to += recipients.get(i);
        }
        message.add(to);
        message.add(joke);
        return message;
    }
}
