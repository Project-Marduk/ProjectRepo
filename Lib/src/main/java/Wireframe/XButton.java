package Wireframe;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;

import static FactoryElements.ShapeSVGFunctions.getLineElement;
import static FactoryElements.ShapeSVGFunctions.squareToSVG;

/**
 * @author David Lindeman
 * creates a square button with an "X" through it
 */
@Getter @Setter
public class XButton extends WireframeObject{

    public XButton(String id, InputObject inObj){
        super(id, inObj);
    }

    public void generateShape(){
        super.shapeSVG = squareToSVG(super.inObject);
        super.shapeSVG += "\n" + getLineElement(
                Double.toString(super.inObject.getXCord()),
                Double.toString(super.inObject.getXCord() + super.inObject.getParams()[0]),
                Double.toString(super.inObject.getYCord()),
                Double.toString(super.inObject.getYCord() - super.inObject.getParams()[0]));
        super.shapeSVG += "\n" + getLineElement(
                Double.toString(super.inObject.getXCord() + super.inObject.getParams()[0]),
                Double.toString(super.inObject.getXCord()),
                Double.toString(super.inObject.getYCord()),
                Double.toString(super.inObject.getYCord() - super.inObject.getParams()[0]));
    }

}
