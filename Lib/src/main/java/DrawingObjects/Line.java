package DrawingObjects;

import FactoryElements.InputObject;
import org.javalite.activejdbc.annotations.Table;

import static DrawingObjects.ShapeSVGFunctions.getLineElement;

//@Table("Line")
public class Line extends LineObject {
    public Line(String id, InputObject inObj) {
        super(id, inObj);
    }

    public String generateShape(){
        return getLineElement(
                Double.toString(super.getX()),
                Double.toString(super.getY()),
                Double.toString(super.getSecondXCord()),
                Double.toString(super.getSecondYCord())
        );
    }
}
