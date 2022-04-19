package DrawingObjects;

import FactoryElements.InputObject;
import org.javalite.activejdbc.annotations.Table;

import static DrawingObjects.ShapeSVGFunctions.getLineElement;

//@Table("Line")
public class Line extends LineObject {
    @Override
    public void initialize(String id, InputObject inObj){
        super.initialize(id, inObj);
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        super.getX(),
                        super.getY())
        });
    }

    public String generateShape(){
        return getLineElement(
                Double.toString(super.getX()),
                Double.toString(super.getY()),
                Double.toString(super.getSecondXCord()),
                Double.toString(super.getSecondYCord())
        );
    }
}
