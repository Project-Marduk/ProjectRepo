package Server.Connection;

import Server.Resources.ServerReturns;
import Server.Resources.ApiCommands;
import com.google.gson.Gson;

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
 * TODO currently the 2 methods are commented out.
 * TODO Un-comment them after we finalize the Diagram class. (highlight ctrl + /)
 * TODO enable a better sever message system
 *
 *TODO currently the 2 methods are commented out.
 * TODO Un-comment them after we finalize the Diagram class. (highlight ctrl + /)
 * TODO update them to work properly with the Diagram,
 * TODO or which ever class represents the top of the data structure.
 *
 *
 * @author Traae
 * @version 0.9.0
 */
public class ServerConnection {
    private static final String httpSuffix = "http://%s:%s";
    private static final String ROOT_CALL = httpSuffix + ApiCommands.root.path();
    private static final String STATUS_CALL = httpSuffix + ApiCommands.getStatus.path();
    private static final String ERROR_CALL = httpSuffix + ApiCommands.getError.path();
    private static final String PNG_CALL = httpSuffix + ApiCommands.renderPNG.path();
    private static final String SVG_CALL = httpSuffix + ApiCommands.renderSVG.path();

    private static final String REGISTER_CALL = httpSuffix + ApiCommands.registerUser.path();
    private static final String LOGIN_CALL = httpSuffix + ApiCommands.loginUser.path();
    private static final String USER_CALL = httpSuffix + ApiCommands.getUserData.path();
    private static final String LOAD_CALL = httpSuffix + ApiCommands.getDiagram.path();
    private static final String SAVE_CALL = httpSuffix + ApiCommands.saveDiagram.path();

    private static ServerConnection INSTANCE = null;
    private String address;
    private String port;
    private String expectedMessage;
    private boolean initialized = false;
    private HttpClient client;
    private Gson gson;

    // constructor
    protected ServerConnection(){
        expectedMessage = ServerReturns.serverMessage.message();
        gson = new Gson();
    }

    /**
     * @return the concrete implementation instance of a Connection.
     */
    public ServerConnection instance() {
        if (INSTANCE == null){
            INSTANCE = new ServerConnection();
        }
        return INSTANCE;
    }


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
    public ServerReturns getStatus(){
        try {
            HttpRequest request = createGet(STATUS_CALL);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ServerReturns status = Enum.valueOf(ServerReturns.class, response.body());
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

    //    /**
//     * Render Png
//     *
//     * Send a Diagram Json to the File Exporter and get a
//     *
//     *
//     * @param diagram The diagram to be rendered.
//     * @return a PNGformat object that can be written to a file.
//     */
//    public PNGformat renderPNG(DIAGRAM diagram) {
//        String input = gson.toJson(diagram);
//        HttpRequest request = createPost(PNG_CALL, input);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return gson.fromJson(response.body(), PNGformat.class);
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in FEconnection.renderPNG(): " + ex.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * render SVG
//     *
//     * @param diagram the diagram to be compiled into an svg file
//     * @return SVGformat object for writeing to file.
//     */
//    public SVGformat renderSVG(DIAGRAM diagram) {
//        String input = gson.toJson(diagram);
//        HttpRequest request = createPost(SVG_CALL, input);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return gson.fromJson(response.body(), SVGformat.class);
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in FEconnection.renderSVG(): " + ex.getMessage());
//            return null;
//        }
//    }

//    /**
//     * Register User
//     *
//     * Send a username to the Info Manager to register it.
//     *
//     *
//     * @param userName the username of the New User.
//     * @return a Server Response enum
//     */
//    public ServerResponses registerUser(String userName) {
//        HttpRequest request = createPost(REGISTER_CALL, userName);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return Enum.valueOf(ServerResponses.class, response.body());
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in IMconnection.registerUser(): " + ex.getMessage());
//            return ServerResponses.clientSideException;
//        }
//    }
//
//    /**
//     * Login User
//     *
//     * @param userName username trying to log in
//     * @return a string specifying details of confirmation.
//     */
//    public ServerResponses loginUser(String userName) {
//        HttpRequest request = createPost(LOGIN_CALL, userName);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return Enum.valueOf(ServerResponses.class, response.body());
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in IMconnection.loginUser(): " + ex.getMessage());
//            return ServerResponses.clientSideException;
//        }
//    }

//    /**
//     * get User Data.
//     *
//     * the User, their folders and all their available diagram files.
//     *
//     * @param userName the username of the Data you want to fetch.
//     * @return a User.class of the requested Username, returns NULL
//     */
//    public User getUserData(String userName) {
//        HttpRequest request = createPost(USER_CALL, userName);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            User user = gson.fromJson(response.body(), User.class);
//            return user;
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in IMconnection.getUserData(): " + ex.getMessage());
//            return null;
//        }
//    }

//    /**
//     * Get Diagram function
//     *
//     * Pass in the diagram's identifying information,
//     * get the diagram object from the server.
//     *
//     * TODO Change DIAGRAM to the finalized data structure class
//     *
//     * @param userName the owner of the diagram.
//     * @param diagramName the diagram's identifier
//     * @return DIAGRAM object, or null if there is an exception.
//     */
//    public DIAGRAM getDiagram(String userName, String diagramName) {
//        HashMap<String, String> userDiagram = new HashMap<>();
//        userDiagram.put("user", userName);
//        userDiagram.put("diagram", diagramName);
//        String toLoad = gson.toJson(userDiagram);
//        HttpRequest request = createPost(LOAD_CALL, toLoad);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            DIAGRAM d = gson.fromJson(response.body(), DIAGRAM.class);
//            return d;
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
//            return null;
//        }
//    }
//
//    /**
//     * Save Diagram
//     *
//     * Pass in the Diagram Data structure for the Info Manager to save.
//     *
//     * TODO edit DIAGRAM to work with the finalized data structure.
//     * TODO Change the string message system to something better.
//     *
//     * @param diagram the Diagram data structure to be saved.
//     * @return String containing a success message.
//     */
//    public String saveDiagram(DIAGRAM diagram) {
//        String toSave = gson.toJson(diagram);
//        HttpRequest request = createPost(LOAD_CALL, toSave);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            String out = response.body();
//            return out;
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
//            return "An exception was thrown in the IMconnection.saveDiagram().";
//        }
//    }



}
