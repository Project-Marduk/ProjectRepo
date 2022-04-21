package ActiveJDBCObjecs;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;

import java.lang.reflect.Type;

@Table ("drawing_object")
@BelongsTo(parent = DrawingBoard.class,
        foreignKeyName = "drawing_board_id")
public class DrawingObject extends Model {

    public DrawingObject(){

    }

    //id INTEGER NOT NULL Auto_Increment,
    //    drawing_board_id INTEGER NOT NULL,
    //    shape_type VARCHAR(20) NOT NULL,
    //    x_cord INTEGER NOT NULL,
    //    y_cord INTEGER NOT NULL,
    //    param_one INTEGER NOT NULL,
    //    param_two INTEGER,
    //    color VARCHAR(13),
    //    style VARCHAR(13),
    //    fill VARCHAR(7),
    //    text_one VARCHAR(255),
    //    text_two VARCHAR(255),
    //    text_three VARCHAR(255),
    // 	PRIMARY KEY(id)

    public String getSVGData(){
        int xCord = this.getInteger("x_cord");
        //this is where we will put the massive switch statement for transforming SVG data
        return "getSVGData";
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


}
