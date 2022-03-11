package IM.Javalin.Server;

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
