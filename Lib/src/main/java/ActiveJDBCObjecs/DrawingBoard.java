package ActiveJDBCObjecs;

import DrawingObjects.DrawingObject;
import FactoryElements.InputObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Table("drawing_board")
@Getter @Setter
public class DrawingBoard extends Model {
    private final double SIZE_DEFAULT = 1000;

    String name;
    private int id;

    double xMax;
    double yMax;
    static int idIndex = 1;

    public DrawingBoard(){}

    public String getId() {
        return "";
    }

    public void addObject(InputObject inObj){
    }

    public void removeObject(String id){
    }

    public void getObject(String id){

    }

    public void getIds(){

    }

    public String getSVGDataFromId(String id){
        return "";
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




}