package IFML;

import FactoryElements.InputObject;
import FactoryElements.Interfaces.ComplexShape;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * All IFML Objects will extend this
 * Though all have at least one text box the text variable will not be stored in the abstract class
 * This is because all have 1+ text boxes each will be required to be their own text box
 */
@Getter @Setter
public abstract class IFMLObject implements ComplexShape {
    public String svgData;
    public String id;
    InputObject inObject;
    String shapeSVG;

    public IFMLObject(String newId, InputObject inObj){
        id = newId;
        svgData = "";
        inObject = inObj;
    }

    public String getSVGData(){
        return svgData;
    }

    //This will be the translation of text to SVG data
    //This may be unnecessary depending on how we convert text to SVG
    public String txtToSVG(String text){
        //TODO: Translate text to SVG data
        return text;
    }
}
