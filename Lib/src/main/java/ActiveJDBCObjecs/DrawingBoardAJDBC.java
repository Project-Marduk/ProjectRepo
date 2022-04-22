package ActiveJDBCObjecs;

import com.google.gson.Gson;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("drawing_board")
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