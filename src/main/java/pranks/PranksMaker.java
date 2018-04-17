package pranks;

import smtp.Client;
import util.CommentedFileReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

/**
 * Class generating pranks and sending forged emails to the victims
 */
public class PranksMaker{

    Client smtpClient;
    LinkedList<String> emailAddresses = new LinkedList();
    LinkedList<String> jokes = new LinkedList();
    Random random = new Random();
    int groupSize;

    CommentedFileReader cfr = null;
    String line;

    /**
     * Constructor of PrankMaker
     * @param smtpClient
     * @param numberOfJokes
     * @param groupSize
     * @throws IOException
     */
    public PranksMaker(Client smtpClient, int numberOfJokes, int groupSize) throws IOException {
        this.smtpClient = smtpClient;
        this.groupSize = groupSize;

        //put all the separated e-mail addresses in a list
        CommentedFileReader cfr = new CommentedFileReader("./src/main/resources/emails.txt");
        while((line = cfr.getNextLine()) != null){
            emailAddresses.add(line);
        }

        if(groupSize < 3 || (groupSize * numberOfJokes) > emailAddresses.size()){
            throw new IOException("the group size does't respect the rules");
        }

        //put all the separated jokes in a list
        CommentedFileReader cfr2 = new CommentedFileReader("./src/main/resources/pranks.txt");
        String prank = "";
        boolean first = true;
        while((line = cfr2.getNextLine()) != null){
            //The jokes are split with "=="
            if(line.equals("==")){
                jokes.add(prank);
                prank = "";
                first = true;
            } else {
                if(first){
                    first = false;
                } else{
                    prank += "\r\n";
                }
                prank += line;
            }
        }

        //
        for(int i = 0; i < numberOfJokes; ++i){
            Email email = new Email(getGroup(), getJoke());
            smtpClient.sendEmail(email);
        }
        smtpClient.disconnect();
    }

    //get a random joke in the list of jokes
    private String getJoke(){
        int jokeNumber = random.nextInt(jokes.size());
        return jokes.get(jokeNumber);
    }

    //create a random group from the list of emails
    private LinkedList<String> getGroup(){
        LinkedList<String> victims = new LinkedList<String>();
        int i = 0;
        while(i < groupSize){
            int nextVictim = random.nextInt(emailAddresses.size());
            String victim = emailAddresses.get(nextVictim);
            if(!victims.contains(victim)){
                victims.add(victim);
                ++i;
                emailAddresses.remove(victim);
            }
        }
        return victims;
    }

}
