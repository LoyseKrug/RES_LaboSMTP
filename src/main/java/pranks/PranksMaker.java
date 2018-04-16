package pranks;

import smtp.Client;
import util.CommentedFileReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class PranksMaker{

    Client smtpClient;
    LinkedList<String> emailAddresses = new LinkedList();
    LinkedList<String> jokes = new LinkedList();
    Random random = new Random();
    int groupSize;

    CommentedFileReader cfr = null;
    String line;

    public PranksMaker(Client smtpClient, int numberOfJokes, int groupSize) throws IOException {
        this.smtpClient = smtpClient;
        this.groupSize = groupSize;

        //put all the separated e-mail addresses in a list
        CommentedFileReader cfr = new CommentedFileReader("./src/main/resources/emails.txt");
        while((line = cfr.getNextLine()) != null){
            emailAddresses.add(line);
        }

        if(groupSize < 3 || groupSize > emailAddresses.size()){
            throw new IOException("the group size does't respect the rules");
        }

        //put all the separated jokes in a list
        CommentedFileReader cfr2 = new CommentedFileReader("./src/main/resources/pranks.txt");
        String prank = "";
        while((line = cfr2.getNextLine()) != null){
            //The jokes are split with "=="
            if(line.equals("==")){
                jokes.add(prank);
                prank = "";
            } else {
                prank += line;
            }
        }

        for(int i = 0; i < numberOfJokes; ++i){
            Email email = new Email(getGroup(), getJoke());
            smtpClient.sendEmail(email);
        }
    }

    private String getJoke(){
        int jokeNumber = random.nextInt(jokes.size());
        return jokes.get(jokeNumber);
    }

    private LinkedList<String> getGroup(){
        LinkedList<String> victims = new LinkedList<String>();
        int i = 0;
        while(i < groupSize){
            int nextVictim = random.nextInt(groupSize);
            if(!victims.contains(emailAddresses.get(nextVictim))){
                victims.add(emailAddresses.get(nextVictim));
                ++i;
            }
        }
        return victims;
    }

}
