import java.util.LinkedList;

public class VictimGroup {
    private String sender;
    private LinkedList<String> recipients = new LinkedList();
    private int size;

    public VictimGroup(int size, LinkedList<String> victims){
        //A IMPLEMENTER
    }

    public String getSender(){
        return sender;
    }

    public LinkedList<String> getRecipients(){
        return recipients;
    }

}
