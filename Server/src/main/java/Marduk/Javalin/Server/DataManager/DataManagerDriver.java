package Marduk.Javalin.Server.DataManager;

/**
 * The Info Manager
 *
 * Handle's call to the Database for saving and loading.
 *
 * @author Traae
 */
public class DataManagerDriver {
    private static DataManagerDriver instance = null;

    private DataManagerDriver(){}
    public static DataManagerDriver getInstance() {
        if (instance == null){
            instance = new DataManagerDriver();
        }
        return instance;
    }
}
