package Servers.Connections;

import Servers.Resources.HttpRequestHeader;
import Servers.Resources.ServerStatuses;
import Servers.apiCommands.GeneralApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


/**
 * The Abstract Connections class
 *
 * This class holds the core code for connecting to a server,
 * and the commands that relate to the General Api.
 *
 * Largely copy and pasted from Issac's Lights out.
 *
 *
 * @author Traae
 * @version 0.9.0
 */
public abstract class Connection {
    protected static final String httpSuffix = "http://%s:%s";
    protected static final String ROOT_CALL = httpSuffix + GeneralApi.root.path();
    protected static final String STATUS_CALL = httpSuffix + GeneralApi.getStatus.path();
    protected static final String ERROR_CALL = httpSuffix + GeneralApi.getError.path();

    protected static Connection INSTANCE = null;
    protected String address;
    protected String port;
    protected String expectedMessage;
    boolean initialized = false;
    protected HttpClient client;

    // Empty constructor
    protected Connection(){}

    /**
     * @return the concrete implementation instance of a Connection, as they are meant to be singleton.
     */
    abstract public Connection instance();


    // Initialize and disconnect, the setup and clean up functions.
    /**
     * Initializes the server address and port to connect to the microservice backend
     *
     * Code Copied from Griffith's Lights out.
     *
     * @param address Address of the service
     * @param port    Port for the service
     */
    public void initialize(String address, String port) {
        this.address = address;
        this.port = port;
        initialized = true;
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    /**
     * Disconnects by setting the address, port, and client to null
     */
    public void disconnect() {
        address = null;
        port = null;
        client = null;
    }

    // Create Get amd create Post, these handle the work for constructing their respective call types.
    /**
     * Constructs a new HttpRequest object using the POST method,
     * for the provided format string of the api call,
     * and the provided json or string data to be sent
     *
     * @param apiCall Format string for the api call
     * @param input    json or string data to be sent
     * @return A HttpRequest object ready to be used with the service
     */
    protected HttpRequest createPost(String apiCall, String input) {
        return HttpRequest.newBuilder()
                .uri(URI.create(String.format(apiCall, address, port)))
                .timeout(Duration.ofSeconds(30))
                .header(HttpRequestHeader.name.get(), HttpRequestHeader.value.get())
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();
    }


    /**
     * Constructs a new HttpRequest object using the GET method, for the provided format string of the api call
     *
     * @param apiCall Format string for the api call
     * @return The newly constructed HttpRequest object ready to be used with the service
     */
    protected HttpRequest createGet(String apiCall) {
        return HttpRequest.newBuilder()
                .uri(URI.create(String.format(apiCall, address, port)))
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();
    }

    // Call functions
    /**
     * Method to test whether the server is up and running and we have the correct address and port to connect to it.
     *
     * @return True if the client can connect to the service, false otherwise
     */
    public boolean testConnection() {
        if (initialized){
            HttpRequest request = createGet(ROOT_CALL);
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body().equals(expectedMessage);
            } catch (IOException | InterruptedException ex) {
                System.out.println("Error caught in Connection.test(): " + ex.getMessage());
                return false;
            }
        } else return false;
    }

    /**
     * Get's the server's root greeting message.
     *
     * @return The server's message. (or an error message)
     */
    public String getServerMessage() {
        HttpRequest request = createGet(ROOT_CALL);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in Connection.getServerMessage(): " + ex.getMessage());
            return "An exception was thrown client side in the Server Message Call.";
        }
    }

    /**
     * Gets the server's status in the form of the ServerStatuses enum.
     *
     * @return an ServerStatuses enum representing the server's status. Null is the call fails.
     */
    public ServerStatuses getStatus(){
        try {
            HttpRequest request = createGet(STATUS_CALL);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ServerStatuses status = Enum.valueOf(ServerStatuses.class, response.body());
            return status;
        } catch (Exception e){
            System.out.println("Error caught in Connection.getStatus(): " + e.getMessage());
            return null;
        }
    }

    /**
     * Get's the last recorded Error message from the Server.
     *
     * @return a string of the error message. Null if the call fails.
     */
    public String getErrorMessage(){
        try{
            HttpRequest request = createGet(ERROR_CALL);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String errorMessage = response.body();
            return errorMessage;
        }catch (Exception e){
            System.out.println("Error caught in Connection.getErrorMessage(): " + e.getMessage());
            return null;
        }

    }




//    public CalculatorOutputs random(CalculatorInputs input) {
//        try{
//            return sendInputs(input, RANDOMIZE_CALL);
//        }catch(Exception e){
//            return new CalculatorOutputs(0);
//        }
//    }


    // JSON INPUTS FUNCTION
//    public CalculatorOutputs sendInputs(CalculatorInputs input, String call) throws IOException, InterruptedException {
//        Gson gson = new Gson();
//        String json = gson.toJson(input);
//        HttpRequest request = createPost(call, json);
//        return getOutput(request);
//    }


//    /**
//     * Sends a GET request to obtain the current state of the game using the provided HttpRequest object
//     *
//     * @param request the request object for the GET call
//     * @return The current game state
//     * @throws IOException          if there was an error connecting to the service via the network
//     * @throws InterruptedException if the request timed out
//     */
//    private CalculatorOutputs getOutput(HttpRequest request) throws IOException, InterruptedException {
//        Gson gson = new Gson();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        CalculatorOutputs output = gson.fromJson(response.body(), CalculatorOutputs.class);
//        return output;
//    }



}
