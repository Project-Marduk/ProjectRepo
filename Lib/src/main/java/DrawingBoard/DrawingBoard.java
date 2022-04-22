package DrawingBoard;

import FactoryElements.InputObject;
import DrawingObjects.DrawingObject;
import FactoryElements.DrawingObjectFactory;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import com.google.gson.reflect.TypeToken;
import org.javalite.activejdbc.Model;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//@Table("Drawing_Board")
@Getter @Setter
public class DrawingBoard implements Serializable {
    private final double SIZE_DEFAULT = 1000;

    String name;
    private String id;
    private ArrayList<String> users;

    Map<String, DrawingObject> objects = new HashMap<>();

    double xMax;
    double yMax;
    DrawingObjectFactory drawingObjectFactory = new DrawingObjectFactory();
    static int idIndex = 1;

    public DrawingBoard(){
        yMax = SIZE_DEFAULT;
        xMax = SIZE_DEFAULT;
    }

    public DrawingBoard(double xMax, double yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        name = "diagram name";
        id = null;
    }

    public ArrayList<DrawingObject> getList(){
        return new ArrayList<DrawingObject>(objects.values());
    }


    public void setId(String id) {
        if (id == null){
            this.id = "0";
        }
    }
    public String getId() {
        return id;
    }

    /**
     * The function adds a Drawing object to the Hashmap and provides in IF
     *
     * @param inObj the input object for the thing you want to make.
     * @return The Drawing object just created
     */
    public DrawingObject addObject(InputObject inObj){
        String id = Integer.toString(idIndex);
        idIndex++;
        DrawingObject d = drawingObjectFactory.create(inObj, id);
        if (d == null){
            System.out.println("INVALID OBJECT, Factory returned Null");
        }
        objects.put(id, d);
        System.out.println("");
        return objects.get(id);
    }

    public void removeObject(String id){
        objects.remove(id);
    }

    public DrawingObject getObject(String id){
        return objects.get(id);
    }

    public Set<String> getIds(){
        return objects.keySet();
    }

    public String getSVGDataFromId(String id){
        return objects.get(id).getSVGData();
    }


    /**
     * @author
     * @return
     * Serializes all the objects in the map to JSON
     */
    public String serializeDrawingBoardToJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @author David Lindeman
     * Converts a drawing object to a JSON string using GSON
     */
    //reference for reading JSON files to java: https://attacomsian.com/blog/gson-read-json-file
    public String toJSON(DrawingObject dwObj) {
        //create Gson instance
        Gson gson = new Gson();
        //create json string to hold data
        String jsonString = gson.toJson(dwObj);
        return null;
    }

    /**
     * @author David Lindeman
     * @param jsonStr
     * @return
     * Takes a JSON string and converts it into a drawing board object
     */
    public DrawingObject fromJSON(String jsonStr){
        try {
            //create Gson instance
            Gson gson = new Gson();

            //set type for scoreboard
            Type drawingObjType = new TypeToken<DrawingObject>(){}.getType();

            //convert JSON string to scoreboard obj
            DrawingObject drawingObj = gson.fromJson(jsonStr, drawingObjType);

            return drawingObj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}