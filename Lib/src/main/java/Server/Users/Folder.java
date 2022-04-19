package Server.Users;

import DrawingBoard.DrawingBoard;

import java.io.Serializable;
import java.util.LinkedList;

public class Folder implements Serializable {
    private User owner;
    String folderName;
    LinkedList<String> drawingBoardIds;

    public Folder(){
        folderName = "";
        owner = null;
        drawingBoardIds = new LinkedList<>();
    }


}
