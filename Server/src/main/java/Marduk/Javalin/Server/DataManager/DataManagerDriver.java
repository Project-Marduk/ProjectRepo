package Marduk.Javalin.Server.DataManager;

import ActiveJDBCObjecs.DrawingBoardAJDBC;
import ActiveJDBCObjecs.DrawingObjectAJDBC;
import DrawingBoard.DrawingBoard;
import DrawingObjects.DrawingObject;
import FactoryElements.InputObject;
import Server.Resources.ApiCommands;

import java.util.ArrayList;


/**
 * Because of the AJDBC plugin issuse, this file prevent compiling.
 *
 * It is temporarrily commented out to be able to test other stuff.
 */

// (Intellij highlighted area comment/uncomment shortcut: (ctrl + /)
// uncomment From here:
//
//import static ActiveJDBCObjecs.CreateSVGFromDatabase.CreateSVGStringFromInputObjectJSON;
//import static ActiveJDBCObjecs.JSONHandler.arrayListToJSON;
//import static ActiveJDBCObjecs.JSONHandler.inputObjectFromJSON;
//
//import org.javalite.activejdbc.Base;
//import org.javalite.activejdbc.LazyList;
//import org.javalite.activejdbc.connection_config.DBConfiguration;
//
//
public class DataManagerDriver {
//    private static DataManagerDriver instance = null;
//
//    private DataManagerDriver() {
//    }
//
//    public static DataManagerDriver getInstance() {
//        if (instance == null) {
//            instance = new DataManagerDriver();
//        }
//        return instance;
//    }
//
//    // TODO You sure you want to open and close with every command?
//    // TODO I've got it set up but just wanted to check, - Traae.
//    public void openDatabase() {
//        DBConfiguration.loadConfiguration("/database.properties");
//        Base.open();
//    }
//
//    public void closeDatabase() {
//        Base.close();
//    }
//
//    /**
//     * TODO  this
//     *
//     * @return true is connection is validated
//     */
//    public boolean validateDatabaseConnection() {
//        // CODE THAT VALIDATE THE CONNECTION;
//        return true;
//    }
//
//    // TODO populate toReturn with the values of dwgb & the appropriate Drawing Objects
//    public DrawingBoard getDrawingBoard(String id) {
//
//        DrawingBoardAJDBC dwgb = DrawingBoardAJDBC.findById(id);
//
//        DrawingBoard toReturn = new DrawingBoard();
//
//        return toReturn;
//    }
//
//    // TODO save the board and objects in toSave
//    public void saveDrawingBoard(DrawingBoard toSave) {
//
//
//    }
//
//    // TODO the client can simultaneously create their own and send it here
//    // for now this just creates and save it to the database, assuming
//    // assuming the client will keep and continue to use their copy.
//    public void createDrawingObject(InputObject inObj) {
//        if (inObj != null) {
//
//            Double p1;
//            Double p2 = null;
//            String t1;
//            String t2 = null;
//            String t3 = null;
//
//            if (inObj.getParams().length == 1) {
//                p1 = inObj.getParams()[0];
//            } else {
//                p1 = inObj.getParams()[0];
//                p2 = inObj.getParams()[1];
//            }
//
//            if (inObj.getText().length == 1) {
//                t1 = inObj.getText()[0];
//            } else if (inObj.getText().length == 2) {
//                t1 = inObj.getText()[0];
//                t2 = inObj.getText()[1];
//            } else {
//                t1 = inObj.getText()[0];
//                t2 = inObj.getText()[1];
//                t3 = inObj.getText()[2];
//            }
//
//            //explicity way to set variables
//            DrawingObjectAJDBC dwgObj = new DrawingObjectAJDBC();
////                    dwgObj.set("id", inObj.getId());
//            dwgObj.set("drawing_board_id", inObj.getParent_id());
//            dwgObj.set("shape_type", inObj.getShapeType());
//            dwgObj.set("x_cord", inObj.getXCord());
//            dwgObj.set("y_cord", inObj.getYCord());
//            dwgObj.set("param_one", p1);
//            dwgObj.set("param_two", p2);
//            dwgObj.set("color", inObj.getColor());
//            dwgObj.set("style", inObj.getStyle());
//            dwgObj.set("fill", inObj.getFill());
//            dwgObj.set("text_one", t1);
//            dwgObj.set("text_two", t2);
//            dwgObj.set("text_three", t3);
//            dwgObj.saveIt();
//        }
//
//    }
//
//    public boolean deleteDrawingObject(String id) {
//        boolean result = false;
//        DrawingObjectAJDBC dwgObj = DrawingObjectAJDBC.findById(Integer.parseInt(param));
//        //catches entry cannot be found
//        if (dwgObj != null) {
//            dwgObj.delete();
//            result = true;
//        }
//        return result;
//    }
//
//
//    public boolean updateDrawingObject(InputObject inObj) {
//        boolean result = false;
//
//        if (inObj != null) {
//            Double p1;
//            Double p2 = null;
//            String t1;
//            String t2 = null;
//            String t3 = null;
//
//            if (inObj.getParams().length == 1) {
//                p1 = inObj.getParams()[0];
//            } else {
//                p1 = inObj.getParams()[0];
//                p2 = inObj.getParams()[1];
//            }
//
//            if (inObj.getText().length == 1) {
//                t1 = inObj.getText()[0];
//            } else if (inObj.getText().length == 2) {
//                t1 = inObj.getText()[0];
//                t2 = inObj.getText()[1];
//            } else {
//                t1 = inObj.getText()[0];
//                t2 = inObj.getText()[1];
//                t3 = inObj.getText()[2];
//            }
//
//            //updates the drawing object and saves it
//            DrawingObjectAJDBC dwgObj = DrawingObjectAJDBC.findById(inObj.getId());
//
//            if (dwgObj != null) {
//                dwgObj.set("drawing_board_id", inObj.getParent_id());
//                dwgObj.set("shape_type", inObj.getShapeType());
//                dwgObj.set("x_cord", inObj.getXCord());
//                dwgObj.set("y_cord", inObj.getYCord());
//                dwgObj.set("param_one", p1);
//                dwgObj.set("param_two", p2);
//                dwgObj.set("color", inObj.getColor());
//                dwgObj.set("style", inObj.getStyle());
//                dwgObj.set("fill", inObj.getFill());
//                dwgObj.set("text_one", t1);
//                dwgObj.set("text_two", t2);
//                dwgObj.set("text_three", t3);
//                dwgObj.saveIt();
//                result = true;
//            }
//        }
//        return result;
//    }
//
//    public DrawingBoard createDrawingBoard() {
//        DrawingBoardAJDBC dwgb = new DrawingBoardAJDBC();
//        dwgb.set("x_size", 1000);
//        dwgb.set("y_size", 1000);
//        dwgb.saveIt();
//
//        return new DrawingBoard();
//    }
//
//    public boolean deleteDrawingBoard(String id) {
//        boolean result = false;
//        DrawingBoardAJDBC dwgb = DrawingBoardAJDBC.findById(Integer.valueOf(id));
//        if (dwgb != null) {
//            dwgb.delete();
//            result = true;
//        }
//        return result;
//    }
//
}
// To Here






