package IFML;

import FactoryElements.InputObject;
import FactoryElements.ShapeFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * Event objects for the IFML Factory
 * Like all classes with text boxes these are generated via setter calls from the front end
 */
@Getter @Setter
public class IFMLEvent extends IFMLObject {
    String text;
//    String fillColor = "#000000"; //hex for black
    boolean isFilled; //default value is false

    public IFMLEvent(String id, InputObject inObj){
        super(id, inObj);
        text = "";
        isFilled = false;
    }

    public void generateShape(){
        if(isFilled){
            super.inObject.setColor("#000000"); //sets the fill color to the hex code for black
        }
        ShapeFactory sF = new ShapeFactory();
        super.shapeSVG = sF.create(super.inObject).getSVGData();
        super.setSvgData(super.shapeSVG + super.txtToSVG(text));
    }

}
