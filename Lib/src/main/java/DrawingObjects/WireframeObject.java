package DrawingObjects;

import FactoryElements.InputObject;
import lombok.Getter;

import static DrawingObjects.ShapeSVGFunctions.*;

@Getter
/**
 * @author David Lindeman
 * Wireframe objects are represented as a single shape with a text box,
 * The user will be able to treat this as a simplistic paint feature to design the UI they want to show
 */
//@Table("Wireframe_Object")
public class WireframeObject extends DrawingObject {
//    String txtSVGData;
    double txtX;
    double txtY;
    String text;

    public WireframeObject(String newId, InputObject inObj){
        super(newId, inObj);
        txtX = inObj.getXCord();
        txtY = inObj.getYCord();
//        setTxtSVGData(""); //default value of an empty string
//        generateShape();
    }

    public void setTxtX(double x){
        txtX = x;
    }

    public void setTxtY(double y){
        txtY = y;
    }

    /**
     * @author David Lindeman
     * Sets the shape SVG data to the type of shape given to it through the input object,
     * defaults to an empty string on invalid inputs
     */
    public void generateShape(){
        switch (super.getInObject().getShapeType()) {
            case "Rectangle":
                super.setShapeSVG(rectToSVG(super.getInObject()));
                break;
            case "Square":
                super.setShapeSVG(squareToSVG(super.getInObject()));
                break;
            case "Circle":
                super.setShapeSVG(circleToSVG(super.getInObject()));
                break;
            case "Hexagon":
                super.setShapeSVG(hexagonToSvg(super.getInObject()));
                break;
            case "Parallelogram":
                super.setShapeSVG(parallelogramToSVG(super.getInObject()));
                break;
            default:
                super.setShapeSVG("");
                break;
        }
        //combine SVG Data
        super.setSvgData(super.getShapeSVG() + "\n" + getTxtSVGData(text));
    }

    /**
     * @author David Lindeman
     * @param text
     * Sets text in the upper left hand corner of the object
     * This can be changed with the setter function
     */
    public String getTxtSVGData(String text){
        return txtToSVG(text, super.getX(), super.getY());
    }

}
