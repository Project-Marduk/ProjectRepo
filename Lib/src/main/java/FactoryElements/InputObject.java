package FactoryElements;

import lombok.Getter;
import lombok.Setter;

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
public class InputObject {
    String shapeType;
    double[] params;
    String color;
    String style;
    public InputObject(String sType, double[] p, String c, String s){
        shapeType = sType;
        params = p;
        color = c;
        style = s;
    }
}
