package DrawingObjects;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;

import static DrawingObjects.ShapeSVGFunctions.activationExpressionSVG;

/**
 * @author David Lindeman
 * This object will require a special SVG shape construction as it has 5 sides and is not a traditional shape
 * TextBoxes is size 1
 * InputObjects Params are size 2
 */
@Getter @Setter
//@Table("IFML_Activation_Expression")
public class IFMLActivationExpression extends DrawingObject {

    public IFMLActivationExpression(String id, InputObject inObj){
        super(id, inObj);
        super.inObject.setColor("#B7BDBB"); //light blue/grey, there are two colors to this shape the other is white
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        super.inObject.getXCord() + inObject.getParams()[0]*.5,
                        super.inObject.getYCord() + super.inObject.getParams()[1]*1.5)
        });
    }

    public String generateShape(){
        return activationExpressionSVG(super.inObject);
    }

}
