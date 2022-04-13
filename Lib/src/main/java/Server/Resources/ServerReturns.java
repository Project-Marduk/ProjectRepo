package Server.Resources;


/**
 * An enumeration of server statuses.
 *
 * allGood - should be used for Server is running, no exceptions found.
 * encounteredError - an Exception was thrown in the server and the message has been saved for reading.
 * fatalError - The server has encountered a fatal error.
 *
 *
 * TODO I whipped this up in 5 seconds, I need design a status system. then update this.
 *
 * @author Traae
 * @version 0.1.0
 */
public enum ServerStatuses {
    serverMessage("This is the Marduk Server"),
    allGood("Server is running. No Errors."),
    encounteredError("Server has encountered an Error."),
    processing("Server is busy processing"),
    operationSuccessful(),
    operationFailed,
    operationNotPossible,
    clientSideException;

    private final String message;
    private ServerStatuses(String s) {
        this.message = s;
    }
    public String Message(){
        return message;
    };

}
