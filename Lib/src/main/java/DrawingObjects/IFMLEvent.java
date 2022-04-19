package DrawingObjects;

import FactoryElements.InputObject;
import static DrawingObjects.ShapeSVGFunctions.circleToSVG;
import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author David Lindeman
 * Event objects for the IFML Factory
 * Like all classes with text boxes these are generated via setter calls from the front end
 * TODO: isFilled cannot be accessed with current implementation
 */
@Getter @Setter
//@Table("IFML_Event")
public class IFMLEvent extends DrawingObject {
    boolean isFilled; //default value is false

    @Override
    public void initialize(String id, InputObject inObj){
        super.initialize(id, inObj);
        isFilled = false;
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        super.inObject.getXCord() + 2*super.inObject.getParams()[0],
                        super.inObject.getYCord() + super.inObject.getParams()[0])
        });
    }

    public String generateShape(){
        if(isFilled){
            super.inObject.setColor("#000000"); //sets the fill color to the hex code for black
        }
        return circleToSVG(super.inObject);
    }

}
