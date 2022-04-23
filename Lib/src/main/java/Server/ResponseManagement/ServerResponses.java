package Server.ResponseManagement;


/**
 * An enumeration of server statuses.
 *
 * allGood - should be used for Server is running, no exceptions found.
 * encounteredError - an Exception was thrown in the server and the message has been saved for reading.
 * fatalError - The server has encountered a fatal error.
 *
 *
 * @author Traae
 * @version 0.1.0
 */
public enum ServerResponses {
    // DO NOT MOVE THE FIRST 3

    // failure & successful SHOULD remain in position 0 & 1 respectively.
    failure("Operation has failed.", false),
    successful("Operation was successful.", true),

    // Enum 3 (ordinal value 2) represents custom responses,
    // If an operation behaves as intended, it doesn't need an on-the-fly custom response,
    // Thus any custom responses are considered as failures.
    // Great for returning a Exception message to the server or client.
    custom("Check the server's Message for a special response.", false),

    // Server
    startingServerResponse("This is the Marduk Server", true),
    upResponse("up", true);

    // DataManager unique responses

    // FileExporter unique responses



    private final int code;
    private final String message;
    private final Boolean success;
    private ServerResponses(String Message, boolean Success) {

        this.code = ServerResponses.this.ordinal();
        this.message = Message;
        this.success = Success;

    }
    public int getCode() {return code;}
    public String getMessage(){return message;}
    public Boolean isSuccess() {return success;}
}
