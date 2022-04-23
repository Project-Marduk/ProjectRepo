package Marduk.Javalin.Server.DataManager;

import ActiveJDBCObjecs.DrawingBoardAJDBC;
import ActiveJDBCObjecs.DrawingObjectAJDBC;
import DrawingBoard.DrawingBoard;
import DrawingBoard.inputBoard;
import FactoryElements.InputObject;

import Server.ResponseManagement.RespondingClass;
import Server.ResponseManagement.ResponseManager;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.connection_config.DBConfiguration;

import java.util.ArrayList;


public class DataManagerDriver implements RespondingClass {
    private static DataManagerDriver instance = null;
    ResponseManager responseManager;

    private DataManagerDriver() {}
    public static DataManagerDriver getInstance() {
        if (instance == null) {
            instance = new DataManagerDriver();
        }
        return instance;
    }
    @Override
    public void setResponseManager(ResponseManager r) {
        responseManager = r;
    }

    // TODO You sure you want to open and close with every command?
    // TODO I can set that up sever side, just want to make sure.
    public void openDatabase() {
        DBConfiguration.loadConfiguration("/database.properties");
        Base.open();
    }

    public void closeDatabase() {
        Base.close();
    }

    /**
     * TODO  this
     *
     * @return true is connection is validated
     */
    public void validateDatabaseConnection() {
        // CODE THAT VALIDATE THE CONNECTION;
        responseManager.setResponseBySuccess(true);
    }

    /**
     * @author David Lindeman
     * @param id
     * @return
     * finds all drawing objects of drawing board based on input id
     */
    public inputBoard getDrawingBoard(String id) {
        boolean result = false;
        DrawingBoardAJDBC dwBoardAJDBC = DrawingBoardAJDBC.findById(id);
        inputBoard inBoard = new inputBoard();
        if(dwBoardAJDBC != null){
            inBoard = dwBoardAJDBC.toInputBoard();
            result = true;
        }
        responseManager.setResponseBySuccess(result);
        return inBoard;
    }

    // TODO save the board and objects in toSave
    // TODO responseManager.setResponse
    public void saveDrawingBoard(inputBoard toSave) {
        boolean result = false;
        DrawingBoardAJDBC dwBoard = DrawingBoardAJDBC.findById(toSave.id);
        DrawingObjectAJDBC dwObj;
        InputObject tempObj;
        //if the drawing board exists iterates through all items and checks if they exist in the db if so they update else it creates new
        if(dwBoard != null){
            for (InputObject inObj : toSave.getInputObjectsList()) {
                dwObj = DrawingObjectAJDBC.findById(inObj.getId());
                if(dwObj != null){
                    updateDrawingObject(inObj);
                }
                else{
                    //create drawing objects returns an InputObject but we dont need it for the purpose of saving the board
                    tempObj = createDrawingObject(inObj);
                }
            }
            result = true;
        }
        responseManager.setResponseBySuccess(result);
    }

    // TODO the client can simultaneously create their own and send it here
    // TODO responseManager.setResponse
    // for now this just creates and save it to the database, assuming
    // assuming the client will keep and continue to use their copy.
    public InputObject createDrawingObject(InputObject inObj) {
        if (inObj != null) {

            Double p1;
            Double p2 = null;
            String t1;
            String t2 = null;
            String t3 = null;

            if (inObj.getParams().length == 1) {
                p1 = inObj.getParams()[0];
            } else {
                p1 = inObj.getParams()[0];
                p2 = inObj.getParams()[1];
            }

            if (inObj.getText().length == 1) {
                t1 = inObj.getText()[0];
            } else if (inObj.getText().length == 2) {
                t1 = inObj.getText()[0];
                t2 = inObj.getText()[1];
            } else {
                t1 = inObj.getText()[0];
                t2 = inObj.getText()[1];
                t3 = inObj.getText()[2];
            }

            //explicity way to set variables
            DrawingObjectAJDBC dwgObj = new DrawingObjectAJDBC();
            dwgObj.set("drawing_board_id", inObj.getParent_id());
            dwgObj.set("shape_type", inObj.getShapeType());
            dwgObj.set("x_cord", inObj.getXCord());
            dwgObj.set("y_cord", inObj.getYCord());
            dwgObj.set("param_one", p1);
            dwgObj.set("param_two", p2);
            dwgObj.set("color", inObj.getColor());
            dwgObj.set("style", inObj.getStyle());
            dwgObj.set("fill", inObj.getFill());
            dwgObj.set("text_one", t1);
            dwgObj.set("text_two", t2);
            dwgObj.set("text_three", t3);
            dwgObj.saveIt();

            responseManager.setResponseBySuccess(true);
            return dwgObj.getInputObject();
        }
        else{
            responseManager.setResponseBySuccess(false);
            return null;
        }
    }

