package Wireframe;

import FactoryElements.InputObject;
import IFML.DrawingObject;
import lombok.Getter;

import static FactoryElements.ShapeSVGFunctions.*;

@Getter
/**
 * @author David Lindeman
 * Wireframe objects are represented as a single shape with a text box,
 * The user will be able to treat this as a simplistic paint feature to design the UI they want to show
 */
public abstract class WireframeObject extends DrawingObject {
    String txtSVGData;
    double txtX;
    double txtY;

    public WireframeObject(String newId, InputObject inObj){
        super(newId, inObj);
        txtX = inObj.getXCord();
        txtY = inObj.getYCord();
        setShapeData();
        setTxtSVGData(""); //default value of an empty string
        setSVGData();
    }

    public void setTxtX(double x){
        txtX = x;
    }

    public void setTxtY(double y){
        txtY = y;
    }

    public void setSVGData(){
        super.setSvgData(super.getShapeSVG() + "\n" + txtSVGData);
    }

    /**
     * @author David Lindeman
     * Sets the shape SVG data to the type of shape given to it through the input object,
     * defaults to an empty string on invalid inputs
     */
    public void setShapeData(){
        switch (super.getInObject().getShapeType()) {
            case "Rectangle":
                super.setShapeSVG(rectToSVG(super.getInObject()));
            case "Square":
                super.setShapeSVG(squareToSVG(super.getInObject()));
            case "Circle":
                super.setShapeSVG(circleToSVG(super.getInObject()));
            case "Hexagon":
                super.setShapeSVG(hexagonToSvg(super.getInObject()));
            case "Parallelogram":
                super.setShapeSVG(parallelogramToSVG(super.getInObject()));
            default:
                super.setShapeSVG("");
        }
    }

    /**
     * @author David Lindeman
     * @param text
     * @param xPos
     * @param yPos
     * @return
     * This will be the translation of text to SVG data
     * This may be unnecessary depending on how we convert text to SVG
     */
    public String txtToSVG(String text, double xPos, double yPos){
        return "<text x=" + '"' + Double.toString(xPos) + '"' +
                "y=" + '"' + Double.toString(yPos) +
                " font-family=" + '"' + "Verdana" + '"' +
                " font-size="+'"' + "18" + '"' +
                "fill="+ '"' + "black" + '"' + ">" +
                text + "</text>";
    }

    /**
     * @author David Lindeman
     * @param text
     * Sets text in the upper left hand corner of the object
     * This can be changed with the setter function
     */
    public void setTxtSVGData(String text){
        txtSVGData = txtToSVG(text, super.getX(), super.getY());
    }

}
