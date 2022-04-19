package DrawingObjects;

import FactoryElements.InputObject;
import org.javalite.activejdbc.annotations.Table;

import static DrawingObjects.ShapeSVGFunctions.hexagonToSvg;

/**
 * @author David Lindeman
 * IFML Action,
 * Has 1 text box
 * InputObject params have 1 dim
 */
//@Table("IFML_Action")
public class IFMLAction extends DrawingObject {
    String componentName;
    String fillColor = "#B7BDBB"; //Hex for light blue grey

    public IFMLAction(String id, InputObject inObj){
        super(id, inObj);
        componentName = "";
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        super.inObject.getXCord() + inObject.getParams()[0]*.5,
                        super.inObject.getYCord() + super.inObject.getParams()[0]*.5)
        });
    }

    public String generateShape(){
        return hexagonToSvg(super.inObject);
    }
}
