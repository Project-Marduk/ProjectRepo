package Servers.Resources;

/**
 * An enumeration of basic greeting messages that will appear for each server.
 *
 * These are intended to be used in a get call on the root "/" path of the server.
 * They can function both as a greeting message, and as a String to test a connection with.
 *
 * @author Traae
 * @version 1.0.0
 */
public enum ServerMessages {
    DCmessage("This is the Diagram Composer"),
    FEmessage("This is the File Exporter"),
    IMmessage("This is the Info Manager");

    private final String message;
    private ServerMessages(String s) {
        this.message = s;
    }
    public String getMessage(){
        return message;
    };
}
