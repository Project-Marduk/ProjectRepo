package IM.Javalin.Server;

/**
 * The Info Manager
 *
 * Handle's call to the Database for saving and loading.
 *
 * @author David
 */
public class InfoManager {
    private static InfoManager instance = null;

    private InfoManager(){}
    public static InfoManager getInstance() {
        if (instance == null){
            instance = new InfoManager();
        }
        return instance;
    }
}
