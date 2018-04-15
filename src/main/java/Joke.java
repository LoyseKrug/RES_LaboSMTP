import java.util.LinkedList;
import java.util.Random;

public class Joke {

    private String joke;

    public Joke(LinkedList<String> jokes){
        Random random = new Random();
        int jokeNumber = random.nextInt(jokes.size());
        joke = jokes.get(jokeNumber);
    }

    public String getJoke() {
        return joke;
    }
}
