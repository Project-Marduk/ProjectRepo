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
//@Getter @Setter
public class DrawingBoardAJDBC extends Model {
//    private final double SIZE_DEFAULT = 1000;

    public DrawingBoardAJDBC(){}


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