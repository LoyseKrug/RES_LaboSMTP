package smtp;

/**
 * Class offering SMTP commands that the Client can use
 */
public class SMTPProtocol {
    public final static String CMD_CONNECT = "EHLO";
    public final static String CMD_FROM = "MAIL FROM: ";
    public final static String CMD_TO= "RCPT TO: ";
    public final static String CMD_DATA = "DATA";
    public final static String CMD_QUIT = "QUIT";
}
