package Server.Resources;

/**
 * An enumeration of basic greeting messages that will appear for each server.
 *
 * These are intended to be used in a get call on the root "/" path of the server.
 * They can function both as a greeting message, and as a String to test a connection with.
 *
 * TODO Since we streamlined our serves, this enum is obsolete. To be reworked.
 *
 * @author Traae
 * @version 1.0.0
 */
public enum ServerMessages {
    serverMessage("This is the Marduk Server");

    private final String message;
    private ServerMessages(String s) {
        this.message = s;
    }
    public String getMessage(){
        return message;
    };
}
