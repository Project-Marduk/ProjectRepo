package DrawingObjects;

import FactoryElements.InputObject;
import FactoryElements.Interfaces.ComplexShape;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * All Objects that will be displayed in the pane will extend this
 * Though all have at least one text box the text variable will not be stored in the abstract class
 * This is because all have 1+ text boxes each will be required to be their own text box
 */
@Getter @Setter
public abstract class DrawingObject implements ComplexShape { // extends Model
    String svgData;
    String id;
    double x;
    double y;
    InputObject inObject;
    String shapeSVG;

    public DrawingObject(String newId, InputObject inObj){
        id = newId;
        svgData = "";
        inObject = inObj;
        x = inObj.getXCord();
        y = inObj.getYCord();
    }

    public String getSVGData(){
        return svgData;
    }

    //This will be the translation of text to SVG data
    //This may be unnecessary depending on how we convert text to SVG
    public String txtToSVG(String text, double xPos, double yPos){
        return "<text x=" + '"' + Double.toString(xPos) + '"' +
                " y=" + '"' + Double.toString(yPos) + '"' +
                " font-family=" + '"' + "Verdana" + '"' +
                " font-size="+'"' + "18" + '"' +
                " fill="+ '"' + "black" + '"' + ">" +
                text + "</text>";
    }
}
