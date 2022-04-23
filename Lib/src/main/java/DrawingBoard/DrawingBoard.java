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
public class DrawingBoard implements Serializable {
    private final double SIZE_DEFAULT = 1000;

    String name;
    private String id;
    private ArrayList<String> users;

    Map<String, DrawingObject> objects = new HashMap<>();

    double xMax;
    double yMax;
    DrawingObjectFactory drawingObjectFactory = new DrawingObjectFactory();
    int idIndex;

    public DrawingBoard(){
        yMax = SIZE_DEFAULT;
        xMax = SIZE_DEFAULT;
        idIndex = 1;
    }

    public DrawingBoard(double xMax, double yMax){
        this.xMax = xMax;
        this.yMax = yMax;
        name = "diagram name";
        id = null;
        idIndex = 1;
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
        //TODO the index system is flawed.
        // How does the system reconcile between the client and the server?
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

}