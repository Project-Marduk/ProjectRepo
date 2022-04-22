package DrawingBoard;

import FactoryElements.InputObject;
import DrawingObjects.DrawingObject;
import FactoryElements.DrawingObjectFactory;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import com.google.gson.reflect.TypeToken;

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

    // This is Obsolete, I'm leaving it for David to take care of.
    // All object types can be referenced using the DrawingObjectTypes Enum
    String[] objectTypes = new String[]{
        "IFML_Action",
        "IFML_Activation_Expression",
        "IFML_Container",
        "IFML_Event",
        "IFML_Module",
        "IFML_Parameter",
        "IFML_View_Component",
        "IFML_View_Component_Part",
        "IFML_Line",
        "Line",
        "Wireframe_Object"
    };

    double xMax;
    double yMax;
    DrawingObjectFactory drawingObjectFactory = new DrawingObjectFactory();
    static int idIndex = 1;

    public DrawingBoard(){}
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
            this.id = id;
        }
    }
    public String getId() {
        return id;
    }

    /**
     * The function adds a Drawing object to the Hashmap and provides it an ID.
     *
     *
     *
     * @param inObj the input object for the thing you want to make.
     * @return The Index assigned as the new Objects id.
     */
    public DrawingObject addObject(InputObject inObj){
        String id = Integer.toString(idIndex);
        idIndex++;
        return objects.put(id, drawingObjectFactory.create(inObj, id));
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