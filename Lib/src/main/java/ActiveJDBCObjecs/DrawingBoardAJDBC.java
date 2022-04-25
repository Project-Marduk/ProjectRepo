package ActiveJDBCObjecs;


import DrawingBoard.DrawingBoard;
import DrawingBoard.InputBoard;
import FactoryElements.InputObject;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.HasMany;
import org.javalite.activejdbc.annotations.Table;
import org.javalite.activejdbc.LazyList;

import java.util.ArrayList;

@Table("drawing_board")
//TODO: VERIFY THIS IS NEEDED, DRAWINGOBJECT HAS A PARENT REFERENCE
@BelongsTo(parent = FolderAJDBC.class,
        foreignKeyName = "folder_id")
@HasMany(child = DrawingObjectAJDBC.class, foreignKeyName = "drawing_board_id")
public class DrawingBoardAJDBC extends Model {
//    private final double SIZE_DEFAULT = 1000;

    public DrawingBoardAJDBC(){}


    public DrawingBoard toDrawingBoard(){
        ArrayList<InputObject> inObjs = new ArrayList<>();
        LazyList<DrawingObjectAJDBC> drawingObjs = this.getAll(DrawingObjectAJDBC.class);
        for (DrawingObjectAJDBC dwObj: drawingObjs) {
            inObjs.add(dwObj.getInputObject());
        }
        InputBoard inBoard = new InputBoard();
        inBoard.setInObjects(inObjs);
        DrawingBoard dwBoard = new DrawingBoard(inBoard);
        dwBoard.setId(this.getInteger("id"));
        dwBoard.setFolder_id(this.getInteger("folder_id"));
        return dwBoard;
    }

    public InputBoard toInputBoard(){
        InputBoard inBoard = new InputBoard();
        ArrayList<InputObject> inObjs = new ArrayList<>();
        LazyList<DrawingObjectAJDBC> drawingObjs = this.getAll(DrawingObjectAJDBC.class);
        for (DrawingObjectAJDBC dwObj: drawingObjs) {
            inObjs.add(dwObj.getInputObject());
        }
        inBoard.setInObjects(inObjs);

        return inBoard;
    }

}