package IFML;

import FactoryElements.InputObject;

import static FactoryElements.ShapeSVGFunctions.activationExpressionSVG;
import static FactoryElements.ShapeSVGFunctions.parallelogramToSVG;

/**
 * @author David Lindeman
 * IFML Parameters can be either DogEaredRectangles OR Parallelograms
 */
public class IFMLParameter extends DrawingObject {
    String headerTxt;
    String bindingTxt;
    boolean hasHeader;

    public IFMLParameter(String id, InputObject inObj, boolean hasHead){
        super(id, inObj);
        hasHeader = hasHead;
        headerTxt = "";
        bindingTxt = "";
    }

    public void generateShape(){
        int fontSize = 18; //this should be a passed in variable that will be padded
        if(super.inObject.getShapeType().equals("parallelogram")){
            super.shapeSVG = parallelogramToSVG(super.inObject);
        }
        else{
            super.shapeSVG = activationExpressionSVG(super.inObject);
        }

        String bindingTxtSvg = super.txtToSVG(bindingTxt,
                super.inObject.getXCord() + super.inObject.getParams()[0]*.5,
                super.inObject.getYCord() - super.inObject.getParams()[1]*.5);
        super.svgData = shapeSVG + bindingTxtSvg;
        if(hasHeader){
            String headerTxtSvg = super.txtToSVG(headerTxt,
                    super.inObject.getXCord() + super.inObject.getParams()[0]*.5,
                    super.inObject.getYCord() - super.inObject.getParams()[1]*.25);
            super.svgData += headerTxtSvg;
        }
    }
}
