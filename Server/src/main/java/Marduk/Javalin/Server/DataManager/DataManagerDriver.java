package Marduk.Javalin.Server;

/**
 * The Info Manager
 *
 * Handle's call to the Database for saving and loading.
 *
 * @author Traae
 */
public class DataManager {
    private static DataManager instance = null;

    private DataManager(){}
    public static DataManager getInstance() {
        if (instance == null){
            instance = new DataManager();
        }
        return instance;
    }
}
