package Server.ResponseManagement;

/**
 * The Server Response Manager
 *
 * A manager class for our server to provide robust feedback to our clients.
 *
 * Allows back end Drivers and Facades to set Responses in the server after an operation.
 * Corresponds to the RespondingClass interface,
 * and uses the ServerResponses Enum.
 */
public class ResponseManager {
    private static ResponseManager INSTANCE;

    private int code;
    private String message;
    private boolean success;

    private int operationCount;
    private boolean didRespond;

    // private singleton constructor
    private ResponseManager(ServerResponses startingResponse){
        operationCount = 0;
        didRespond = false;
        setFullResponse(startingResponse);
    }
    // get singleton instance.
    public static ResponseManager instance(ServerResponses startingResponse){
        if (INSTANCE == null) {INSTANCE = new ResponseManager(startingResponse);}
        return INSTANCE;
    }

    /**
     * The function is to be called by the server using the Response manager.
     * It initiates the check to see if an operation provided a response.
     */
    public void operationInitiated(){
        operationCount = operationCount + 1;
        didRespond = false;
    }

    /**
     * RespondingClass can:
     * Set the Server's response status via the ServerResponses enum.
     *
     * Reference the enum for documentation of possible responses.
     *
     * @param response The response appropriate for the result of the back end operation.
     */
    public void setFullResponse(ServerResponses response){
        code = response.getCode();
        message = response.getMessage();
        success = response.isSuccess();
        didRespond = true;
    }

    /**
     * RespondingClass can:
     * Set a custom message as a response.
     *
     * Custom responses are always considered failures.
     *
     * Should only be used for unique cases when the operation didn't work as intended.
     *
     * @param Message the message associated with your custom response.
     */
    public void setCustomResponse(String Message){
        this.code = 2;
        this.message = Message;
        this.success = false;
        didRespond = true;
    }

    /**
     * RespondingClass can:
     * Set a basic Successful or Failure response using a boolean.
     *
     * @param operationSuccessful boolean value representing success.
     */
    public void setResponseBySuccess(Boolean operationSuccessful){
        if (operationSuccessful){
            setFullResponse(ServerResponses.successful);
        }else {
            setFullResponse(ServerResponses.failure);
        }
        didRespond = true;
    }

    /**
     * Set a Response using an integer Code.
     *
     * 0 = Failure; 1 = Success;
     * 2 = custom; Please use .setCustomResponse() instead.
     *
     * 2 & invalid code will be set to 0 instead.
     *
     * All codes correspond to the Enum.ordinal() value;
     * Reference the ServerResponses Enum for a full list
     *
     * @param Code integer code of the Response to be set.
     */
    public void setResponseByCode(int Code){
        int toSet = -1;
        for (ServerResponses r : ServerResponses.values()){
            if (r.getCode() == code){
                if (code == 2) {
                    toSet = 0;
                }else {
                    toSet = code;
                }
            }
        }
        if (toSet < 0){ toSet = 0;}
        setFullResponse(ServerResponses.values()[toSet]);
        didRespond = true;
    }

    /**
     * Check if the most recent operation updates the response.
     * Internal use only.
     */
    private void checkForResponse(){
        if (!didRespond){
            setCustomResponse("No response to previous operation.");
        }
    }

    public int getCode() {
        checkForResponse();
        return code;
    }

    public String getMessage() {
        checkForResponse();
        return message;
    }

    public boolean isSuccess() {
        checkForResponse();
        return success;
    }
}
