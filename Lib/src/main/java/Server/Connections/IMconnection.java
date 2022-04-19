package Server.Connections;

import Server.Resources.ServerMessages;
import Server.Resources.ServerResponses;
import Server.Users.User;
import Server.apiCommands.DMapi;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * The Info Manager connection class.
 *
 * Contains methods that interface with the Info Manager Server's Api.
 *
 * TODO currently the 2 methods are commented out.
 * TODO Un-comment them after we finalize the Diagram class. (highlight ctrl + /)
 *
 * TODO enable a better sever message system
 *
 * @author Traae
 * @version 0.1.0
 */
public class IMconnection extends Connection{
    private static final String REGISTER_CALL = httpSuffix + DMapi.registerUser.path();
    private static final String LOGIN_CALL = httpSuffix + DMapi.loginUser.path();
    private static final String USER_CALL = httpSuffix + DMapi.getUserData.path();
    private static final String LOAD_CALL = httpSuffix + DMapi.getDiagram.path();
    private static final String SAVE_CALL = httpSuffix + DMapi.saveDiagram.path();

    Gson gson;


    // Constructor
    private IMconnection(){
        super();
        //expectedMessage = ServerMessages.IMmessage.getMessage();
        gson = new Gson();
    }

    /**
     * IMconnection is a singleton class, this is the instance getter.
     *
     * @return instance of the IMconnection singleton class
     */
    @Override
    public Connection instance() {
        if (INSTANCE == null){
            INSTANCE = new IMconnection();
        }
        return INSTANCE;
    }


    /**
     * Register User
     *
     * Send a username to the Info Manager to register it.
     *
     *
     * @param userName the username of the New User.
     * @return a Server Response enum
     */
    public ServerResponses registerUser(String userName) {
        HttpRequest request = createPost(REGISTER_CALL, userName);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Enum.valueOf(ServerResponses.class, response.body());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in IMconnection.registerUser(): " + ex.getMessage());
            return ServerResponses.clientSideException;
        }
    }

    /**
     * Login User
     *
     * @param userName username trying to log in
     * @return a string specifying details of confirmation.
     */
    public ServerResponses loginUser(String userName) {
        HttpRequest request = createPost(LOGIN_CALL, userName);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Enum.valueOf(ServerResponses.class, response.body());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in IMconnection.loginUser(): " + ex.getMessage());
            return ServerResponses.clientSideException;
        }
    }

    /**
     * get User Data.
     *
     * the User, their folders and all their available diagram files.
     *
     * @param userName the username of the Data you want to fetch.
     * @return a User.class of the requested Username, returns NULL
     */
    public User getUserData(String userName) {
        HttpRequest request = createPost(USER_CALL, userName);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            User user = gson.fromJson(response.body(), User.class);
            return user;
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error caught in IMconnection.getUserData(): " + ex.getMessage());
            return null;
        }
    }

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
