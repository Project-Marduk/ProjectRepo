package DrawingObjects;

import FactoryElements.InputObject;
import org.javalite.activejdbc.annotations.Table;

import static DrawingObjects.ShapeSVGFunctions.activationExpressionSVG;
import static DrawingObjects.ShapeSVGFunctions.parallelogramToSVG;

/**
 * @author David Lindeman
 * IFML Parameters can be either DogEaredRectangles OR Parallelograms
 */
//@Table("IFML_Parameter")
public class IFMLParameter extends DrawingObject {
    String headerTxt;
    String bindingTxt;
    boolean hasHeader;

    public IFMLParameter(String id, InputObject inObj){
        super(id, inObj);
        hasHeader = false;
        headerTxt = "";
        bindingTxt = "";
    }

    public void changeHeaderStatus(){
        hasHeader = !hasHeader;
    }

    public String generateShape(){
        int fontSize = 18; //this should be a passed in variable that will be padded
        String partShapeSVG;
        if(super.inObject.getShapeType().equals("Parallelogram")){
            partShapeSVG = parallelogramToSVG(super.inObject);
        }
        else{
            partShapeSVG = activationExpressionSVG(super.inObject);
        }

        String bindingTxtSvg = super.txtToSVG(bindingTxt,
                super.inObject.getXCord() + super.inObject.getParams()[0]*.5,
                super.inObject.getYCord() - super.inObject.getParams()[1]*.5);
        partShapeSVG += "\n" + bindingTxtSvg;
        if(hasHeader){
            String headerTxtSvg = super.txtToSVG(headerTxt,
                    super.inObject.getXCord() + super.inObject.getParams()[0]*.5,
                    super.inObject.getYCord() - super.inObject.getParams()[1]*.25);
            partShapeSVG += headerTxtSvg;
        }
        return partShapeSVG;
    }
}
