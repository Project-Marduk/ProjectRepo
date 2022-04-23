package ActiveJDBCObjecs;


import DrawingBoard.DrawingBoard;
import FactoryElements.InputObject;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import DrawingBoard.inputBoard;
import org.javalite.activejdbc.LazyList;

import java.util.ArrayList;

@Table("drawing_board")
public class DrawingBoardAJDBC extends Model {
//    private final double SIZE_DEFAULT = 1000;

    public DrawingBoardAJDBC(){}


    public DrawingBoard toDrawingBoard(){
        ArrayList<InputObject> inObjs = new ArrayList<>();
        LazyList<DrawingObjectAJDBC> drawingObjs = this.getAll(DrawingObjectAJDBC.class);
        for (DrawingObjectAJDBC dwObj: drawingObjs) {
            inObjs.add(dwObj.getInputObject());
        }
        inputBoard inBoard = new inputBoard();
        inBoard.setInObjects(inObjs);
        DrawingBoard dwBoard = new DrawingBoard(inBoard);
        dwBoard.setId(this.getInteger("id"));
        dwBoard.setFolder_id(this.getInteger("folder_id"));
        return dwBoard;
    }

    public inputBoard toInputBoard(){
        inputBoard inBoard = new inputBoard();
        ArrayList<InputObject> inObjs = new ArrayList<>();
        LazyList<DrawingObjectAJDBC> drawingObjs = this.getAll(DrawingObjectAJDBC.class);
        for (DrawingObjectAJDBC dwObj: drawingObjs) {
            inObjs.add(dwObj.getInputObject());
        }
        inBoard.setInObjects(inObjs);

        return inBoard;
    }

}