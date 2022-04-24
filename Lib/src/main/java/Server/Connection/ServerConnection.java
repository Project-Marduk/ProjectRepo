package Server.Connection;

import Server.Files.PNGformatData;
import Server.Files.SVGformatData;
import Server.ResponseManagement.ServerResponses;
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
    private static final String ROOT_CALL = httpSuffix + ApiCommands.root;
    private static final String UP_CALL = httpSuffix + ApiCommands.up;
    private static final String CODE_CALL = httpSuffix + ApiCommands.getResponseCode;
    private static final String MESSAGE_CALL = httpSuffix + ApiCommands.getResponseMessage;
    private static final String BOOLEAN_CALL = httpSuffix + ApiCommands.getResponseBoolean;


    private static final String PNG_CALL = httpSuffix + ApiCommands.renderPNG;
    private static final String SVG_CALL = httpSuffix + ApiCommands.renderSVG;

    private static final String REGISTER_CALL = httpSuffix + ApiCommands.registerUser;
    private static final String LOGIN_CALL = httpSuffix + ApiCommands.loginUser;
    private static final String USER_CALL = httpSuffix + ApiCommands.getUserData;
    private static final String LOAD_CALL = httpSuffix + ApiCommands.createDrawingBoard;
    private static final String SAVE_CALL = httpSuffix + ApiCommands.saveDrawingBoard;


    private static ServerConnection INSTANCE = null;
    private String address;
    private String port;
    private boolean initialized = false;
    private HttpClient client;
    private Gson gson;

    // CLASS FUNCTIONS
    private ServerConnection(){
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
                .header(serverRequestHeader.name, serverRequestHeader.value)
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


    // server FUNCTIONS

    /**
     * Test connection, same as the Up method.
     * @return true is able to reach the server.
     */
    public boolean testConnection() {
        return up();
    }

    /**
     * Is the server up?
     * @return boolean is connection can be made.
     */
    public boolean up(){
        if (initialized){
            HttpRequest request = createGet(UP_CALL);
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body().equals(ServerResponses.upResponse.getMessage());
            } catch (IOException | InterruptedException ex) {
                System.out.println("Error caught in Connection.test(): " + ex.getMessage());
                return false;
            }
        } else return false;
    }

    /**
     * @return Gets the server's response message.
     */
    public String getResponseMessage() {
        HttpRequest request = createGet(MESSAGE_CALL);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in Connection.getServerMessage(): " + ex.getMessage());
            return ServerResponses.exceptionInConnection.getMessage();
        }
    }

    /**
     * @return the int of the Server Response code
     */
    public int getResponseCode(){
        HttpRequest request = createGet(CODE_CALL);
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return Integer.valueOf(response.body());
            } catch (IOException | InterruptedException ex) {
                System.out.println("Error caught in Connection.getServerMessage(): " + ex.getMessage());
                return ServerResponses.exceptionInConnection.getCode();
            }
    }

    /**
     * @return get the Server response boolean
     */
    public boolean getResponseBoolean(){
        HttpRequest request = createGet(BOOLEAN_CALL);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Boolean.valueOf(response.body());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in Connection.getServerMessage(): " + ex.getMessage());
            return ServerResponses.exceptionInConnection.isSuccess();
        }
    }

    /**
     * @return get the whole ServerResponse enum, if you're into that sort of thing.
     */
    public ServerResponses getServerResponse(){
        return ServerResponses.enumOfCode(getResponseCode());
    }




    // FILE EXPORTER FUNCTIONS
    /**
     *  EXPORT PNG CALL
     *
     * @param toRender Serialize and send the entire data Structure
     * @return PNGFormat data class for writing with the FileWriter.
     */
    public PNGformatData renderPNG(/*DATASTRUCTURE toRender*/ Object toRender){
        try{
            String json = gson.toJson(toRender);
            HttpRequest request = createPost(PNG_CALL, json);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            PNGformatData data = gson.fromJson(response.body(), PNGformatData.class);

            return data;
        }catch (Exception e){
            System.out.println("Error caught in Connection.getErrorMessage(): " + e.getMessage());
            return null;
        }
    }

    /**
     *  EXPORT SVG CALL
     *
     * @param toRender Serialize and send the entire data Structure
     * @return PNGFormat data class for writing with the FileWriter.
     */
    public SVGformatData renderSVG(/*DATASTRUCTURE toRender*/ Object toRender){
        try{
            String json = gson.toJson(toRender);
            HttpRequest request = createPost(PNG_CALL, json);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            SVGformatData data = gson.fromJson(response.body(), SVGformatData.class);

            return data;
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