// LEAVE THIS COMMENTED OUT

//    //Takes in a drawing board id and returns a string of svg data
//            app.get(ApiCommands.getSVGFileData.path(),ctx ->
//
//    {
//        String param = ctx.queryParam("drawing_board_id");
//        String outString = "";
//
//        DrawingBoardAJDBC dwgb = DrawingBoardAJDBC.findById(Integer.parseInt(param));
//        ArrayList<String> outList = new ArrayList<>();
//
//        if (dwgb != null) {
//            LazyList<DrawingObjectAJDBC> lzDwgObj = dwgb.getAll(DrawingObjectAJDBC.class); //pulls a lazy list
//
//            //serializes all the drawingobjects to json
//            for (DrawingObjectAJDBC dw : lzDwgObj) {
//                outList.add(dw.toInputObjectJSON());
//            }
//        } else {
//            outString = "id not found";
//        }
//        outString = arrayListToJSON(outList);
//
//        ctx.result(CreateSVGStringFromInputObjectJSON(outString));
//    }
//
//    //returns the JSON string of a single drawing object as an input object
//            app.get(ApiCommands.getDrawingObjectData.path(), ctx ->{
//        String param = ctx.queryParam("drawing_object_id");
//        DrawingObjectAJDBC dwgObj = DrawingObjectAJDBC.findById(Integer.parseInt(param));
//        //catches entry cannot be found
//        if(dwgObj != null){
//            ctx.result(dwgObj.toInputObjectJSON());
//        }
//        else{
//            ctx.result("no drawing object with id " + Integer.parseInt(param));
//        }
//    });

//            app.post(ApiCommands.updateDrawingBoard.path(), ctx -> {
//                String param = ctx.queryParam("drawingboardjson");
//                DrawingBoard inDwgBrd;//= DrawingBoard.findById(param);
//                try{
//                    inDwgBrd = drawingBoardFromJSON(param);
//                }
//                catch(Exception e){
//                    inDwgBrd = null;
//                }
//
//                if(inDwgBrd != null){
//
//                    DrawingBoard dwgb = DrawingBoard.findById(inDwgBrd.getInteger("id"));
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!11");
//                    System.out.println(inDwgBrd.getInteger("id"));
//                    System.out.println(inDwgBrd.toString());
//                    if(dwgb != null){
//                        dwgb.set("x_size", inDwgBrd.get("x_cord"));
//                        dwgb.set("y_size", inDwgBrd.get("y_cord"));
//                    }
//                    else{
//                        ctx.result("cannot find drawing board with same id");
//                    }
//                }
//                else{
//                    ctx.result("cannot parse object from JSON");
//                }
//
//            });

