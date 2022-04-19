package Marduk.Javalin.Server.DataManager;

import Server.Users.User;

import java.util.ArrayList;

/**
 * The Info Manager
 *
 * Handle's call to the Database for saving and loading.
 *
 * @author Traae
 */
public class DataManagerDriver {
    private static DataManagerDriver instance = null;

    private ArrayList<User> loggedIn;

    private DataManagerDriver(){}
    public static DataManagerDriver getInstance() {
        if (instance == null){
            instance = new DataManagerDriver();
        }
        return instance;
    }


}
