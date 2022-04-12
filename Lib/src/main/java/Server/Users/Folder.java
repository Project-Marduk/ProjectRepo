package Server.Users;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Folder Class, a simple class to hold a set of Diagram id's
 *
 * To be used with the User class for to transmit all of a user's data from server to client.
 *
 * TODO Update diagramNames to there best form of ID when the Diagram data structer is finalized.
 *
 * TODO This can be reworked into an ACTIVE JDBC model class, once the Structure of the Info Manager is decided.
 *
 * @author Traae
 * @version 0.1.0
 */
public class Folder implements Serializable {

    private String name;
    private ArrayList<String> diagramNames;

    public Folder(String folderName){
        name = folderName;
        diagramNames = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<String> getDiagramNames() {
        return diagramNames;
    }
}
