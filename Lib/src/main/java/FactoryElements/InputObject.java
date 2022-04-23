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
//@Table("Input_Object")
@Getter @Setter
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

    /**
     * @param sType Type: Discrete Type Variable, reference the ShapeType enum.
     * @param p parameters array of double, different behavior per shape type. usually width and height.
     * @param c color in ??? form;
     * @param s Style in ??? form;
     * @param x X coordinate
     * @param y Y Coordinate
     * @param t an array or String for the Text boxes
     */
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
}
