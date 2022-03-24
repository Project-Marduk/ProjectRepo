package Wireframe;

import FactoryElements.InputObject;

import static FactoryElements.ShapeSVGFunctions.rectToSVG;
import static FactoryElements.ShapeSVGFunctions.getLineElement;

public class DisplayWindow extends WireframeObject{

    public DisplayWindow(String id, InputObject inObj){
        super(id, inObj);
    }

    public void generateShape(){
        super.shapeSVG = rectToSVG(super.inObject);
        super.shapeSVG += "\n" + getLineElement(
                Double.toString(super.inObject.getXCord()),
                Double.toString(super.inObject.getXCord() + super.inObject.getParams()[0]),
                Double.toString(super.inObject.getYCord()),
                Double.toString(super.inObject.getYCord() - super.inObject.getParams()[1]));
        super.shapeSVG += "\n" + getLineElement(
                Double.toString(super.inObject.getXCord() + super.inObject.getParams()[0]),
                Double.toString(super.inObject.getXCord()),
                Double.toString(super.inObject.getYCord()),
                Double.toString(super.inObject.getYCord() - super.inObject.getParams()[1]));
    }
}