    public void deleteDrawingObject(String id) {
        boolean result = false;
        DrawingObjectAJDBC dwgObj = DrawingObjectAJDBC.findById(Integer.parseInt(id));
        //catches entry cannot be found
        if (dwgObj != null) {
            dwgObj.delete();
            result = true;
        }
        responseManager.setResponseBySuccess(result);
    }


    public void updateDrawingObject(InputObject inObj) {
        boolean result = false;

        if (inObj != null) {
            Double p1;
            Double p2 = null;
            String t1;
            String t2 = null;
            String t3 = null;

            if (inObj.getParams().length == 1) {
                p1 = inObj.getParams()[0];
            } else {
                p1 = inObj.getParams()[0];
                p2 = inObj.getParams()[1];
            }

            if (inObj.getText().length == 1) {
                t1 = inObj.getText()[0];
            } else if (inObj.getText().length == 2) {
                t1 = inObj.getText()[0];
                t2 = inObj.getText()[1];
            } else {
                t1 = inObj.getText()[0];
                t2 = inObj.getText()[1];
                t3 = inObj.getText()[2];
            }

            //updates the drawing object and saves it
            DrawingObjectAJDBC dwgObj = DrawingObjectAJDBC.findById(inObj.getId());

            if (dwgObj != null) {
                dwgObj.set("drawing_board_id", inObj.getParent_id());
                dwgObj.set("shape_type", inObj.getShapeType());
                dwgObj.set("x_cord", inObj.getXCord());
                dwgObj.set("y_cord", inObj.getYCord());
                dwgObj.set("param_one", p1);
                dwgObj.set("param_two", p2);
                dwgObj.set("color", inObj.getColor());
                dwgObj.set("style", inObj.getStyle());
                dwgObj.set("fill", inObj.getFill());
                dwgObj.set("text_one", t1);
                dwgObj.set("text_two", t2);
                dwgObj.set("text_three", t3);
                dwgObj.saveIt();
                result = true;
            }
        }

        responseManager.setResponseBySuccess(result);
    }

    /**
     * @author David Lindeman
     * @param inBoard
     * @return
     * requires inBoard to have a folder id
     */
    public DrawingBoard createDrawingBoard(inputBoard inBoard) {
        DrawingBoardAJDBC dwgb = new DrawingBoardAJDBC();
        double xSize;
        double ySize;

        //set sizes
        if(inBoard.xMax > 0){xSize = inBoard.xMax;}else{xSize = 1000;}
        if(inBoard.yMax > 0){ySize = inBoard.yMax;}else{ySize = 1000;}

        dwgb.set("x_size", xSize);
        dwgb.set("y_size", ySize);
        dwgb.set("folder_id", inBoard.folderId);
        dwgb.saveIt();

        return dwgb.toDrawingBoard();
    }

    public void deleteDrawingBoard(String id) {
        boolean result = false;
        DrawingBoardAJDBC dwgb = DrawingBoardAJDBC.findById(Integer.valueOf(id));
        if (dwgb != null) {
            dwgb.delete();
            result = true;
        }
        responseManager.setResponseBySuccess(result);
    }

}
// To Here






// LEAVE THIS COMMENTED OUT

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

