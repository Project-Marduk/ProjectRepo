package ActiveJDBCObjecs;



import DrawingObjects.DrawingObject;
import FactoryElements.InputObject;
import com.google.gson.Gson;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;

@Table ("drawing_object")
@BelongsTo(parent = DrawingBoardAJDBC.class,
        foreignKeyName = "drawing_board_id")
public class DrawingObjectAJDBC extends Model {

    public DrawingObjectAJDBC(){

    }

    //    id INTEGER NOT NULL Auto_Increment,
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

    public InputObject getInputObject() {


        InputObject inObj = new InputObject(
                this.getString("shape_type"),
                new double[]{this.getDouble("param_one"), this.getDouble("param_two")},
                this.getString("color"),
                this.getString("style"),
                this.getDouble("x_cord"),
                this.getDouble("y_cord"),
                new String[]{
                        this.getString("text_one"), this.getString("text_two"), this.getString("text_three")
                });

        inObj.setId(this.getInteger("id"));
        inObj.setParent_id(this.getInteger("drawing_board_id"));
        inObj.setFill(this.getString("fill"));

        return inObj;
    }


}
