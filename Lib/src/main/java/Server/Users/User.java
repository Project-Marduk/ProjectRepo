package Server.Users;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A Class the holds User information and their associated Diagrams;
 *
 * Used fro transmitting data from Info Manager to the client.
 *
 * TODO This can be reworked into an ACTIVE JDBC model class, once the Structure of the Info Manager is decided.
 *
 * @author Traae
 * @version 0.1.0
 */
public class User implements Serializable {
    private String name;

    private Folder main;
    private ArrayList<Folder> folders;

    public User(String userName){
        name = userName;
        main = new Folder("Main");
        folders = new ArrayList<>();
        folders.add(main);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Folder> getFolders() {
        return folders;
    }
    public Folder getMainFolder() {
        return main;
    }
}
