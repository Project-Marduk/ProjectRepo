package Server.Connection;

import DrawingBoard.InputBoard;
import FactoryElements.InputObject;
import Files.PNGformatData;
import Files.SVGformatData;
import Server.ResponseManagement.ServerResponses;
import Server.Resources.ApiCommands;
import com.github.javaparser.utils.Pair;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;


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
    // Server Calls
    private static final String ROOT_CALL = httpSuffix + ApiCommands.root;
    private static final String UP_CALL = httpSuffix + ApiCommands.up;
    private static final String CODE_CALL = httpSuffix + ApiCommands.getResponseCode;
    private static final String MESSAGE_CALL = httpSuffix + ApiCommands.getResponseMessage;
    private static final String BOOLEAN_CALL = httpSuffix + ApiCommands.getResponseBoolean;
    // File Exporter calls
    private static final String PNG_CALL = httpSuffix + ApiCommands.renderPNG;
    private static final String SVG_CALL = httpSuffix + ApiCommands.renderSVG;
    // Data Manager calls - User related
    private static final String REGISTER_CALL = httpSuffix + ApiCommands.registerUser;
    private static final String LOGIN_CALL = httpSuffix + ApiCommands.loginUser;
    private static final String GET_USER_DATA_CALL = httpSuffix + ApiCommands.UserFolderIDs;
    private static final String LOGOUT_CALL = httpSuffix + ApiCommands.logoutUser;
    // Data Manager calls DrawingBoard
    private static final String CREATE_BOARD_CALL = httpSuffix + ApiCommands.createDrawingBoard;
    private static final String SAVE_BOARD_CALL = httpSuffix + ApiCommands.saveDrawingBoard;
    private static final String LOAD_BOARD_CALL = httpSuffix + ApiCommands.getDrawingBoard;
    private static final String DELETE_BOARD_CALL = httpSuffix + ApiCommands.deleteDrawingBoard;
    // Data Manager calls DrawingObjects
    private static final String CREATE_OBJECT_CALL = httpSuffix + ApiCommands.createDrawingObject;
    private static final String GET_OBJECT_CALL = httpSuffix + ApiCommands.getDrawingObject;
    private static final String DELETE_OBJECT_CALL = httpSuffix + ApiCommands.deleteDrawingObject;
    private static final String UPDATE_OBJECT_CALL = httpSuffix + ApiCommands.updateDrawingObject;
    // Folder Calls
    private static final String GET_USERS_FOLDER_IDS_CALL = httpSuffix + ApiCommands.UserFolderIDs;
    private static final String ASSIGN_USER_TO_FOLDER_CALL = httpSuffix + ApiCommands.assignFolder;
    private static final String REMOVE_USER_FROM_FOLDER_CALL = httpSuffix + ApiCommands.removeFolderUser;
    private static final String CREATE_FOLDER = httpSuffix + ApiCommands.createFolder;
    private static final String GET_FOLDERS_BOARD_IDS_CALL = httpSuffix + ApiCommands.folderBoardIDs;
    // - Database Commands
    private static final String VALIDATE_DBCONNECTION_CALL = httpSuffix + ApiCommands.validateDatabaseConnection;


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
    public static ServerConnection instance() {
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
                .header(serverRequestHeader.name, serverRequestHeader.genericValue)
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
    public String serverRootCall(){
        HttpRequest request = createGet(ROOT_CALL);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in Connection.test(): " + ex.getMessage());
            return null;
        }
    }

    /**
     * Test connection, same as the Up method.
     * @return true is able to reach the server.
     */
    public boolean testServerConnection() {
        return up();
    }

    /**
     * Is the server up?
     * @return boolean is connection can be made.
     */
    public boolean up(){
            HttpRequest request = createGet(UP_CALL);
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body().equals(ServerResponses.upResponse.getMessage());
            } catch (IOException | InterruptedException ex) {
                System.out.println("Error caught in Connection.test(): " + ex.getMessage());
                return false;
            }
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
                return response.statusCode();
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
    public PNGformatData renderPNG(InputBoard toRender){
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
    public SVGformatData renderSVG(InputBoard toRender){
        try{
            String json = gson.toJson(toRender);
            HttpRequest request = createPost(SVG_CALL, json);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            SVGformatData data = gson.fromJson(response.body(), SVGformatData.class);

            return data;
        }catch (Exception e){
            System.out.println("Error caught in Connection.getErrorMessage(): " + e.getMessage());
            return null;
        }
    }

    // Data Manager Calls

    /**
     * Validate DB connection
     * check to see if we're reaching the DB
     * @return boolean for success.
     */
    public boolean validateDatabaseConnection(){
        HttpRequest request = createGet(VALIDATE_DBCONNECTION_CALL);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return (response.statusCode() == ServerResponses.successful.getCode());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in Connection.test(): " + ex.getMessage());
            return false;
        }
    }

    /**
     * create DrawingBoard function
     *
     * Pass in the diagram's identifying information,
     * get the diagram object from the server.
     *
     * @param initialInputBoard the initialInputBoard made client side.
     * @return the input board made by the server
     */
    public InputBoard createDrawingBoard(InputBoard initialInputBoard){
        String toSend = gson.toJson(initialInputBoard);
        HttpRequest request = createPost(CREATE_BOARD_CALL, toSend);
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                InputBoard i = gson.fromJson(response.body(), InputBoard.class);
                return i;
            } catch (IOException | InterruptedException ex) {
                System.out.println("Error caught in getDiagram(): " + ex.getMessage());
                return null;
            }
    }

    public InputBoard loadDrawingBoard(String toLoad){
        HttpRequest request = createPost(LOAD_BOARD_CALL, toLoad);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            InputBoard i = gson.fromJson(response.body(), InputBoard.class);
            return i;
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return null;
        }
    }

    /**
     * Save Drawing Board
     *
     * @param toSave the inputBoard to be saved.
     * @return boolean denoting success.
     */
    public boolean saveDrawingBoard(InputBoard toSave) {
        String jsonToSave = gson.toJson(toSave);
        try {
            HttpRequest request = createPost(SAVE_BOARD_CALL, jsonToSave);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return (response.statusCode() == ServerResponses.successful.getCode());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return ServerResponses.exceptionInConnection.isSuccess();
        }
    }

    public boolean deleteDrawingBoard(String toDelete){
        HttpRequest request = createPost(DELETE_BOARD_CALL, toDelete);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return (response.statusCode() == ServerResponses.successful.getCode());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return ServerResponses.exceptionInConnection.isSuccess();
        }
    }

    public InputObject createDrawingObject(InputBoard initialInputBoard){
        String toCreate = gson.toJson(initialInputBoard);
        try {
            HttpRequest request = createPost(CREATE_OBJECT_CALL, toCreate);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), InputObject.class);
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return null;
        }
    }

    public InputObject getDrawingObject(String DrawingID){
        try {
            HttpRequest request = createPost(GET_OBJECT_CALL, DrawingID);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), InputObject.class);
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return null;
        }
    }

    public boolean deleteDrawingObject(String DrawingID){
        try {
            HttpRequest request = createPost(DELETE_OBJECT_CALL, DrawingID);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return (response.statusCode() == ServerResponses.successful.getCode());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return false;
        }
    }

    public boolean updateDrawingObject(InputBoard toUpdate){
        String toSend = gson.toJson(toUpdate);
        try {
            HttpRequest request = createPost(UPDATE_OBJECT_CALL, toSend);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return (response.statusCode() == ServerResponses.successful.getCode());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return false;
        }
    }

    public List<Integer> getFolderIDs(int UserID){
        try {
            HttpRequest request = createPost(GET_USERS_FOLDER_IDS_CALL, String.valueOf(UserID));
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            List<Integer> folderIDs = gson.fromJson(response.body(), List.class);

            return folderIDs;
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return null;
        }
    }

    public boolean assignUserToFolder(int UserID, int FolderID){
        Pair<Integer,Integer> input = new Pair<>(UserID, FolderID);
        String toSend = gson.toJson(input);
        try {
            HttpRequest request = createPost(ASSIGN_USER_TO_FOLDER_CALL, toSend);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return (response.statusCode() == ServerResponses.successful.getCode());

        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteUserFromFolder(int UserID, int FolderID){
        Pair<Integer,Integer> input = new Pair<>(UserID, FolderID);
        String toSend = gson.toJson(input);
        try {
            HttpRequest request = createPost(REMOVE_USER_FROM_FOLDER_CALL, toSend);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return (response.statusCode() == ServerResponses.successful.getCode());

        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return false;
        }
    }

    public boolean createFolder(int userID){
        try {
            HttpRequest request = createPost(CREATE_FOLDER, String.valueOf(userID));
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return (response.statusCode() == ServerResponses.successful.getCode());

        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return false;
        }
    }

    public List<Integer> getFoldersDrawingBoardIDs(int folderID){
        try {
            HttpRequest request = createPost(GET_FOLDERS_BOARD_IDS_CALL, String.valueOf(folderID));
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            List<Integer> boardIDs = gson.fromJson(response.body(), List.class);

            return boardIDs;
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in getDiagram(): " + ex.getMessage());
            return null;
        }
    }


    //public boolean updateDrawingObject(){}
    //public boolean deleteDrawingObject(){}



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
//            return ServerResponses.exceptionInConnection;
//        }
//    }

//    /**
//     * Login User
//     *
//     * @param userName username trying to log in
//     * @return ServerResponse Enum.
//     */
//    public ServerResponses loginUser(String userName) {
//        HttpRequest request = createPost(LOGIN_CALL, userName);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return ServerResponses.enumOfCode(response.statusCode());
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in IMconnection.loginUser(): " + ex.getMessage());
//            return ServerResponses.exceptionInConnection;
//        }
//    }

    //public boolean logoutUser(){}
    //public Object getUserData(){}



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


}
