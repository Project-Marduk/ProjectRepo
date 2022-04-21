package FactoryElements;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;

import java.io.Serializable;

/**
 * @author David Lindeman
 * All inputs must be passed into the input object
 * shapeType will determine the type of shape the factory will create
 * params will determine numerical input values give (usually dimensions)
 * color will determine the color of the shape
 * style will determine the line style type of the shape
 * complex items will need to be passed as a list of input objects
 * All type checking will need to be done on the front end(s)
 */
@Getter @Setter
//@Table("Input_Object")
public class InputObject implements Serializable {
    Integer id;
    Integer parent_id;
    String shapeType;
    double xCord;
    double yCord;
    double[] params;
    String color;
    String style;
    String fill;
    String[] text;

    public InputObject(){
        fill = fill = "#FFFFFF";
    }
    public InputObject(String sType, double[] p, String c, String s, double x, double y, String[] t){ //TODO: Look up default values in Java

        shapeType = sType;
        params = p;
        color = c;
        style = s;
        xCord = x;
        yCord = y;
        fill = "#FFFFFF"; //set default value as white fill
        text = t;
    }

    public String toJSON() {
        //create Gson instance
        Gson gson = new Gson();
        //create json string to hold data
        String jsonString = gson.toJson(this);
        return jsonString;
    }
}
