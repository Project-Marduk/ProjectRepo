package DrawingObjects;

import FactoryElements.InputObject;
import static DrawingObjects.ShapeSVGFunctions.rectToSVG;

import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author David Lindeman
 * IFML component, will be initialized as an empty rectangle
 */
@Getter @Setter
//@Table("IFML_View_Component")
public class IFMLViewComponent extends DrawingObject {
    String componentName;
    String text;
    String fillColor = "#B7BDBB"; //Hex for light blue grey

    public IFMLViewComponent(String id, InputObject inObj){
        super(id, inObj);
        componentName = "";
    }

    public String generateShape(){
        //TODO: Create a rounded edge rectangle shape
        return rectToSVG(super.inObject) + "\n" + super.txtToSVG(text,
                super.inObject.getXCord() + inObject.getParams()[0]*.5,
                super.inObject.getYCord() + super.inObject.getParams()[1]*.5);
    }
}
