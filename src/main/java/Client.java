import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Client {

    static final Logger LOG = Logger.getLogger(Client.class.getName());
    final static int BUFFER_SIZE = 1024;

    Socket clientSocket = null;
    OutputStream os = null;
    InputStream is = null;

    public void connect(String host, int port){
        try {
            this.clientSocket = new Socket(host, port);
            os = clientSocket.getOutputStream();
            is = clientSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnect(){
        //A IMPLEMENTER
    }

    public void sendPrank(Email email){
        // A IMPLEMENTER
    }

    public static void main(String ... args){
        // A IMPLEMENTER
    }




}
