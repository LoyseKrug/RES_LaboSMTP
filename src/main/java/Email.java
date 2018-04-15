import java.util.LinkedList;

public class Email {

    VictimGroup victims;
    Joke joke;

    public Email(VictimGroup victims, Joke joke){
        this.victims = victims;
        this.joke = joke;
    }

    public Joke getJoke() {
        return joke;
    }

    public VictimGroup getVictims(){
        return victims;
    }
}
