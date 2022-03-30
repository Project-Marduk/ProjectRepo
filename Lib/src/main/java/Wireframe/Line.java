package Wireframe;

import FactoryElements.InputObject;
import IFML.LineObject;

import static FactoryElements.ShapeSVGFunctions.getLineElement;

public class Line extends LineObject {
    public Line(String id, InputObject inObj) {
        super(id, inObj);
    }

    public void generateShape(){
        super.setShapeSVG(getLineElement(
                Double.toString(super.getX()),
                Double.toString(super.getY()),
                Double.toString(super.getSecondXCord()),
                Double.toString(super.getSecondYCord())
        ));
    }
}
