package DrawingObjects;

import FactoryElements.InputObject;
import static DrawingObjects.ShapeSVGFunctions.rectToSVG;

import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author David Lindeman
 * IFML component, will be initialized as an empty rectangle
 * InputObject params have 1 dim
 * Has 1 text box
 */
@Getter @Setter
//@Table("IFML_View_Component")
public class IFMLViewComponent extends DrawingObject {
    String fillColor = "#B7BDBB"; //Hex for light blue grey

    @Override
    public void initialize(String id, InputObject inObj){
        super.initialize(id, inObj);

        TextBox t1;
        t1 = new TextBox();
        t1.initialize("",
                super.inObject.getXCord() + inObject.getParams()[0]*.5,
                super.inObject.getYCord() + super.inObject.getParams()[1]*.5);
        super.setTextBoxes(new TextBox[]{t1,});
    }

    public String generateShape(){
        //TODO: Create a rounded edge rectangle shape
        return rectToSVG(super.inObject);
    }
}
