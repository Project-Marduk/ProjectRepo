package Server.Users;

import DrawingBoard.DrawingBoard;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    String username;
    private int folderCount;
    private int diagramCount;
    ArrayList<Folder> folders;
    Folder main;

    public User(){
        username = "";
        diagramCount = 0;
        folderCount = 0;
        main = new Folder();
        main.folderName = "Main";
        folders = new ArrayList<>();
        folders.add(main);
    }

    public String getNextDiagramID(){
        diagramCount = diagramCount+1;
        return username + diagramCount;
    }

    public int getNextFolderNumber() {
        folderCount = folderCount+1;
        return folderCount;
    }


    public Folder createFolder(){
        Folder f = new Folder();
        f.folderName = "Folder" + getNextFolderNumber();
        folders.add(f);
        return f;
    }
}
